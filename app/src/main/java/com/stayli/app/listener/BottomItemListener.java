package com.stayli.app.listener;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.stayli.app.R;

/**
 * Created by yhgao on 2018/2/7.
 */

public class BottomItemListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "BottomItemListener";
    private int previousPosition = -1;
    private CurrentInterface mCurrentInterface;

    public static interface CurrentInterface {
        void currentPosition(int position);
    }

    public BottomItemListener(CurrentInterface currentInterface) {
        mCurrentInterface = currentInterface;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int position = 0;
        switch (item.getItemId()) {
            case R.id.i_music:
                position = 0;
                break;
            case R.id.i_backup:
                position = 1;
                break;
            case R.id.i_favor:
                position = 3;
                break;
            case R.id.i_visibility:
                position = 4;
                break;
            case R.id.i_empty:
                position = 2;
                break;
            default:
                return false;
        }
        Log.e(TAG, "onNavigationItemSelected: " + position);
        if (position != previousPosition) {
            mCurrentInterface.currentPosition(position);
            previousPosition = position;
        }
        return true;
    }
}



