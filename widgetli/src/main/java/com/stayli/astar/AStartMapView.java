package com.stayli.astar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yhgao on 2018/5/18.
 */

public class AStartMapView extends View {
    private static final String TAG = "AStartMapView";
    // 地图点
    final int[][] dmaps = {
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

    };

    // 地图大小
    int[][] maps = new int[12][15];

    private Paint mPaint;
    private int dw;
    private MapInfo info;

    public AStartMapView(Context context) {
        this(context, null);

    }

    public AStartMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AStartMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        maps = dmaps.clone();
        mPaint = new Paint();
        rect = new Rect();
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }


    private Node start;
    private Node end;

    int count = 1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int v = (int) (x / dw);
                int v1 = (int) (y / dw);
                if (v1 > dw * 15) break;
                if (count < 3) {
                    if (maps[v1][v] != AStar.BAR) {
                        maps[v1][v] = AStar.PATH;
                        if (start == null) {
                            start = new Node(v, v1);
                        } else {
                            end = new Node(v, v1);
                        }

                        count++;
                        invalidate();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    Rect rect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ----> ");
        int width = getWidth();
        dw = width / 8;
        for (int i = 0; i < maps.length; i++) {
            int[] map = maps[i];

            for (int i1 = 0; i1 < map.length; i1++) {
                if (map[i1] == 0) {
                    mPaint.setColor(Color.GREEN);
                } else if (map[i1] == AStar.BAR) {
                    mPaint.setColor(Color.BLACK);
                } else {
                    mPaint.setColor(Color.WHITE);
                }

                rect.left = dw * i1;
                rect.top = i * dw;
                rect.right = dw * (i1 + 1);
                rect.bottom = (i + 1) * dw;
                canvas.drawRect(rect, mPaint);
                mPaint.setColor(Color.RED);
                mPaint.setTextSize(30.0f);
                canvas.drawText(i1 + ":" + i, rect.centerX(), rect.centerY(), mPaint);
                mPaint.setColor(Color.BLUE);
                canvas.drawLine(dw * i1, 0, dw * i1, dw * 15, mPaint);
            }
            mPaint.setColor(Color.BLUE);
            canvas.drawLine(0, dw * i, width, dw * i, mPaint);
        }


    }

    public MapInfo getInfo() {
        if (start == null || end == null) return null;
        info = new MapInfo(maps, maps[0].length, maps.length, start, end);
        return info;
    }

    public void setMap(Coord c) {
        maps[c.y][c.x] = AStar.PATH;
        Log.e(TAG, "setMap: " + c);
        invalidate();
    }

    /**
     * 重置
     */
    public void reset() {
        final int[][] dmaps = {
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

        };

        maps = dmaps;
        count = 1;
        start = null;
        end = null;
        invalidate();
    }


    public void changeMap() {

        final int[][] dmaps = {
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},

        };
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] dmap : dmaps) {
            for (int i : dmap) {
                list.add(i);
            }
        }
        Collections.shuffle(list);
        int index = 0;
        for (int i = 0; i < dmaps.length; i++) {
            int[] dmap = dmaps[i];
            for (int i1 = 0; i1 < dmap.length; i1++) {
                dmaps[i][i1] = list.get(index);
                index++;
            }
        }
        count = 1;
        start = null;
        end = null;
        maps = dmaps;
        invalidate();
    }

}
