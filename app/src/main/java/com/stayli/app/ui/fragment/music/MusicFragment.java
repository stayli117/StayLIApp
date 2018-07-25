package com.stayli.app.ui.fragment.music;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.stayli.app.R;
import com.stayli.app.tools.adapter.frag.MusicContentPagerAdapter;
import com.stayli.app.base.BaseFragment;
import com.stayli.app.databinding.FragmentMusicBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhgao on 2018/2/8.
 */

public class MusicFragment extends BaseFragment {


    private MusicContentPagerAdapter mAdapter;
    private TabLayout mIndicator;
    private static List<String> tabIndicators;
    private static List<Fragment> tabFragments;


    public MusicFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title  Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicFragment newInstance(String title, String param2) {
        MusicFragment t = new MusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PARAM2, param2);
        t.setArguments(args);
        return t;
    }


    @Override
    public int onCreateView() {
        return R.layout.fragment_music;
    }

    @Override
    public void bindData(View view) {
        FragmentMusicBinding bind = DataBindingUtil.bind(view);
        initContent();
        ViewPager vp = bind.vpContent;
        vp.setAdapter(mAdapter);
        mIndicator.setupWithViewPager(vp);
    }


    public void setIndicator(TabLayout indicator) {
        mIndicator = indicator;
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();

        tabIndicators.add("音乐");
        tabIndicators.add("股票");
        tabIndicators.add("电台");

        tabFragments = new ArrayList<>();
        for (String indicator : tabIndicators) {
            Fragment fragment = null;
            if ("股票".equals(indicator)) {
                fragment = GuPiaoFragment.newInstance(indicator);
            } else {
                fragment = TabListFragment.newInstance(indicator);
            }

            tabFragments.add(fragment);
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        mAdapter = new MusicContentPagerAdapter(manager, tabFragments, tabIndicators);

    }
}
