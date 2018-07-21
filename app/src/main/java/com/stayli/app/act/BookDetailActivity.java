package com.stayli.app.act;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.stayli.app.R;
import com.stayli.app.adapter.recycle.BaseRecycleAdapter;
import com.stayli.app.adapter.recycle.BaseViewHolder;
import com.stayli.app.api.DBInterface;
import com.stayli.app.api.NetAPIManager;
import com.stayli.app.base.BaseActivity;
import com.stayli.app.domain.DBBookCollection;
import com.stayli.app.domain.TagBean;
import com.stayli.app.helper.ToolbarColorizeHelper;
import com.stayli.app.utils.PaletteUtil;
import com.stayli.app.utils.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created  on 16/8/10.
 */
public class BookDetailActivity extends BaseActivity {

    public static final String EXTRA_INDEX = "index";

    private CollapsingToolbarLayout mToolbarCtl;
    private ImageView mHeaderIv;
    private RecyclerView mContentRv;
    private static final String TAG = "BookDetailActivity";
    private LinearLayout mLayout;
    private String mBookId;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        mToolbarCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        mHeaderIv = (ImageView) findViewById(R.id.iv_header);
        mContentRv = findViewById(R.id.sv_content);

        mLayout = (LinearLayout) findViewById(R.id.ll_layout);
        ViewCompat.setNestedScrollingEnabled(mContentRv, true);


