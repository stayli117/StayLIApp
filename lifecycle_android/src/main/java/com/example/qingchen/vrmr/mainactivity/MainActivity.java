package com.example.qingchen.vrmr.mainactivity;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.qingchen.vrmr.DataBase.NewsBean;
import com.example.qingchen.vrmr.TestActivity;
import com.example.qingchen.vrmr.mainactivity.module.DaggerMainActivityComponent;
import com.example.qingchen.vrmr.mainactivity.module.ProfileViewModel;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import net.people.lifecycle_android.R;

import java.util.List;

import javax.inject.Inject;

/**
 *
 */

public class MainActivity extends FragmentActivity {

    @Inject
    public ProfileViewModel viewModel;
    public EasyRecyclerView easyRecyclerView;
    private RecyclerArrayAdapter<NewsBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivityComponent.create().inject(this);

        viewModel.getInfos().observe(this, new Observer<List<NewsBean>>() {
            @Override
            public void onChanged(@Nullable List<NewsBean> infoBean) {
                adapter.clear();
                adapter.addAll(infoBean);
                Log.e("abc", "onChanged: " + infoBean.toString());
            }
        });
        if (adapter == null) {
            adapter = new RecyclerArrayAdapter<NewsBean>(this) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new AbsViewHolder.MyViewHolder(parent);
                }

                @Override
                public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position, @NonNull final List<Object> payloads) {
                    super.onBindViewHolder(holder, position, payloads);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (position==0){
                                AlertDialog alertDialog = showMultiBtnDialog();
                            }else {
                                startActivity(new Intent(MainActivity.this, TestActivity.class));
//                                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//                                    //切换竖屏
//                                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                                } else {
//                                    //切换横屏
//                                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                                }
                            }
                        }
                    });
                }
            };
        }

        easyRecyclerView = findViewById(R.id.easy);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        easyRecyclerView.setAdapter(adapter);
    }


    private AlertDialog showMultiBtnDialog() {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("我是一个普通Dialog").setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("按钮1",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNeutralButton("按钮2",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNegativeButton("切换屏幕", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    //切换竖屏
                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    //切换横屏
                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
        // 创建实例并显示
        AlertDialog show = normalDialog.show();
        return show;
    }
}
