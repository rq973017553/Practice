package com.rq.practice.activities.practice;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.adapter.RecyclerListAdapter;
import com.rq.practice.adapter.base.IAdapterChildClickListener;
import com.rq.practice.bean.RecyclerListBean;
import com.rq.practice.utils.HandlerUtils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPracticeActivity extends BaseToolBarActivity {

    private RecyclerView mRecyclerView;

    private RecyclerListAdapter mRecyclerListAdapter;

    private int LIST_SIZE = 100;

    private TestScrollHandler testScrollHandler;

    private Button testSmoothScrollToPosition;
    private Button testSmoothScrollBy;
    private Button testScrollToPositionWithOffset;
    private Button closeLoopScroll;
    private EditText recyclerEdit;

    private boolean switch_close_scroll = true;

    private LinearLayoutManager layoutManager;

    @Override
    public int getLayoutID() {
        return R.layout.activity_recyclerview_pra;
    }

    @Override
    public void bindView() {
        recyclerEdit = findViewById(R.id.recycler_edit);
        closeLoopScroll = findViewById(R.id.close_loop_scroll);
        testSmoothScrollToPosition = findViewById(R.id.test_smooth_scroll_to_position);
        testSmoothScrollBy = findViewById(R.id.test_smooth_scroll_by);
        testScrollToPositionWithOffset = findViewById(R.id.test_scroll_to_position_with_offset);

        testSmoothScrollToPosition.setOnClickListener(clickListener);
        testSmoothScrollBy.setOnClickListener(clickListener);
        testScrollToPositionWithOffset.setOnClickListener(clickListener);
        closeLoopScroll.setOnClickListener(clickListener);

        closeLoopScroll.setText("关闭循环滚动");

        mRecyclerView = findViewById(R.id.recycler_view);

        // 设置RecyclerView列表形式
        layoutManager = new LinearLayoutManager(RecyclerViewPracticeActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void initData() {
        List<RecyclerListBean> listData = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i ++){
            RecyclerListBean bean = new RecyclerListBean();
            bean.setText("第"+i+"项");
            listData.add(bean);
        }
        mRecyclerListAdapter = new RecyclerListAdapter(RecyclerViewPracticeActivity.this,
                listData);
        mRecyclerView.setAdapter(mRecyclerListAdapter);
        mRecyclerListAdapter.setItemClickListener(itemClickListener);
        mRecyclerListAdapter.setItemChildClickListener(itemChildClickListener);
        // https://blog.csdn.net/yancychas/article/details/77483095
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        EasyLog.e("拖动中!");
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
//                        EasyLog.e("静止中!");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
//                        EasyLog.e("滚动中!");
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isScrollToTop(recyclerView)){
                    Toast.makeText(RecyclerViewPracticeActivity.this,
                            "列表到顶了!", Toast.LENGTH_SHORT).show();
                }else if (isScrollToBottom(recyclerView)){
                    Toast.makeText(RecyclerViewPracticeActivity.this,
                            "列表到底了!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        testScrollHandler = new TestScrollHandler(mRecyclerView);
    }

    private boolean isScrollToBottom(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null){
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null){
                if (lastVisiblePosition == adapter.getItemCount()-1){
                    View lastView = recyclerView.getChildAt(lastVisiblePosition - firstVisiblePosition);
                    if (lastView.getBottom() + recyclerView.getPaddingBottom() == recyclerView.getHeight()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isScrollToTop(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null){
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            if (firstVisiblePosition == 0){
                View firstView = recyclerView.getChildAt(firstVisiblePosition);
                if (firstView.getTop() == recyclerView.getPaddingTop()){
                    return true;
                }
            }
        }
        return false;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pos = recyclerEdit.getText().toString();
            switch (v.getId()){
                case R.id.close_loop_scroll:
                    switch_close_scroll = !switch_close_scroll;
                    if (switch_close_scroll){
                        closeLoopScroll.setText("关闭循环滚动");
                        testScrollHandler.removeCallbacksAndMessages(null);
                    }else {
                        closeLoopScroll.setText("开启循环滚动");
                        testScrollHandler.removeMessages(0);
                        testScrollHandler.sendEmptyMessageDelayed(0, 3*1000);
                    }
                    break;
                case R.id.test_smooth_scroll_to_position:
                    if (!TextUtils.isEmpty(pos)){
                        mRecyclerView.smoothScrollToPosition(Integer.valueOf(pos));
                    }
                    break;
                case R.id.test_smooth_scroll_by:
                    if (!TextUtils.isEmpty(pos)){
                        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                        int position= Integer.valueOf(pos);
                        if (position > firstVisibleItemPosition && position < lastVisibleItemPosition){
                            View childView = mRecyclerView.getChildAt(position-firstVisibleItemPosition);
                            mRecyclerView.smoothScrollBy(0, childView.getTop());
                        }
                    }
                    break;
                case R.id.test_scroll_to_position_with_offset:
                    if (!TextUtils.isEmpty(pos)){
                        layoutManager.scrollToPositionWithOffset(Integer.valueOf(pos), 0);
                    }
                    break;
            }
        }
    };

    static class TestScrollHandler extends HandlerUtils<RecyclerView>{

        LinearLayoutManager manager;

        int count;

        int position;

        //目标项是否在最后一个可见项之后
        private boolean mShouldScroll;
        //记录目标项位置
        private int mToPosition;

        public TestScrollHandler(RecyclerView recyclerView) {
            super(recyclerView);
            manager = (LinearLayoutManager)recyclerView.getLayoutManager();
            if (recyclerView.getAdapter() != null){
                count = recyclerView.getAdapter().getItemCount();
            }
        }

        @Override
        public void handleMessage(RecyclerView recyclerView, Message message) {
            if (recyclerView != null){
                if (manager != null){
                    if (position < count){
//                        manager.scrollToPositionWithOffset(position, 5);
                        smoothMoveToPosition(recyclerView, manager, position);
                        position++;
                        removeMessages(0);
                        sendEmptyMessageDelayed(0, 600);
                    }
                }
            }
        }
        /**
         * 滑动到指定位置
         * https://www.jianshu.com/p/7110bedfdb5e
         * https://blog.csdn.net/smile_Running/article/details/105342479
         * https://www.jianshu.com/p/ff6082c0867e
         */
        private void smoothMoveToPosition(RecyclerView mRecyclerView,LinearLayoutManager manager, final int position) {
            // 第一个可见位置
            int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
//            int firstItem = manager.findFirstVisibleItemPosition();
            // 最后一个可见位置
            int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
//            int lastItem = manager.findLastVisibleItemPosition();
            if (position < firstItem) {
                // 第一种可能:跳转位置在第一个可见位置之前
                mRecyclerView.smoothScrollToPosition(position);
            } else if (position <= lastItem) {
                // 第二种可能:跳转位置在第一个可见位置之后
                int movePosition = position - firstItem;
                if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                    int top = mRecyclerView.getChildAt(movePosition).getTop();
                    mRecyclerView.smoothScrollBy(0, top);
                }
            } else {
                // 第三种可能:跳转位置在最后可见项之后
                mRecyclerView.smoothScrollToPosition(position);
                mToPosition = position;
                mShouldScroll = true;
            }
        }
    }

    RecyclerListAdapter.OnItemClickListener<RecyclerListAdapter> itemClickListener = new RecyclerListAdapter.OnItemClickListener<RecyclerListAdapter>(){
        @Override
        public void onItemClick(RecyclerListAdapter adapter, View itemView, int position) {
            Toast.makeText(RecyclerViewPracticeActivity.this, "点击item", Toast.LENGTH_SHORT).show();
        }
    };

    IAdapterChildClickListener.OnItemChildClickListener<RecyclerListAdapter> itemChildClickListener = new IAdapterChildClickListener.OnItemChildClickListener<RecyclerListAdapter>() {
        @Override
        public void onItemChildClick(RecyclerListAdapter adapter, View view, int position) {
            String selectViewName = "未选中";
            switch (view.getId()){
                case R.id.item_button:
                    selectViewName = "选中button";
                    break;
                case R.id.item_text:
                    selectViewName = "选中TextView";
                    break;
            }
            Toast.makeText(RecyclerViewPracticeActivity.this, selectViewName, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (testScrollHandler != null){
            testScrollHandler.removeCallbacksAndMessages(null);
        }
        testScrollHandler = null;
    }
}
