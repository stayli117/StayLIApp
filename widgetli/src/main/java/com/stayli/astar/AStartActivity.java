package com.stayli.astar;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.stayli.pb.PairCircleProgressBar;
import com.stayli.widget.R;

import java.util.Stack;

public class AStartActivity extends AppCompatActivity {


    static Stack<Coord> path;

    MyHandler myHandler = new MyHandler();
    private static AStartMapView mapView;
    private Button start;
    private Button reset;
    private Button c_map;
    private Button calc;
    private WindowManager mWM;
    private PairCircleProgressBar mPCbar;
    private WindowManager.LayoutParams wl;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View obj = (View) msg.obj;
            if (path.isEmpty()) {
                reset.setEnabled(true);
                c_map.setEnabled(true);
                Toast.makeText(AStartActivity.this, "路径完成", Toast.LENGTH_SHORT).show();
                mPCbar.cancleAnimation();
                mPCbar.endAnimation();
                mWM.removeView(mPCbar);
                return;
            }
            Coord poll = path.pop();
            if (poll != null) {
                mapView.setMap(poll);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astart);

        mapView = findViewById(R.id.asmv);
        start = findViewById(R.id.btn_start);
        reset = findViewById(R.id.btn_reset);
        c_map = findViewById(R.id.btn_c_map);
        calc = findViewById(R.id.btn_calc);
        mWM = getWindow().getWindowManager();
        mPCbar = new PairCircleProgressBar(this);
        mPCbar.setBackgroundColor(Color.WHITE);
        wl = new WindowManager.LayoutParams();
        Point point = new Point();
        mWM.getDefaultDisplay().getRealSize(point);
        wl.height = point.y / 3;
        wl.width = point.x * 9 / 10;
        wl.alpha = 0.5f;
        wl.gravity = Gravity.CENTER;
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapInfo info = mapView.getInfo();
                if (info == null) return;
                mWM.addView(mPCbar, wl);
                mPCbar.startAnimation();
                path = new Stack<>();
                reset.setEnabled(false);
                c_map.setEnabled(false);
                calc.setEnabled(false);
                start.setEnabled(false);
                new AStar(new AStar.IPath() {
                    @Override
                    public void onStep(Coord coord) {
                        path.push(coord);
                        Log.e("abc", "run: " + path.size());
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(AStartActivity.this, "计算完成", Toast.LENGTH_SHORT).show();
                        start.setEnabled(true);
                        mPCbar.cancleAnimation();
                        mPCbar.endAnimation();
                        mWM.removeView(mPCbar);
                    }
                }).start(info);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                reset.setEnabled(false);
                c_map.setEnabled(false);
                calc.setEnabled(false);
                start.setEnabled(false);
                mWM.addView(mPCbar, wl);
                mPCbar.startAnimation();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("abc", "run: " + path.isEmpty());
                        path.pop();// 第一个路径不需要再次绘制

                        while (!path.empty() && path.size() > 1) {
                            try {
                                Message msg = Message.obtain();
                                msg.obj = v;
                                Thread.sleep(1000);
                                myHandler.sendMessage(msg);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        path.pop();
                        myHandler.sendEmptyMessage(0);
                    }
                }).start();

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setEnabled(true);
                mapView.reset();
            }
        });
        c_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setEnabled(true);
                mapView.changeMap();
            }
        });

    }
}
