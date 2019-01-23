package com.example.qingchen.vrmr.mainactivity;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.qingchen.vrmr.DataBase.NewsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import net.people.lifecycle_android.R;

/**
 */


public abstract class AbsViewHolder<M> extends BaseViewHolder<M> {

    public ImageView imageView;
    public TextView title;
    public TextView content;

    public AbsViewHolder(ViewGroup itemView) {
        this(itemView, R.layout.mainactivity_info_item);
    }

    public AbsViewHolder(ViewGroup parent, int res) {
        super(parent, res);
        imageView = $(R.id.imageView);
        title = $(R.id.textView);
        content = $(R.id.textView2);
    }

    @Override
    public abstract void setData(M data);

    public static class MyViewHolder extends AbsViewHolder<NewsBean> {

        public MyViewHolder(ViewGroup itemView) {
            super(itemView);
        }


        @Override
        public void setData(NewsBean newsBean) {


//        Glide.with(getContext()).load(newsBean.getPicUrl())
//
//                .placeholder(R.drawable.ic_launcher_background).centerCrop().into(imageView);


            Glide.with(getContext()).load(newsBean.getPicUrl())
                    .apply(RequestOptions
                            .placeholderOf(R.drawable.ic_launcher_background)
                            .centerCrop())
                    .into(imageView);
            title.setText(newsBean.getTitle());
            content.setText(newsBean.getCtime());
        }


    }
}