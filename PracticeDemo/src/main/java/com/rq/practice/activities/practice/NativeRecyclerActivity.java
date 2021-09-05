package com.rq.practice.activities.practice;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.adapter.NativeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NativeRecyclerActivity extends BaseToolBarActivity {

    private RecyclerView nativeRecyclerView;

    private NativeRecyclerAdapter nativeRecyclerAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_native_recycler;
    }

    @Override
    public void bindView() {
        nativeRecyclerView = findViewById(R.id.native_recycler_view);
        nativeRecyclerView.setLayoutManager(
                new LinearLayoutManager(NativeRecyclerActivity.this,
                        LinearLayoutManager.VERTICAL,
                        false));
        nativeRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10, 0, 10, 20);
            }
        });
        nativeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        LinearLayoutManager manager = (LinearLayoutManager)nativeRecyclerView.getLayoutManager();
                        Log.e("scboy", "SCROLL_STATE_IDLE FirstCompletelyVisible "+manager.findFirstCompletelyVisibleItemPosition());
                        Log.e("scboy", "SCROLL_STATE_IDLE LastCompletelyVisible "+manager.findLastCompletelyVisibleItemPosition());
                        Log.e("scboy", "SCROLL_STATE_IDLE FirstVisibleItem "+manager.findFirstVisibleItemPosition());
                        Log.e("scboy", "SCROLL_STATE_IDLE LastVisibleItem "+manager.findLastVisibleItemPosition());
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Log.e("scboy", "SCROLL_STATE_DRAGGING");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Log.e("scboy", "SCROLL_STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            }
        });
    }

    @Override
    public void initData() {
        nativeRecyclerAdapter = new NativeRecyclerAdapter(NativeRecyclerActivity.this);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i ++){
            data.add(String.valueOf(i));
        }
        nativeRecyclerAdapter.setDataList(data);
        nativeRecyclerView.setAdapter(nativeRecyclerAdapter);
    }
}
