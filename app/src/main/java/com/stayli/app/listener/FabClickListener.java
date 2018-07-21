package com.stayli.app.listener;

import android.content.res.ColorStateList;
import android.view.View;

/**
 * Created by yhgao on 2018/2/7.
 */

public class FabClickListener implements View.OnClickListener {


    public static interface Callback {
        void changeFtbTintList(ColorStateList colorStateList);
        void current2Position();
    }

    private Callback mCallback;

    public FabClickListener(Callback callback) {
        mCallback =callback;

    }

    @Override
    public void onClick(View v) {
        int[][] states = new int[1][3];
        int[] colors = new int[2];
        colors[0] = 0xFF3F51B5;
        colors[1] = 0xFFCCC;
        mCallback.changeFtbTintList(new ColorStateList(states,colors));
        mCallback.current2Position();
    }

}
