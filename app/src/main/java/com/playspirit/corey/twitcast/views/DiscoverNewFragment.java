package com.playspirit.corey.twitcast.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.playspirit.corey.twitcast.frameworks.DividerItemDecoration;
import com.playspirit.corey.twitcast.MainActivity;
import com.playspirit.corey.twitcast.R;
import com.playspirit.corey.twitcast.adapters.DiscoverNewAdapter;
import com.playspirit.corey.twitcast.twitParse.ParseTwitData;
import com.playspirit.corey.twitcast.twitParse.ParseTwitEpisodes;
import com.playspirit.corey.twitcast.twitParse.ParseTwitShows;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class DiscoverNewFragment extends Fragment {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ParseTwitData mParseTwitData;
    private ParseTwitEpisodes[] mTwitEpisodes;
    private ParseTwitShows[] mParseTwitShowses;
    private ProgressBar mLoginProgressbar;
    private Call call;
    private DiscoverNewAdapter discoverNewAdapter;
    private RecyclerView mRecyclerView;
    private Boolean loadDataDone;

    public DiscoverNewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataDone = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.discover_new_fragment, container, false);
        mLoginProgressbar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mLoginProgressbar.setVisibility(View.INVISIBLE);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_list);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (loadDataDone == false) {
            String twitUrlPrefix = "";
            mLoginProgressbar.setVisibility(View.VISIBLE);
            loadTwitData(twitUrlPrefix);
        } else {
            updateTwitShowsDisplay();
        }

        return rootView;
    }


    private void updateTwitShowsDisplay() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // This code will always run on the UI thread, therefore is safe to modify UI elements.
                mLoginProgressbar.setVisibility(View.INVISIBLE);
                mTwitEpisodes = Arrays.copyOf(mParseTwitData.getParseTwitEpisodes(), mParseTwitData.getParseTwitEpisodes().length, ParseTwitEpisodes[].class);
                discoverNewAdapter = new DiscoverNewAdapter(getActivity(), mTwitEpisodes);
                mRecyclerView.setAdapter(discoverNewAdapter);
            }
        });
    }

    private ParseTwitData parseTwitDetails(String jsonData) throws JSONException {
        ParseTwitData parseTwitData = new ParseTwitData();
        parseTwitData.setParseTwitEpisodes(getTwitEpisodes(jsonData));
        return parseTwitData;
    }

    //THIS IS WHERE THE JSON DATA IS PARSED FOR TWIT SHOWS
    private ParseTwitEpisodes[] getTwitEpisodes(String jsonData) throws JSONException {
        JSONObject twitData = new JSONObject(jsonData);
        JSONArray twitEpisodes = twitData.getJSONArray("episodes");

        ParseTwitEpisodes[] parseTwitEpisode = new ParseTwitEpisodes[twitEpisodes.length()];

        for (int i=0; i < twitEpisodes.length(); i++) {
            JSONObject jsonTwitEpisodes = twitEpisodes.getJSONObject(i);
            ParseTwitEpisodes parseTwitEpisodes = new ParseTwitEpisodes();
            if (!jsonTwitEpisodes.isNull("video_small")) {
                JSONObject jsonTwitVideoHd = jsonTwitEpisodes.getJSONObject("video_small");
                parseTwitEpisodes.setMediaUrl(jsonTwitVideoHd.getString("mediaUrl"));
            }
            parseTwitEpisodes.setId(jsonTwitEpisodes.getInt("id"));
            parseTwitEpisodes.setLabel(jsonTwitEpisodes.getString("label"));

            JSONObject twitEmbedded = jsonTwitEpisodes.getJSONObject("_embedded");
            JSONArray twitShowsEmbedded = twitEmbedded.getJSONArray("shows");
            JSONObject jsonTwitShowsEmbedded = null;
            JSONObject twitCoverImage = null;
            ParseTwitEpisodes[] parseembededshow = new ParseTwitEpisodes[twitShowsEmbedded.length()];
            for (int t=0; t < twitShowsEmbedded.length(); t++) {
                jsonTwitShowsEmbedded = twitShowsEmbedded.getJSONObject(t);
                JSONObject twitDerivatives = jsonTwitShowsEmbedded.getJSONObject("coverArt");
                twitCoverImage = twitDerivatives.getJSONObject("derivatives");
            }
            parseTwitEpisodes.setCoverArtUrlThumb(twitCoverImage.getString("twit_album_art_300x300"));
            parseTwitEpisodes.setShowLabel(jsonTwitShowsEmbedded.getString("label"));

            parseTwitEpisode[i] = parseTwitEpisodes;

        }

        return parseTwitEpisode;
    }

    public void loadTwitData(final String twitUrlPrefix) {
        //OKHTTP IS GETTING THE INFO FROM TWIT API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://twit.tv/api/v1.0/episodes" + twitUrlPrefix)
                .header("Accept", "application/json")
                .addHeader("app-id", "3e742ac7")
                .addHeader("app-key", "2a6557daace8c6524cc82af2e718fbcc")
                .build();

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
                    Log.v(TAG, "THIS IS THE OUTPUT FOR EPISODES " + jsonData);
                    if (response.isSuccessful()) {
                        mParseTwitData = parseTwitDetails(jsonData);
                        updateTwitShowsDisplay();
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getActivity(), "You found Leo's salad: The network is down right now try again later", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                } catch (IOException | JSONException e) {
                    Log.e(TAG, "Exception caught: ", e);
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            mLoginProgressbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), "Leo's salad: Sorry it looks like parse the Json data failed.", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }
}