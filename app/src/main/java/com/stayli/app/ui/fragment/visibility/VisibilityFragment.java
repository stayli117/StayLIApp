package com.stayli.app.ui.fragment.visibility;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stayli.app.R;
import com.stayli.app.base.BaseFragment;
import com.stayli.app.databinding.FragmentVisBinding;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Created by yhgao on 2018/2/10.
 */

public class VisibilityFragment extends BaseFragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title  Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VisibilityFragment newInstance(String title, String param2) {

        VisibilityFragment t = new VisibilityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PARAM2, param2);
        t.setArguments(args);
        return t;
    }

    @Override
    public int onCreateView() {
        return R.layout.fragment_vis;
    }

    @Override
    public void bindData(final View view) {
        FragmentVisBinding binding = DataBindingUtil.bind(view);

        //获取所以的第三方App

//        byte[] s = Base64.decode("为什么/没有出现" +
//                "bad base64呢 " +
//                "为什么呢 哎吆喂 ADSJBS NK877 \t G\nGS", Base64.DEFAULT);
//
//        Log.e(TAG, "bindData: "+ new String(s));
//        String bytes = Base64.encodeToString(s, Base64.DEFAULT);
//        Log.e(TAG, "bindData: "+new String(bytes) );
//        String decode = Base64.encodeToString(bytes.getBytes(), Base64.NO_WRAP);
//        Log.e(TAG, "bindData: "+decode );


//        try {
//            getAllThirdApps();
//        } catch (PackageManager.NameNotFoundException | IOException e) {
//            e.printStackTrace();
//        }


        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "btn1", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "btn2", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "btn1 long", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        binding.btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "btn2 long", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    private static final String TAG = "VisibilityFragment";

    public void getAllThirdApps() throws PackageManager.NameNotFoundException, IOException {
        PackageManager manager = getContext().getPackageManager();
        List<PackageInfo> infoList = manager.getInstalledPackages(0);
        for (PackageInfo packageInfo : infoList) {
            String packageName = packageInfo.packageName;
//
            //判断是否为系统预装的应用
            if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) <= 0) {
                // 第三方应用
                String label = packageInfo.applicationInfo.loadLabel(manager).toString();
                Log.e(TAG, "AppName: " + label);
                Log.e(TAG, "getAllThirdApps: " + packageName);
                Log.e(TAG, "getAllThirdApps: " + packageInfo.sharedUserId);
                Log.e(TAG, "getAllThirdApps: " + packageInfo.toString());
                Log.e(TAG, "getAllThirdApps: " + packageInfo.versionName);
                Log.e(TAG, "getAllThirdApps: " + packageInfo.firstInstallTime);
                Log.e(TAG, "getAllThirdApps: " + packageInfo.lastUpdateTime);
                Log.e(TAG, "getAllThirdApps: " + packageInfo.versionCode);

                String path = manager.getApplicationInfo(packageName, 0).sourceDir;
                DexFile dexFile = new DexFile(path);// get dex file of APK
                Enumeration<String> entries = dexFile.entries();
                while (entries.hasMoreElements()) {// travel all classes
                    String className = entries.nextElement();
                    Log.e(TAG, "getAllThirdApps: " + className);
                }
            } else {
                //系统应用
            }

        }


    }
}