        final DBBookCollection.CollectionsBean.BookBean index = getIntent().getParcelableExtra(EXTRA_INDEX);
        Glide.with(this).asBitmap().load(index.images.large)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        mHeaderIv.setImageBitmap(resource);
                        mToolbarCtl.setTitle("");
                        palette(resource);
                    }
                });
        mBookId = index.id;
        Log.e(TAG, "onCreate: " + mBookId);
        Log.e(TAG, "onCreate: " + index.binding);
        Log.e(TAG, "onCreate: " + index.authorIntro);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if (state == State.COLLAPSED) {
                    mToolbarCtl.setTitle(index.title);
                }
                if (state == State.EXPANDED) {
                    mToolbarCtl.setTitle("致");
                }
                if (state == State.IDLE) {
                    mToolbarCtl.setTitle("");
                }
            }
        });

        // 书籍详情
        initContentRv();
    }

    private static final int MAX = 9;

    private void initContentRv() {
        final List<TagBean> tagBeanList = new ArrayList<>();
        initData(tagBeanList);

        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (tagBeanList.get(position).getTag_name().length() > MAX)
                    return 2;
                return 1;
            }
        });


        mContentRv.setLayoutManager(layoutManage);


        BookAdapter adapter = new BookAdapter(Util.getApplication(), tagBeanList, R.layout.tag_layout);
        mContentRv.setAdapter(adapter);

    }


    class BookAdapter extends BaseRecycleAdapter<TagBean, BookViewHolder> {
        private boolean isSelected = false;
        private List<TagBean> selectList;

        public BookAdapter(Context context, List<TagBean> mDatas, int layoutId) {
            super(context, mDatas, layoutId);
            selectList = new ArrayList<>();
        }

        @Override
        protected BookViewHolder createViewHolder(View itemView) {

            return new BookViewHolder(itemView);
        }

        @Override
        protected void listIsEmpty() {

        }

        @Override
        protected void convert(final BookViewHolder holder, final TagBean tagBean, int position) {
            holder.mTag.setText(tagBean.getTag_name());
            holder.mTag.setTag(tagBean);
            holder.mTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelected = !holder.mTag.isSelected();
                    if (isSelected) {
                        holder.mTag.setSelected(true);
                        holder.mTag.setBackgroundResource(R.drawable.tag_checked_bg);
                        selectList.add(tagBean);
                        String tag_id = tagBean.getTag_id();
                        if ("25".equals(tag_id)) {
                            upDateBook();
                        }
                        if ("26".equals(tag_id)) {
                            getAnn();
                        }
                    } else {
                        holder.mTag.setSelected(false);
                        holder.mTag.setBackgroundResource(R.drawable.tag_normal_bg);
                        selectList.remove(tagBean);
                    }
                }
            });
        }
    }

    private void upDateBook() {

        DBInterface dbInterface = NetAPIManager.getInstance().create(DBInterface.class);
        final Call<ResponseBody> reading = dbInterface.putBookCollection(mBookId, "reading");
        reading.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
                Log.e(TAG, "onResponse: " + response);
//                    Log.e(TAG, "onResponse: " + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getAnn() {
        DBInterface dbInterface = NetAPIManager.getInstance().create(DBInterface.class);
        Call<ResponseBody> bookAnnotations = dbInterface.getBookAnnotations(mBookId);
        bookAnnotations.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG, "onResponse: " + response);
                    String str = response.body().string();
                    int strLength = str.length();
                    int start = 0;
                    int end = 2000;
                    for (int i = 0; i < 100; i++) {
                        //剩下的文本还是大于规定长度则继续重复截取并输出
                        if (strLength > end) {
                            Log.e(TAG + i, str.substring(start, end));
                            start = end;
                            end = end + 2000;
                        } else {
                            Log.e(TAG, str.substring(start, strLength));
                            break;
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    class BookViewHolder extends BaseViewHolder {

        public TextView mTag;

        public BookViewHolder(View itemView) {
            super(itemView);
            mTag = getView(R.id.tag_tv);
        }
    }

    private void initData(List<TagBean> tagBeanList) {
        tagBeanList.add(new TagBean("1", "准时"));
        tagBeanList.add(new TagBean("2", "非常绅士"));
        tagBeanList.add(new TagBean("3", "非常有礼貌"));
        tagBeanList.add(new TagBean("4", "很会照顾女生"));
        tagBeanList.add(new TagBean("5", "我的男神是个大暖男哦"));
        tagBeanList.add(new TagBean("6", "谈吐优雅"));
        tagBeanList.add(new TagBean("7", "送我到楼下"));
        tagBeanList.add(new TagBean("8", "准时"));
        tagBeanList.add(new TagBean("9", "迟到"));
        tagBeanList.add(new TagBean("10", "态度恶劣"));
        tagBeanList.add(new TagBean("11", "有不礼貌行为"));
        tagBeanList.add(new TagBean("12", "有侮辱性语言有暴力倾向"));
        tagBeanList.add(new TagBean("13", "人身攻击"));
        tagBeanList.add(new TagBean("14", "临时改变行程"));
        tagBeanList.add(new TagBean("15", "非常绅士"));
        tagBeanList.add(new TagBean("16", "非常有礼貌"));
        tagBeanList.add(new TagBean("17", "很会照顾女生"));
        tagBeanList.add(new TagBean("18", "我的男神是个大暖男哦"));
        tagBeanList.add(new TagBean("19", "谈吐优雅"));
        tagBeanList.add(new TagBean("20", "送我到楼下"));
        tagBeanList.add(new TagBean("21", "迟到"));
        tagBeanList.add(new TagBean("22", "态度恶劣"));
        tagBeanList.add(new TagBean("23", "有不礼貌行为"));
        tagBeanList.add(new TagBean("24", "有侮辱性语言有暴力倾向"));
        tagBeanList.add(new TagBean("25", "更新收藏"));
        tagBeanList.add(new TagBean("26", "获取笔记"));
        tagBeanList.add(new TagBean("27", "客户迟到并无理要求延长约会时间"));
    }


    private void palette(Bitmap bitmap) {

        PaletteUtil.getInstance().init(bitmap, new PaletteUtil.PaletteCallBack() {
            @Override
            public String PaletteType() {
                return PaletteUtil.VIBRANT_SWATCH;
            }

            @Override
            public void onCallBack(Palette palette, Palette.Swatch swatch, int rgb) {

                int color = palette.getDominantColor(rgb);
                int colorDark = palette.getDarkMutedColor(color);
                int titleTextColor = swatch.getTitleTextColor();

                Log.e(TAG, "onGenerated: " + color);
                Log.e(TAG, "onGenerated: " + colorDark);
                Log.e(TAG, "onGenerated: " + titleTextColor);

                mToolbarCtl.setContentScrimColor(colorDark);
                mToolbarCtl.setBackgroundColor(colorDark);
                mToolbarCtl.setStatusBarScrimColor(colorDark);

                mToolbarCtl.setCollapsedTitleTextColor(titleTextColor);
                mToolbarCtl.setExpandedTitleColor(titleTextColor);

                ToolbarColorizeHelper.colorizeToolbar(mToolbarTb, titleTextColor, BookDetailActivity.this);


            }
        });


//        Palette.from(bitmap)
//                .generate(new Palette.PaletteAsyncListener() {
//                    @Override
//                    public void onGenerated(Palette palette) {
//                        for (Palette.Swatch swatch : palette.getSwatches()) {
//                            Log.e(TAG, "onGenerated: " + swatch);
//                        }
//
//                        //1.活力颜色
//                        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//                        // 亮的活力颜色
//                        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
//                        // 暗的活力颜色
//                        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
//                        //柔色
//                        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
//                        //亮的柔色
//                        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
//                        //暗的柔色
//                        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
//
//                        // 优势样本
//                        Palette.Swatch dominantSwatch = palette.getDominantSwatch();
//
//
//                        Log.e(TAG, "onGenerated: " + palette.getSwatches().size());
//                        Log.e(TAG, "onGenerated: " + vibrantSwatch);
//                        Log.e(TAG, "onGenerated: " + dominantSwatch);
//                        Log.e(TAG, "onGenerated: " + mutedSwatch);
//                        Log.e(TAG, "onGenerated: " + lightVibrantSwatch);
//                        Log.e(TAG, "onGenerated: " + lightMutedSwatch);
//                        Log.e(TAG, "onGenerated: " + darkVibrantSwatch);
//                        Log.e(TAG, "onGenerated: " + darkMutedSwatch);
//
//
//                        int rgb = palette.getSwatches().get(0).getRgb();
//
//                        int color = palette.getDominantColor(rgb);
//                        int colorDark = palette.getDarkMutedColor(color);
//                        int titleTextColor = palette.getDominantSwatch().getTitleTextColor();
//                        Log.e(TAG, "onGenerated: " + color);
//                        Log.e(TAG, "onGenerated: " + colorDark);
//                        Log.e(TAG, "onGenerated: " + titleTextColor);
//                        mToolbarCtl.setContentScrimColor(color);
//                        mToolbarCtl.setBackgroundColor(color);
//                        mToolbarCtl.setStatusBarScrimColor(color);
//                        mToolbarCtl.setCollapsedTitleTextColor(titleTextColor);
//                        mToolbarCtl.setExpandedTitleColor(titleTextColor);
//                        ToolbarColorizeHelper.colorizeToolbar(mToolbarTb, titleTextColor, BookDetailActivity.this);
//
//                    }
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }


    abstract static class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

        public enum State {
            EXPANDED,
            COLLAPSED,
            IDLE
        }

        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    }
}
