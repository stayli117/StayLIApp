package com.stayli.app.ui.fragment.friends;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.stayli.app.R;
import com.stayli.app.base.BaseFragment;
import com.stayli.app.databinding.FragmentFriendsBinding;
import com.stayli.app.ui.bd_fanyi.FanYiActivity;
import com.stayli.app.ui.face.FaceActivity;
import com.stayli.app.ui.qrcode.AliCaptureActivity;
import com.stayli.app.ui.qrcode.DefaultCaptureActivity;
import com.stayli.app.ui.qrcode.WeChatCaptureActivity;

/**
 * Created by yhgao on 2018/2/10.
 * <p>
 * 二维码扫描
 */

public class FriendsFragment extends BaseFragment {

    public FriendsFragment() {
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title  Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String title, String param2) {
        FriendsFragment t = new FriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PARAM2, param2);
        t.setArguments(args);
        return t;
    }

    @Override
    public int onCreateView() {
        return R.layout.fragment_friends;
    }

    @Override
    public void bindData(View view) {
        FragmentFriendsBinding binding = DataBindingUtil.bind(view);
        binding.defaultStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DefaultCaptureActivity.class);
                startActivity(intent);
            }
        });

        binding.wechatStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeChatCaptureActivity.class);
                startActivity(intent);
            }
        });

        binding.alipayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AliCaptureActivity.class);
                startActivity(intent);
            }
        });
        binding.baiduFanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FanYiActivity.class);
                startActivity(intent);
            }
        });
        binding.tententFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FaceActivity.class);
                startActivity(intent);
            }
        });
    }


}
