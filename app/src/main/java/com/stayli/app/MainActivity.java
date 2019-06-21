package com.stayli.app;

import android.app.admin.DevicePolicyManager;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.stayli.app.base.BaseActivity;
import com.stayli.app.base.BaseFragment;
import com.stayli.app.databinding.ActivityMainBinding;
import com.stayli.app.databinding.LayoutNavHeaderBinding;
import com.stayli.app.listener.BottomItemListener;
import com.stayli.app.listener.FabClickListener;
import com.stayli.app.listener.ViewPagerChangeListener;
import com.stayli.app.model.api.DBInterface;
import com.stayli.app.model.api.LunarJIeQi;
import com.stayli.app.model.api.NetAPIManager;
import com.stayli.app.model.database.DBJieQi;
import com.stayli.app.model.database.DbManager;
import com.stayli.app.model.domain.DBBook;
import com.stayli.app.model.domain.JieQi;
import com.stayli.app.tools.adapter.frag.VpAdapter;
import com.stayli.app.ui.act.ViewAnotherActivity;
import com.stayli.app.ui.bd_fanyi.FanYiActivity;
import com.stayli.app.ui.fragment.cloud.CloudFragment;
import com.stayli.app.ui.fragment.favor.FavorFragment;
import com.stayli.app.ui.fragment.friends.FriendsFragment;
import com.stayli.app.ui.fragment.music.MusicFragment;
import com.stayli.app.ui.fragment.visibility.VisibilityFragment;
import com.stayli.app.ui.shudu.ShuduActivity;
import com.stayli.app.utils.DateUtils;
import com.stayli.app.utils.DrawableUtil;
import com.stayli.app.utils.SPUtils;
import com.stayli.astar.AStartActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";


    private ColorStateList mBackgroundTintList;
    private MusicFragment musicFragment;

    private ArrayList<Fragment> fragmentList;
    private BottomItemListener itemSelectedListener;
    private ViewPagerChangeListener mViewPagerChangeListener;
    private FabClickListener mFabClickListener;

    protected ActivityMainBinding bind;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //底部导航
        initData();
        initView();
        initListener();
        initEvent();
        //测边栏
        initDrawer();
        //头部TAB
        initTab();

        //侧边栏处理

        // 查询节气

        final int year = DateUtils.getYear();
        int year1 = SPUtils.getInstance().getInt("year");
        if (year != year1) {
            LiveData<List<DBJieQi>> liveData = DbManager.getInstance().loadYear(String.valueOf(year));
            liveData.observe(this, new Observer<List<DBJieQi>>() {
                @Override
                public void onChanged(@Nullable List<DBJieQi> dbJieQis) {
                    Log.e(TAG, "onChanged: " + dbJieQis);
                    if (dbJieQis == null || dbJieQis.size() < 1) {
                        getJiqQiApi(year);
                    } else {
                        Toast.makeText(mContext, "查询数据库", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


//        int v = 123456;
//        byte[] bytes = ByteBuffer.allocate(4).putInt(v).array();

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }


    // 初始化侧边栏
    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this,
                bind.dlRoot, R.string.app_name, R.string.app_name);
        bind.dlRoot.setDrawerListener(drawerToggle);

        DrawerLayout.LayoutParams layoutParams =
                (DrawerLayout.LayoutParams) bind.llMenu.getLayoutParams();
        layoutParams.width = getScreenSize()[0] / 4 * 3;


        View headerView = bind.llMenu.inflateHeaderView(R.layout.layout_nav_header);
        bind.llMenu.inflateMenu(R.menu.menu_navigation_drawer);
        LayoutNavHeaderBinding binding = DataBindingUtil.bind(headerView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.me_pic_hd);
        Drawable roundedBitmapDrawable = DrawableUtil.createRoundImageWithBorder(bitmap, DrawableUtil.BORDER_NO);

        binding.ivUserHeader.setImageDrawable(roundedBitmapDrawable);
        bind.llMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_item_aStar:

                        startActivity(new Intent(MainActivity.this, AStartActivity.class));
                        break;

                    case R.id.navigation_item_fanyi:
                        startActivity(new Intent(MainActivity.this, FanYiActivity.class));
                        break;

                    case R.id.navigation_item_lock:
                        lock();
                        break;

                    case R.id.navigation_item_view_another:
                        startActivity(new Intent(MainActivity.this, ViewAnotherActivity.class));
                        break;

                    case R.id.navigation_item_message_sub:
                        startActivity(new Intent(MainActivity.this, ShuduActivity.class));
                        break;
                }
                return false;
            }
        });

    }

    // 锁屏
    private void lock() {
        DevicePolicyManager manager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        if (manager != null) {
            manager.lockNow();
        }


    }


    //初始化音乐界面显示的tab
    private void initTab() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        bind.tlIndicator.setTabMode(TabLayout.MODE_FIXED);
        bind.tlIndicator.setTabTextColors(ContextCompat.getColor(
                this, R.color.gray), ContextCompat.getColor(this,
                R.color.white));
        bind.tlIndicator.setSelectedTabIndicatorColor(
                ContextCompat.getColor(this, R.color.white));

        ViewCompat.setElevation(bind.tlIndicator, 10);

        musicFragment.setIndicator(bind.tlIndicator);
        //判断当前是哪个Fragment 显示不同的tab
        showTab(0);
    }


    // 处理顶部菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    //处理侧边栏
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //处理侧边栏
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("致爱");

        }
    }

    //处理侧边栏
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_notification) {
            Toast.makeText(mContext, "000000", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //主fragment
    private void initData() {
        fragmentList = new ArrayList<>();
        musicFragment = MusicFragment.newInstance(getString(R.string.music), "0");
        CloudFragment cloudFragment = CloudFragment.newInstance(getString(R.string.backup), "1");
        FriendsFragment friendFragment = FriendsFragment.newInstance(getString(R.string.friends), "2");
        FavorFragment favorFragment = FavorFragment.newInstance(getString(R.string.favor), "3");
        VisibilityFragment visibilityFragment = VisibilityFragment.newInstance(getString(R.string.visibility), "4");

        fragmentList.add(musicFragment);
        fragmentList.add(cloudFragment);
        fragmentList.add(friendFragment);
        fragmentList.add(favorFragment);
        fragmentList.add(visibilityFragment);
    }

    //初始化主页面
    private void initView() {
//        bind.bnve.enableAnimation(false);
//        bind.bnve.enableItemShiftingMode(false);
//        bind.bnve.enableShiftingMode(false);
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragmentList);
        bind.vp.setScroll(false);
        bind.vp.setOffscreenPageLimit(4);
        bind.vp.setAdapter(adapter);

    }

    private void initListener() {
        // 获取默认属性
        mBackgroundTintList = bind.fab.getBackgroundTintList();

        itemSelectedListener = new BottomItemListener(new BottomItemListener.CurrentInterface() {
            @Override
            public void currentPosition(int position) {
                bind.vp.setCurrentItem(position, false);
                showTab(position);
                Log.e(TAG, "currentPosition: " + position);
            }
        });

        mViewPagerChangeListener = new ViewPagerChangeListener(new ViewPagerChangeListener.CurrentInterface() {
            @Override
            public void currentNo2Position(int position) {
                //设置底部导航选中
                bind.bnve.setSelectedItemId(position);
                // 恢复悬浮按钮初始化的样式
                bind.fab.setBackgroundTintList(mBackgroundTintList);
            }

            @Override
            public void current2Position() {
                // 中间位置出发点击事件
                bind.fab.performClick();
            }
        });


        mFabClickListener = new FabClickListener(new FabClickListener.Callback() {

            @Override
            public void changeFtbTintList(ColorStateList colorStateList) {
                //修改悬浮按钮样式
                bind.fab.setBackgroundTintList(colorStateList);
            }

            @Override
            public void current2Position() {
                //设置底部导航选中
                bind.bnve.setSelectedItemId(R.id.i_empty);
            }
        });


    }

    private void initEvent() {
        //底部导航监听
        bind.bnve.setOnNavigationItemSelectedListener(itemSelectedListener);
        //view page 页面变化监听
        bind.vp.addOnPageChangeListener(mViewPagerChangeListener);
        //悬浮按钮覆盖中间位置
        bind.fab.setOnClickListener(mFabClickListener);
    }


    // 与Fragment 交互
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void showTab(int position) {
        if (position == 0) { // 音乐界面
            bind.tlIndicator.setVisibility(View.VISIBLE);
        } else {
            bind.tlIndicator.setVisibility(View.GONE);
        }
    }

    //悬浮按钮点击事件
    public void onClickFab(View v) {
//        Snackbar.make(findViewById(R.id.fab_add), "Show The Snackbar", Snackbar.LENGTH_SHORT).show();


//        BDInterface bdInterface =NetAPIManager.getInstance().getRetrofit().create(BDInterface.class);
//        Call<ResponseBody> call = bdInterface.getBlog(3);
//        call.enqueue(new Callback<ResponseBody>() {
//             @Override
//             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                 String text = "null";
//                 try {
//                     text = response.body().string();
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//                 Log.e(TAG, "onResponse: "+text );
//                 Snackbar.make(findViewById(R.id.fab),text,Snackbar.LENGTH_LONG).show();
//             }
//
//             @Override
//             public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//             }
//         });


//        TBInterface tbInterface = NetAPIManager.getInstance().getRetrofit(TBInterface.BASE_URL).create(TBInterface.class);
//        Call<TbProject> tbCall = tbInterface.query("utf-8", "卫衣");
//        tbCall.enqueue(new Callback<TbProject>() {
//            @Override
//            public void onResponse(Call<TbProject> call, Response<TbProject> response) {
//
//                Log.e(TAG, "onResponse: " + response.body().result);
//
//            }
//
//            @Override
//            public void onFailure(Call<TbProject> call, Throwable t) {
//
//            }
//        });


//        DBInterface dbInterface = NetAPIManager.getInstance().getRetrofit(DBInterface.BASE_URL).create(DBInterface.class);
//        String s = "v2/book/1220562";
//        Call<DBBook> dbCall = dbInterface.getTestBook(s);
//        Log.e(TAG, "onClickFab: " + dbCall.request().url().url());
//        dbCall.enqueue(new Callback<DBBook>() {
//            @Override
//            public void onResponse(Call<DBBook> call, Response<DBBook> response) {
//                Log.e(TAG, "onResponse: " + response.body().author);
//            }
//
//            @Override
//            public void onFailure(Call<DBBook> call, Throwable t) {
//
//            }
//        });
    }

    public void getJiqQiApi(final int year) {
        LunarJIeQi ieQi = NetAPIManager.getInstance().create(LunarJIeQi.class);
        Observable<JieQi> jieQi = ieQi.getJieQi(String.valueOf(year));
        jieQi.subscribeOn(Schedulers.io())
                .map(new Function<JieQi, JieQi.ResultBean>() {
                    @Override
                    public JieQi.ResultBean apply(JieQi jieQi) throws Exception {
                        List<JieQi.ResultBean.ListBean> list = jieQi.result.list;

                        List<DBJieQi> qiList = new ArrayList<>();
                        if (list != null) {
                            for (JieQi.ResultBean.ListBean bean : list) {
                                DBJieQi dbJieQi = new DBJieQi();
                                dbJieQi.setCtime(DateUtils.getStringDate());
                                dbJieQi.setJieqiid(bean.jieqiid);
                                dbJieQi.setJtime(bean.time);
                                dbJieQi.setName(bean.name);
                                dbJieQi.setPic(bean.pic);
                                dbJieQi.setYear(String.valueOf(DateUtils.getYear()));
                                qiList.add(dbJieQi);
                            }
                        }
                        if (qiList.size() > 0) {
                            DbManager.getInstance().insertJieQi(qiList);
                            SPUtils.getInstance().put("year", year);
                        }

                        Log.e(TAG, "apply: " + qiList);
                        return jieQi.result;
                    }
                }).subscribe();

    }
}
