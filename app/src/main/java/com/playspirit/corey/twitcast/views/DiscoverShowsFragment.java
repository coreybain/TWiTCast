package com.playspirit.corey.twitcast.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.playspirit.corey.twitcast.MainActivity;
import com.playspirit.corey.twitcast.R;
import com.playspirit.corey.twitcast.frameworks.SpacesItemDecoration;
import com.playspirit.corey.twitcast.adapters.DiscoverShowsAdapter;
import com.playspirit.corey.twitcast.twitParse.ParseTwitData;
import com.playspirit.corey.twitcast.twitParse.ParseTwitShows;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class DiscoverShowsFragment extends Fragment {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ParseTwitData mParseTwitData;
    private ParseTwitShows[] mTwitShows;
    private ProgressBar mLoginProgressbar;
    private Call call;
    private DiscoverShowsAdapter discoverShowAdapter;
    private RecyclerView mRecyclerView;
    private Boolean loadDataDone;
    private Boolean callRunning;

    public DiscoverShowsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataDone = false;
        callRunning = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.discover_shows_fragment, container, false);
        mLoginProgressbar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mLoginProgressbar.setVisibility(View.INVISIBLE);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_grid);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        if (!loadDataDone) {
            String twitUrlPrefix = "";
            mLoginProgressbar.setVisibility(View.VISIBLE);
            loadTwitData(twitUrlPrefix);
        } else {
            updateTwitShowsDisplay();
        }

        setHasOptionsMenu(true);
        return rootView;
    }

    private void updateTwitShowsDisplay() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // This code will always run on the UI thread, therefore is safe to modify UI elements.
                mLoginProgressbar.setVisibility(View.INVISIBLE);
                mTwitShows = Arrays.copyOf(mParseTwitData.getParseTwitShows(), mParseTwitData.getParseTwitShows().length, ParseTwitShows[].class);
                discoverShowAdapter = new DiscoverShowsAdapter(getActivity(), mTwitShows);
                mRecyclerView.setAdapter(discoverShowAdapter);
            }
        });
    }

    private ParseTwitData parseTwitDetails(String jsonData) throws JSONException {
        ParseTwitData parseTwitData = new ParseTwitData();
        parseTwitData.setParseTwitShows(getTwitShows(jsonData));
        return parseTwitData;
    }

    //THIS IS WHERE THE JSON DATA IS PARSED FOR TWIT SHOWS
    private ParseTwitShows[] getTwitShows(String jsonData) throws JSONException {
        JSONObject twitData = new JSONObject(jsonData);
        JSONArray twitShows = twitData.getJSONArray("shows");

        ParseTwitShows[] parseTwitShow = new ParseTwitShows[twitShows.length()];

        for (int i=0; i < twitShows.length(); i++) {
            JSONObject jsonTwitShows = twitShows.getJSONObject(i);
            JSONObject twitDerivatives = jsonTwitShows.getJSONObject("coverArt");
            JSONObject twitCoverImage = twitDerivatives.getJSONObject("derivatives");
            ParseTwitShows parseTwitShows = new ParseTwitShows();

            parseTwitShows.setId(jsonTwitShows.getInt("id"));
            parseTwitShows.setLabel(jsonTwitShows.getString("label"));
            parseTwitShows.setDiscription(jsonTwitShows.getString("description"));
            parseTwitShows.setShowDate(jsonTwitShows.getString("showDate"));
            parseTwitShows.setShowContactInfo(jsonTwitShows.getString("showContactInfo"));
            parseTwitShows.setTageLine(jsonTwitShows.getString("tagLine"));
            parseTwitShows.setShortCode(jsonTwitShows.getString("shortCode"));
            parseTwitShows.setCoverArtUrl300(twitCoverImage.getString("twit_album_art_600x600"));
            parseTwitShows.setCoverArtUrlThumb(twitCoverImage.getString("twit_album_art_300x300"));
            parseTwitShows.setShowUrl(jsonTwitShows.getString("cleanPath"));
            parseTwitShow[i] = parseTwitShows;
        }

        return parseTwitShow;
    }

    private void writeToFile(String jsonData) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput("twitShowsCache.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonData);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {

        String jsonData = "";

        try {
            InputStream inputStream = getActivity().openFileInput("twitShowsCache.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                jsonData = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return jsonData;
    }

    public void loadTwitData(final String twitUrlPrefix) {
        //OKHTTP IS GETTING THE INFO FROM TWIT API
        if (isAirplaneModeOn()) {
            //This method is below and checks if the reason the users connection fail is airplane mode and informs the user what to do
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getActivity(), "You found Leo's salad: The network is down right now try again later", Toast.LENGTH_LONG).show();
                }
            });
        } else if (readFromFile().isEmpty()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://twit.tv/api/v1.0/shows" + twitUrlPrefix)
                    .header("Accept", "application/json")
                    .addHeader("app-id", "3e742ac7")
                    .addHeader("app-key", "2a6557daace8c6524cc82af2e718fbcc")
                    .build();

            callRunning = true;
            call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Leo's salad: Sorry it looks like the internet is not working.", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    //THIS IS WHERE THE CODE TO GET THE DATA FROM TWIT IS STORED:
                    //TODO: THis is the refresh loader -- add from other app

                    try {
                        String jsonData = response.body().string();

                        writeToFile(jsonData);
                        Log.v(TAG, "THIS IS THE OUTPUT " + jsonData);
                        if (response.isSuccessful()) {
                            mParseTwitData = parseTwitDetails(jsonData);
                            // TwitShowAdapter adapter = new TwitShowAdapter(getListView().getContext(), mTwitShows);
                            // mListView.setAdapter(adapter);
                                  /*  mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            String nameOfShow = mTwitShows[position].getLabel();
                                            Toast.makeText(getActivity(), "THIS IS: " + nameOfShow, Toast.LENGTH_SHORT).show();
                                        }
                                    });*/

                            updateTwitShowsDisplay();
                        } else {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getActivity(), "Leo's salad: Sorry we had trouble displaying the data.", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    } catch (IOException | JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        } else {
            try {
                String jsonData = readFromFile();
                Log.v(TAG, "THIS IS THE CACHED OUTPUT " + jsonData);
                mParseTwitData = parseTwitDetails(jsonData);
                updateTwitShowsDisplay();
            } catch (JSONException e) {
                Log.e(TAG, "Exception caught: ", e);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "Leo's salad: The network is down right now try again later", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private boolean isAirplaneModeOn() {
        boolean isAirplaneMode = false;
        if (Settings.System.getInt(getActivity().getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1) {
            isAirplaneMode = true;
        }
        return isAirplaneMode;
    }
}
