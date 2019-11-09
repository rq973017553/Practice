package com.rq.practice.activities.practice;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.adapter.PraListViewAdapter;
import com.rq.practice.utils.EasyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rock you
 * @version [1.0.0, 2018.8.31]
 */
public class ListViewPracticeActivity extends BaseToolBarActivity {

    /**
     * 最后显示的pos
     */
    private int mLastVisiblePosition;

    private int mScrollState;

    private int mTotalHeight;

    private ListView mListView;

    private PraListViewAdapter mAdapter;

    private static int SCROLL_UPWARD_STATE = 0;

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
        for (int i = 1; i <= 100; i++) {
            listData.add(String.valueOf(i));
        }
        mAdapter = new PraListViewAdapter(ListViewPracticeActivity.this);
        mAdapter.setListData(listData);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(scrollListener);
    }

    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case SCROLL_STATE_IDLE:
                    int scrolled = view.getLastVisiblePosition();
                    //滚动后下标大于滚动前 向下滚动了
                    if (scrolled > mLastVisiblePosition) {
                        //scroll = false;
                        mScrollState = SCROLL_DOWN_STATE;
                        EasyLog.v("向下滚动");
                    } else {
                        //向上滚动了
                        //scroll = true;
                        mScrollState = SCROLL_UPWARD_STATE;
                        EasyLog.v("向上滚动");
                    }
//                        EasyLog.v("SCROLL_STATE_IDLE");
                    break;
                case SCROLL_STATE_FLING:
//                    EasyLog.v("SCROLL_STATE_FLING");
                    break;
                case SCROLL_STATE_TOUCH_SCROLL:
                    mLastVisiblePosition = view.getLastVisiblePosition();
                    EasyLog.v("SCROLL_STATE_TOUCH_SCROLL");
                    break;
            }
        }


        @Override
        public void onScroll(AbsListView listView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//            EasyLog.e("==================================================");
//            EasyLog.e("firstVisibleItem::"+firstVisibleItem);
//            EasyLog.e("visibleItemCount::"+visibleItemCount);
//            EasyLog.e("totalItemCount::"+totalItemCount);

            if (listView != null) {
                if (isScrollToTop(listView)) {
                    Toast.makeText(ListViewPracticeActivity.this,
                            "列表到顶了!", Toast.LENGTH_SHORT).show();
                }

                if (isScrollToBottom(listView)) {
                    Toast.makeText(ListViewPracticeActivity.this,
                            "列表到底了!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private boolean isScrollToTop(AbsListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition == 0) {
            View firstView = listView.getChildAt(firstVisiblePosition);
            if (firstView != null && firstView.getTop() == firstView.getPaddingTop()) {
                return true;
            }
        }
        return false;
    }

    private boolean isScrollToBottom(AbsListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        if (lastVisiblePosition == listView.getCount() - 1) {
            View lastView = listView.getChildAt(lastVisiblePosition - firstVisiblePosition);
            if (lastView != null && lastView.getBottom() == listView.getHeight() + lastView.getPaddingBottom()) {
                return true;
            }
        }
        return false;
    }
}
