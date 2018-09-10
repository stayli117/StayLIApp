package com.stayli.app.ui.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stayli.app.R;
import com.stayli.app.databinding.ActivityViewAnotherBinding;

import java.util.ArrayList;
import java.util.Collections;

public class ViewAnotherActivity extends AppCompatActivity {
    private static final String TAG = "FanYiActivity";
    protected ActivityViewAnotherBinding bind;

    private ArrayList<Integer> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_view_another);

        for (int i = 0; i < 20; i++) {
            datas.add(i);
        }


        bind.rvAnother.setLayoutManager(new GridLayoutManager(this, 3));

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//拖拽

        new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(dragFlags, dragFlags) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        //滑动事件
                        Collections.swap(datas, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                    }
                }).attachToRecyclerView(bind.rvAnother);

        bind.rvAnother.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                BitmapViewAnother another = new BitmapViewAnother(getApplicationContext());

                ImageView view = new ImageView(getApplicationContext());


                return new RecyclerView.ViewHolder(view) {

                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ImageView view = (ImageView) holder.itemView;
                view.setTag(position);
                int i = datas.get(position) % 2;
                view.setImageResource(i == 0 ? R.mipmap.me_pic_hd : R.mipmap.jing_pic_hd);

            }

            @Override
            public int getItemCount() {
                return datas.size();
            }
        });
    }


}
