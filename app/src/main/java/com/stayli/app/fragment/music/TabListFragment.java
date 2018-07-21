package com.stayli.app.fragment.music;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.stayli.app.R;
import com.stayli.app.act.PaletteDetailActivity;
import com.stayli.app.databsae.DBJieQi;
import com.stayli.app.databsae.DbManager;
import com.stayli.app.utils.DateUtils;

import java.util.List;


/**
 *
 *
 */
public class TabListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String EXTRA_CONTENT = "content";

    public static TabListFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabListFragment tabContentFragment = new TabListFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tab_list, null);
        final ListView mContentLv = contentView.findViewById(R.id.lv_content);
        mContentLv.setOnItemClickListener(this);
        ViewCompat.setNestedScrollingEnabled(mContentLv, true);

        String string = getArguments().getString(EXTRA_CONTENT);

        if (!"电台".equals(string)) {
            mContentLv.setAdapter(new ContentAdapter());
        } else {

            int year = DateUtils.getYear();
            LiveData<List<DBJieQi>> data = DbManager.getInstance().loadYear(String.valueOf(year));

            data.observe(this, new Observer<List<DBJieQi>>() {
                @Override
                public void onChanged(@Nullable List<DBJieQi> dbJieQis) {
                    initDB(mContentLv, dbJieQis);
                }
            });

        }

        return contentView;
    }

    private void initDB(ListView mContentLv, final List<DBJieQi> dbJieQis) {


        ContentAdapter ad = new ContentAdapter() {
            @Override
            public int getCount() {
                return dbJieQis.size();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public DBJieQi getItem(int position) {
                return dbJieQis.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                DBJieQi jieQi = getItem(position);

                ViewHolder viewHolder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_simple_list_2, null);
                    viewHolder = new ViewHolder();
                    viewHolder.coverIv = convertView.findViewById(R.id.iv_cover);
                    viewHolder.tvCover = convertView.findViewById(R.id.tv_title);
                    viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.tvCover.setText(jieQi.getName());
                viewHolder.tvCover.setTextSize(24);
                viewHolder.tvCover.setTextColor(Color.BLUE);

                viewHolder.tvContent.setText(jieQi.getName() + "\n" + "时间：" + jieQi.getJtime());


                ImageView imageView = viewHolder.coverIv;
                if (imageView != null) {
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .circleCrop()
                            .override(1020, 1920)
                            .skipMemoryCache(false)
                            .override(Target.SIZE_ORIGINAL);

                    Glide.with(TabListFragment.this)
                            .load(jieQi.getPic())
                            .apply(options)
                            .into(imageView);
                }


                return convertView;
            }
        };


        mContentLv.setAdapter(ad);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO
    }


    class ViewHolder {
        ImageView coverIv;
        TextView tvCover;
        TextView tvContent;

    }

    private class ContentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.item_simple_list_2, null);
            ImageView coverIv = (ImageView) contentView.findViewById(R.id.iv_cover);
            coverIv.setImageResource(getResources().getIdentifier("ic_palette_0" + position % 4, "mipmap", getActivity().getPackageName()));
            contentView.findViewById(R.id.cv_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(getActivity(), PaletteDetailActivity.class);
                    detailIntent.putExtra(PaletteDetailActivity.EXTRA_INDEX, position);
                    getContext().startActivity(detailIntent);
                }
            });
            return contentView;
        }
    }

}
