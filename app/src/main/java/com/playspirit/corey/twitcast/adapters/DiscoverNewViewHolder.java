package com.playspirit.corey.twitcast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playspirit.corey.twitcast.R;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class DiscoverNewViewHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;
    protected ImageView playBtn;
    protected ImageView downloadBtn;
    protected TextView episodeTitle;
    protected TextView showTitle;
    private Context mContext;

    public DiscoverNewViewHolder(View View) {
        super(View);
        this.thumbnail = (ImageView) View.findViewById(R.id.discoverNewCoverImage);
        this.episodeTitle = (TextView) View.findViewById(R.id.discoverNewEpisodeLabel);
        this.showTitle = (TextView) View.findViewById(R.id.discoverNewShowLabel);
        this.playBtn = (ImageView) View.findViewById(R.id.discoverNewPlayBtn);
    }
}