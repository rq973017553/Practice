package com.rq.practice.activities.practice;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.rq.practice.R;
import com.rq.practice.activities.MainActivity;
import com.rq.practice.activities.base.BaseActivity;
import com.rq.practice.adapter.PraListViewAdapter;
import com.rq.practice.utils.EasyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rock you
 * @version [1.0.0, 2018.8.31]
 */
public class ListViewPracticeActivity extends BaseActivity {

    private int mTotalHeight;

    private ListView mListView;

    private PraListViewAdapter mAdapter;

    private static int SCROLL_UP_STATE = 0;

    private static int SCROLL_DOWN_STATE = 1;

    @Override
    public int getLayoutID() {
        return R.layout.activity_listview_pra;
    }

    @Override
    public void bindView() {
        mListView = findViewById(R.id.list_view);
    }

    @Override
    public void initData() {
        List<String> listData = new ArrayList<>();
        for (int i = 1; i <= 100; i++){
            listData.add(String.valueOf(i));
        }
        mAdapter = new PraListViewAdapter(ListViewPracticeActivity.this);
        mAdapter.setListData(listData);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(scrollListener);
    }

    private int lvIndext;

    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState){
                case SCROLL_STATE_IDLE:
                    int scrolled = view.getLastVisiblePosition();
                    //滚动后下标大于滚动前 向下滚动了
                    if(scrolled>lvIndext){
                        //scroll = false;
                    }
                    else {
                        //向上滚动了
                        //scroll = true;
                    }
                        EasyLog.v("SCROLL_STATE_IDLE");
                    break;
                case SCROLL_STATE_FLING:
                    EasyLog.v("SCROLL_STATE_FLING");
                    break;
                case SCROLL_STATE_TOUCH_SCROLL:
                    lvIndext=view.getLastVisiblePosition();
                    EasyLog.v("SCROLL_STATE_TOUCH_SCROLL");
                    break;
            }
        }


        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            EasyLog.v("==================================================");
//            EasyLog.v("firstVisibleItem::"+String.valueOf(firstVisibleItem));
//            EasyLog.v("visibleItemCount::"+String.valueOf(visibleItemCount));
//            EasyLog.v("totalItemCount::"+String.valueOf(totalItemCount));
            View childView = view.getChildAt(0);
            View firstVisibleView = view.getChildAt(firstVisibleItem);
            if (childView != null && firstVisibleView != null){
                int top = childView.getTop();
                int height = firstVisibleView.getHeight();
//                EasyLog.v("height::"+height);

                mTotalHeight = mTotalHeight + height + Math.abs(top);
                EasyLog.v("firstVisibleItem::"+firstVisibleItem);
                EasyLog.v("visibleItemCount::"+visibleItemCount);
//                EasyLog.v("mTotalHeight::"+mTotalHeight);
//                EasyLog.v("top::"+top);
//                EasyLog.v("result::"+String.valueOf(Math.abs(top)+firstVisibleItem*height));
            }
        }
    };
}
