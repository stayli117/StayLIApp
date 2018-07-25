package com.stayli.app.tools.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yhgao on 2018/3/6.
 */

public abstract class BaseRecycleAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;

    private SparseArray<View> mViewTypes;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecycleAdapter(Context context, List<T> mDatas, int layoutId) {
        mContext = context;
        this.mDatas = mDatas;
        mLayoutId = layoutId;
        mViewTypes = new SparseArray<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateView(parent);
        mViewTypes.put(viewType, view);
        BaseViewHolder viewHolder = createViewHolder(view);
        setListener(view, viewType, viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemCount() > 0)
            convert((VH) holder, mDatas.get(position), position);
        else listIsEmpty();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected View inflateView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent,false);
    }


    protected abstract BaseViewHolder createViewHolder(View itemView);


    protected abstract void listIsEmpty();

    protected abstract void convert(VH holder, T t, int position);

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        if (position != getItemCount()) {      // 这个判断的意义就是如果移除的是最后一个，就不用管它了，= =whatever，老板还不发工资啊啊啊啊啊啊
            notifyItemRangeChanged(position, getItemCount() - position);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    protected boolean isEnabled(int viewType) {

        return true;
    }


    protected void setListener(View view, int viewType, final BaseViewHolder viewHolder) {
        if (!isEnabled(viewType)) return;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemLongClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }


    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }
}
