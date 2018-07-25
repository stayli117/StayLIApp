package com.stayli.app.ui.fragment.favor;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.stayli.app.R;
import com.stayli.app.model.api.ApiObserverCallBack;
import com.stayli.app.model.api.KDInterface;
import com.stayli.app.model.api.NetAPIManager;
import com.stayli.app.base.BaseFragment;
import com.stayli.app.databinding.FragmentFavBinding;
import com.stayli.app.model.domain.KDBean;
import com.stayli.app.model.domain.KDInfoBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yhgao on 2018/2/10.
 * <p>
 * 快递查询
 */

public class FavorFragment extends BaseFragment {

    FragmentFavBinding bind;
    private KDInterface mKdInterface;
    private ArrayAdapter<String> adapter;
    private EditText tilPassword;
    private ClickObserver<KDBean> mClickObserver;

    public FavorFragment() {
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
    public static FavorFragment newInstance(String title, String param2) {
        FavorFragment t = new FavorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PARAM2, param2);
        t.setArguments(args);
        return t;
    }

    @Override
    public int onCreateView() {
        return R.layout.fragment_fav;
    }

    @Override
    public void bindData(View view) {
        bind = DataBindingUtil.bind(view);
        mKdInterface = NetAPIManager.getInstance().create(KDInterface.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.item_kd_com);
        bind.spCom.setAdapter(adapter);
        mClickObserver = new ClickObserver<>();
        bind.spCom.setOnItemSelectedListener(mClickObserver);
        // 请求可查询的快递公司
        request(adapter);
        tilPassword = bind.tilPassword.getEditText();
        if (tilPassword == null) {
            Toast.makeText(getContext(), "错误的输入框", Toast.LENGTH_SHORT).show();
            return;
        }
        tilPassword.setText("NF29863920144");
        bind.btnCommit.setOnClickListener(mClickObserver);

    }

    //请求可查询的快递公司
    private void request(final ArrayAdapter<String> adapter) {
        Observable<KDBean> kdno = mKdInterface.getKDNO("5e301e043c702575280f5443c71fd5e5");
        kdno.flatMap(new Function<KDBean, ClickObserver<KDBean>>() {
            @Override
            public ClickObserver<KDBean> apply(KDBean bean) throws Exception {
                mClickObserver.setBean(bean);
                return mClickObserver;
            }
        }).map(new Function<KDBean, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(KDBean bean) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                list.add("请选择快递公司");
                List<KDBean.ResultBean> result = bean.result;
                if (result != null)
                    for (KDBean.ResultBean resultBean : result) {
                        list.add(resultBean.com);
                    }
                return list;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserverCallBack<ArrayList<String>>() {
                    @Override
                    public void onSuccess(ArrayList<String> bean) {
                        if (bean.size() < 1) {
                            Toast.makeText(getContext(), "快递公司查询失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        adapter.addAll(bean);
                    }

                    @Override
                    public void onFinal() {
                        Toast.makeText(getContext(), "查询快递公司完毕", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    class ClickObserver<T extends KDBean> extends Observable<T> implements AdapterView.OnItemSelectedListener, View.OnClickListener {

        private T mBean;
        private KDBean.ResultBean resultBean;
        private int mPosition;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mPosition = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onClick(View v) {
            String num = tilPassword.getText().toString();
            if (mPosition < 1 || TextUtils.isEmpty(num)) {
                Toast.makeText(getContext(), "请选择快递公司 或 检查订单号", Toast.LENGTH_SHORT).show();
                return;
            }
            mPosition = mPosition - 1;
            resultBean = mBean.result.get(mPosition);
            String com = resultBean.no;
            final Observable<KDInfoBean> kdInfo = mKdInterface.getKDInfo("5e301e043c702575280f5443c71fd5e5",
                    com, num, "json");

            kdInfo.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiObserverCallBack<KDInfoBean>() {
                        @Override
                        public void onSuccess(KDInfoBean kdInfoBean) {
                            Menu menu = bind.navigationMenu.getMenu();
                            menu.add(kdInfoBean.reason);
                            if (!"200".equals(kdInfoBean.resultcode)) {
                                menu.add("错误码" + kdInfoBean.errorCode);
                                return;
                            }

                            if (!"0".equals(kdInfoBean.result.status)) {
                                menu.add("查询失败 \n 状态码 " + kdInfoBean.result.status);
                                return;
                            }

                            menu.add("快递公司：" + kdInfoBean.result.company);
                            menu.add("编号：" + kdInfoBean.result.com);
                            menu.add("订单号：" + kdInfoBean.result.no);

                            List<KDInfoBean.ResultBean.ListBean> list = kdInfoBean.result.list;
                            for (KDInfoBean.ResultBean.ListBean bean : list) {
                                menu.add("时间: " + bean.datetime);
                                menu.add("信息: " + bean.remark);
                                menu.add("zone: " + bean.zone);
                            }

                        }

                        @Override
                        public void onFinal() {

                        }
                    });

            kdInfo.map(new Function<KDInfoBean, String>() {
                @Override
                public String apply(KDInfoBean kdInfoBean) throws Exception {
                    Log.e("KD", "apply: " + kdInfoBean.reason);
                    StringBuilder sb = new StringBuilder();
                    List<KDInfoBean.ResultBean.ListBean> list = kdInfoBean.result.list;
                    if (list == null || list.size() < 1) {
                        sb.append("错误原因：").append(kdInfoBean.reason);
                        return sb.toString();
                    }

                    for (KDInfoBean.ResultBean.ListBean resultBean : list) {
                        sb.append("时间：").append(resultBean.datetime).append("\n")
                                .append("信息: ").append(resultBean.remark).append("\n")
                                .append("zone").append(resultBean.zone).append("\n");
                    }
                    return sb.toString();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiObserverCallBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Log.e("sd", "onSuccess: " + s);
                        }

                        @Override
                        public void onFinal() {

                        }
                    });
        }

        @Override
        protected void subscribeActual(Observer<? super T> observer) {
            observer.onNext(mBean);
            observer.onComplete();
        }

        public void setBean(T bean) {
            this.mBean = bean;
        }
    }
}
