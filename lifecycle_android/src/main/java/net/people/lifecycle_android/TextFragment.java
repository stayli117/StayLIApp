package net.people.lifecycle_android;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qingchen.vrmr.mainactivity.AbsViewHolder;
import com.example.qingchen.vrmr.mainactivity.MainActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import net.people.lifecycle_android.database.DbCityBean;
import net.people.lifecycle_android.module.DaggerTextFragmentComponent;
import net.people.lifecycle_android.module.TextViewModel;

import java.util.List;

import javax.inject.Inject;

public class TextFragment extends Fragment {
    @Inject
    public TextViewModel mTextViewModel;
    private TextView mTv;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mTextViewModel = new TextViewModel();

        DaggerTextFragmentComponent.create().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mTextViewModel = ViewModelProviders.of(this).get(TextViewModel.class);


        mTextViewModel.getLiveData().observe(this, new Observer<List<DbCityBean>>() {
            @Override
            public void onChanged(@Nullable List<DbCityBean> dbCityBeans) {
                adapter.clear();
                adapter.addAll(dbCityBeans);

            }
        });
        mTextViewModel.getLiveDataNewByTime().observe(this, new Observer<DbCityBean>() {
            @Override
            public void onChanged(@Nullable DbCityBean bean) {
                Log.e("abc", "onChanged: " + bean);
                if (bean != null)
                    Toast.makeText(getContext(), "" + bean.getCtime(), Toast.LENGTH_SHORT).show();
            }
        });
//        mTextViewModel.getLiveDataAllByTime().observe(this, new Observer<List<DbCityBean>>() {
//            @Override
//            public void onChanged(@Nullable List<DbCityBean> dbCityBeans) {
//                if (dbCityBeans == null)
//                    Toast.makeText(getContext(), "db is null", Toast.LENGTH_SHORT).show();
//                else
//                    for (DbCityBean bean : dbCityBeans) {
//                        Log.e("abc", "onChanged: " + bean.getCtime());
//                    }
//            }
//        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fancy_list_content, container, false);

    }

    public EasyRecyclerView easyRecyclerView;
    private RecyclerArrayAdapter<DbCityBean> adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        easyRecyclerView = view.findViewById(R.id.easy_city);
        FloatingActionButton button = view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "start", Toast.LENGTH_SHORT).show();
                mTextViewModel.start();
            }
        });

        if (adapter == null) {
            adapter = new RecyclerArrayAdapter<DbCityBean>(getActivity()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MmyViewHolder(parent);
                }

                @Override
                public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
                    super.onBindViewHolder(holder, position, payloads);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            };
        }
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        easyRecyclerView.setAdapter(adapter);
    }

    public void setEmptyText(final CharSequence text) {

    }

    public void setContentTv(String content) {
        mTv.setText(content);
    }

    class MmyViewHolder extends AbsViewHolder<DbCityBean> {

        public MmyViewHolder(ViewGroup itemView) {
            super(itemView);
        }

        @Override
        public void setData(DbCityBean data) {
            title.setText(data.getTitle());
            content.setText(data.getCtime());
        }

    }


}
