package com.stayli.app.tools.adapter.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yhgao on 2018/3/6.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    private View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        initView();
    }


    protected  <T extends View> T getView(int resId) {
        View view = mItemView.findViewById(resId);
        return (T) view;
    }

    private final void initView() {

    }
}
