package com.playspirit.corey.twitcast.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playspirit.corey.twitcast.R;
import com.playspirit.corey.twitcast.twitParse.ParseTwitEpisodes;
import com.playspirit.corey.twitcast.views.VideoViewActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class DiscoverNewAdapter extends RecyclerView.Adapter<DiscoverNewViewHolder> {
    private Context mContext;
    private ParseTwitEpisodes[] mParseTwitEpisodes;

    public DiscoverNewAdapter(Context context, ParseTwitEpisodes[] parseTwitEpisodes) {
        mContext = context;
        mParseTwitEpisodes = parseTwitEpisodes;

    }

    @Override
    public DiscoverNewViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_new_item, null);
        return new DiscoverNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscoverNewViewHolder discoverNewViewHolder, int position) {
        ParseTwitEpisodes parseTwitEpisodes = mParseTwitEpisodes[position];
        Picasso.with(mContext).load(parseTwitEpisodes.getCoverArtUrlThumb())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(discoverNewViewHolder.thumbnail);

        discoverNewViewHolder.episodeTitle.setText(parseTwitEpisodes.getLabel());
        discoverNewViewHolder.showTitle.setText(parseTwitEpisodes.getShowLabel());
        final String mUri = parseTwitEpisodes.getMediaUrl();
        final String mTitle = parseTwitEpisodes.getLabel();
        final String mShowLabel = parseTwitEpisodes.getShowLabel();
        discoverNewViewHolder.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, VideoViewActivity.class);
                intent.putExtra("mUrl", mUri);
                intent.putExtra("mTitle", mTitle);
                intent.putExtra("mShowLabel", mShowLabel);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mParseTwitEpisodes ? mParseTwitEpisodes.length : 0);
    }
}