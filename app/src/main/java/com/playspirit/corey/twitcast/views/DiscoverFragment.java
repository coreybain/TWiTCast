package com.playspirit.corey.twitcast.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playspirit.corey.twitcast.R;
import com.playspirit.corey.twitcast.frameworks.SlidingTabLayout;
import com.playspirit.corey.twitcast.adapters.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    ViewPager pager;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Shows","New","Trending"};
    int Numboftabs = 3;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discover,container,false);
        pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());

        tabs = (SlidingTabLayout) v.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        return v;
    }

    private PagerAdapter buildAdapter() {
        return(new ViewPagerAdapter(getFragmentManager(),Titles,Numboftabs));
    }

}
