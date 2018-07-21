package com.stayli.app.fragment.music;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stayli.app.R;
import com.stayli.app.adapter.recycle.BaseRecycleAdapter;
import com.stayli.app.adapter.recycle.BaseViewHolder;
import com.stayli.app.databinding.FragmentTabGupiaoBinding;
import com.stayli.app.domain.JiJBean;
import com.stayli.app.fragment.music.gupiao.vm.GuPiaoViewM;

import java.util.ArrayList;


/**
 *
 *
 */
public class GuPiaoFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";
    FragmentTabGupiaoBinding mDataBinding;
    GuPiaoViewM mGuPiaoViewM;

    private ArrayList<String> jiJBeans;

    public static GuPiaoFragment newInstance(String title) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, title);
        GuPiaoFragment tabContentFragment = new GuPiaoFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mGuPiaoViewM = ViewModelProviders.of(getActivity()).get(GuPiaoViewM.class);
        jiJBeans = new ArrayList<>();
        JiJBean jBean = new JiJBean();
        jBean.name = "嘉实新消费股票";
        jiJBeans.add("嘉实新消费股票");
        jiJBeans.add("嘉实沪港深精选股票");
        jiJBeans.add("嘉实价值精选股票");
        jiJBeans.add("广发稳健增长混合");
        jiJBeans.add("汇添富蓝筹稳健混合");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_gupiao, container, false);
        if (arguments == null) return mDataBinding.getRoot();
        String title = arguments.getString(EXTRA_CONTENT);

        ViewCompat.setNestedScrollingEnabled(mDataBinding.rvContent, true);

        initView();
        return mDataBinding.getRoot();
    }

    class GPViewHolder extends BaseViewHolder {

        public GPViewHolder(View itemView) {
            super(itemView);
        }

    }

    private void initView() {
        mDataBinding.rvContent.setAdapter(new BaseRecycleAdapter<String, GPViewHolder>(getContext(), jiJBeans
                , R.layout.item_simple_list_2) {
            @Override
            protected BaseViewHolder createViewHolder(View itemView) {
                GPViewHolder holder = new GPViewHolder(itemView);

                return holder;
            }

            @Override
            protected void listIsEmpty() {

            }

            @Override
            protected void convert(GPViewHolder holder, String s, int position) {

            }


        });
    }

}
