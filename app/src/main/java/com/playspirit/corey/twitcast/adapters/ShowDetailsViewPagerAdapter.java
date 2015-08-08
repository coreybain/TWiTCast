package com.playspirit.corey.twitcast.adapters;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentPagerAdapter;

import com.playspirit.corey.twitcast.detailViews.DiscoverShowsDetailsActivity;
import com.playspirit.corey.twitcast.detailViews.ViewPagerFragment;

/**
 * Created by coreybaines on 7/08/2015.
 */
public class ShowDetailsViewPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    public ShowDetailsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.app.Fragment getItem(int i) {
        ViewPagerFragment moduleFragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(DiscoverShowsDetailsActivity.KEY_VIEW_PAGER_FRAG_TYPE_STRING, getString(mViewPagerFragmentTitles[i]));
        moduleFragment.setArguments(args);
        return moduleFragment;

    }

    @Override
    public int getCount() {
        return mViewPagerFragmentTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getString(mViewPagerFragmentTitles[position]);
    }
}