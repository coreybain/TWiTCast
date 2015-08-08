package com.playspirit.corey.twitcast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.playspirit.corey.twitcast.R;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class DiscoverShowsViewHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;
    private Context mContext;

    public DiscoverShowsViewHolder(View View) {
        super(View);
        this.thumbnail = (ImageView) View.findViewById(R.id.discoverShowCoverImage);
    }
}
