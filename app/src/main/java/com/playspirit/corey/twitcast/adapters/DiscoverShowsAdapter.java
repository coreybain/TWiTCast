package com.playspirit.corey.twitcast.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.playspirit.corey.twitcast.MainActivity;
import com.playspirit.corey.twitcast.R;
import com.playspirit.corey.twitcast.detailViews.DiscoverShowsDetailsActivity;
import com.playspirit.corey.twitcast.twitParse.ParseTwitShows;
import com.squareup.picasso.Picasso;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class DiscoverShowsAdapter extends RecyclerView.Adapter<DiscoverShowsViewHolder> implements View.OnClickListener{
    private Context mContext;
    private ParseTwitShows[] mParseTwitShowses;


    public DiscoverShowsAdapter(Context context, ParseTwitShows[] parseTwitShowses) {
        mContext = context;
        mParseTwitShowses = parseTwitShowses;
        //FragmentManager fm = ((FragmentActivity)mContext).getFragmentManager();
    }

    @Override
    public DiscoverShowsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_shows_item, null);
        return new DiscoverShowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscoverShowsViewHolder discoverShowsViewHolder, final int position) {
        final ParseTwitShows parseTwitShows = mParseTwitShowses[position];
        Picasso.with(mContext).load(parseTwitShows.getCoverArtUrl300())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(discoverShowsViewHolder.thumbnail);
        discoverShowsViewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, DiscoverShowsDetailsActivity.class);
                mContext.startActivity(intent);
                ((MainActivity)mContext).overridePendingTransition(R.anim.slide_up, R.anim.stay);
                //mContext.startActivity(new Intent(mContext, DiscoverShowsDetailsActivity.class));
/*
               ShowsDetailsFragment fragment = new ShowsDetailsFragment();
                FragmentManager frgManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                frgManager.beginTransaction().replace(R.id.frame,fragment).commit();


                ShowsDetailsFragment fragment = new ShowsDetailsFragment();
                android.app.FragmentTransaction testing = ((FragmentActivity)mContext).getChildFragmentManager()().beginTransaction();

                //testing.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                testing.addToBackStack(null);
                testing.replace(R.id.frame, fragment);
                testing.addToBackStack(null);
                testing.commit();


                BlankFragment fragment = new BlankFragment();
                android.app.FragmentTransaction transaction = ((FragmentActivity)mContext).getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();*/

                Toast.makeText(mContext, "This show is: " + parseTwitShows.getLabel(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return (null != mParseTwitShowses ? mParseTwitShowses.length : 0);
    }

    @Override
    public void onClick(View v) {

    }
}