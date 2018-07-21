package com.stayli.app.adapter.frag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by yhgao on 2018/2/8.
 */

public class MusicContentPagerAdapter extends BaseFragmentPagerAdapter {
    private static List<String> mTabIndicators;

    public MusicContentPagerAdapter(FragmentManager fm,
                                    List<Fragment> musicTabFragments,
                                    List<String> musicTabIndicator) {
        super(fm, musicTabFragments);
        mTabIndicators = musicTabIndicator;
    }


    @Override
    public int getCount() {
        return mTabIndicators.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabIndicators.get(position);
    }
}
