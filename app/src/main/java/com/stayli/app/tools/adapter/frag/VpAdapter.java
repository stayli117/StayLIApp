package com.stayli.app.tools.adapter.frag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by yhgao on 2018/2/7.
 */

public class VpAdapter extends BaseFragmentPagerAdapter {
    public VpAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm, data);
    }
}
