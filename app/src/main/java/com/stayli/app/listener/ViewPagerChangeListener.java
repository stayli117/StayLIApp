package com.stayli.app.listener;

import android.support.v4.view.ViewPager;

/**
 * Created by yhgao on 2018/2/7.
 */

public class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {

    private CurrentInterface mCurrentInterface;
    public ViewPagerChangeListener(CurrentInterface currentInterface) {
        mCurrentInterface =currentInterface;
    }

    public static interface CurrentInterface {
        void currentNo2Position(int position);
        void current2Position();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            // 中间位置出发点击事件
           mCurrentInterface.current2Position();
        } else {
           mCurrentInterface.currentNo2Position(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
