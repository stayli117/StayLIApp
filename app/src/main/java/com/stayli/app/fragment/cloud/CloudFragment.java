package com.stayli.app.fragment.cloud;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.stayli.app.R;
import com.stayli.app.act.BookDetailActivity;
import com.stayli.app.api.ApiObserverCallBack;
import com.stayli.app.api.DBInterface;
import com.stayli.app.api.NetAPIManager;
import com.stayli.app.base.BaseFragment;
import com.stayli.app.databinding.FragmentCloudBinding;
import com.stayli.app.domain.DBBookCollection;
import com.stayli.app.utils.Util;
import com.stayli.app.view.recycle.CommonAdapter;
import com.stayli.app.view.recycle.DividerItemDecoration;
import com.stayli.app.view.recycle.MyRecycleView;
import com.stayli.app.view.recycle.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yhgao on 2018/2/10.
 * 豆瓣书籍展示
 */

public class CloudFragment extends BaseFragment {


    private FragmentCloudBinding bind;
    private MyRecycleView<DBBookCollection.CollectionsBean.BookBean> recyclerView;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title  Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CloudFragment newInstance(String title, String param2) {
        CloudFragment t = new CloudFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PARAM2, param2);
        t.setArguments(args);
        return t;
    }


    @Override
    public int onCreateView() {
        return R.layout.fragment_cloud;
    }

    private static final String TAG = "CloudFragment";
    private final List<DBBookCollection.CollectionsBean.BookBean> mCollections = new ArrayList<>();

    @Override
    public void bindData(View view) {
        bind = DataBindingUtil.bind(view);
        recyclerView = bind.rvContent;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setCanMore(false);//禁用加载更多 生效需要用在setAdapter（）之前
        // 下拉 加载 监听器
        recyclerView.setRefreshLoadMoreListener(listener);
        recyclerView.setAdapter(adapter);
        //数据正在请求时加载的布局，同时会关闭下拉刷新
        recyclerView.firstLoadingView("数据加载中");

        recyclerView.setOnItemClickListener(mItemClickListener);
    }


    // 初始化数据
    private void initData(final int start) {
        //获取用户收藏的书籍信息
        DBInterface dbInterface = NetAPIManager.getInstance().create(DBInterface.class);
        Observable<DBBookCollection> jinger = dbInterface.getUser2BookCollections("jinger", String.valueOf(start), String.valueOf(20));
        jinger.subscribeOn(Schedulers.io())
                .map(new Function<DBBookCollection, List<DBBookCollection.CollectionsBean.BookBean>>() {
                    @Override
                    public List<DBBookCollection.CollectionsBean.BookBean> apply(DBBookCollection dbBookCollection) throws Exception {
                        Log.e(TAG, Thread.currentThread().getName() + " apply: " + dbBookCollection.start);
                        Log.e(TAG, Thread.currentThread().getName() + "apply: " + dbBookCollection.count);
                        Log.e(TAG, Thread.currentThread().getName() + "apply: " + dbBookCollection.total);
                        mStart = dbBookCollection.count + dbBookCollection.start;
                        List<DBBookCollection.CollectionsBean.BookBean> bookBeans = new ArrayList<>();
                        List<DBBookCollection.CollectionsBean> collections = dbBookCollection.collections;
                        for (DBBookCollection.CollectionsBean collection : collections) {
                            bookBeans.add(collection.book);
                        }
                        return bookBeans;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserverCallBack<List<DBBookCollection.CollectionsBean.BookBean>>() {
                    @Override
                    public void onSuccess(List<DBBookCollection.CollectionsBean.BookBean> dbBookCollection) {
                        Log.e(TAG, "onSuccess: ");
                        if (mStart == 20) {
                            recyclerView.setDateRefresh(mCollections, dbBookCollection, R.drawable.no_image, "暂无最新数据");
                        } else {
                            recyclerView.setDateLoadMore(mCollections, dbBookCollection);
                        }
                    }

                    @Override
                    public void onFinal() {

                    }


                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                        recyclerView.setDateRefreshErr(R.drawable.no_image, "点击图片刷新");
                    }
                });
    }


    // recycleView 适配器
    private CommonAdapter<DBBookCollection.CollectionsBean.BookBean> adapter = new CommonAdapter<DBBookCollection.CollectionsBean.BookBean>(Util.getApplication(),
            R.layout.cloud_recyclerview_item, mCollections) {
        @Override
        protected void listIsEmpty() {
            Log.e(TAG, "listIsEmpty: ");
        }

        @Override
        protected void convert(ViewHolder holder, DBBookCollection.CollectionsBean.BookBean book, int position) {
            holder.setText(R.id.item_tv_book_id, book.author.toString())
                    .setText(R.id.item_tv_title, book.title)
                    .setText(R.id.item_tv_book_summary, book.summary)
                    .setImageResource(R.id.item_image, book.images.large, CloudFragment.this);

        }

    };
    // 数据加载起始索引值
    private int mStart = 0;
    // 上拉刷新 与 下拉加载更多监听器
    private MyRecycleView.RefreshLoadMoreListener listener = new MyRecycleView.RefreshLoadMoreListener() {
        @Override
        public void onRefresh() {
            Log.e(TAG, "onRefresh: ---- 刷新 0 -20 ");
            initData(0);
        }

        @Override
        public void onLoadMore() {
            Log.e(TAG, "onRefresh: ---- 加载更多 0 -20 " + mStart);
            initData(mStart);
        }
    };

    //条目点击事件
    private MyRecycleView.ItemClickListener mItemClickListener = new MyRecycleView.ItemClickListener() {
        @Override
        public void onClick(View view, RecyclerView.ViewHolder holder, int position) {
            Intent detailIntent = new Intent(getActivity(), BookDetailActivity.class);
            detailIntent.putExtra(BookDetailActivity.EXTRA_INDEX, mCollections.get(position));
            getContext().startActivity(detailIntent);
        }

        @Override
        public void onLongClick(View view, RecyclerView.ViewHolder holder, int position) {

        }
    };



}

