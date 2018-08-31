package com.rq.practice.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

/**
 * 提供给ListView&GridView的Adapter
 * @author rock you
 * @version [1.0.0, 2018.8.16]
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter implements IAdapter{

    private List<T> mListData;

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    // ItemChildClickListener
    private IAdapterChildClickListener.OnItemChildClickListener mItemChildClickListener;

    // ItemChildLongClickListener
    private IAdapterChildClickListener.OnItemChildLongClickListener mItemChildLongClickListener;

    // ItemChildTouchClickListener
    private IAdapterChildClickListener.OnItemChildTouchClickListener mItemChildTouchClickListener;

    public CommonBaseAdapter(Context context){
        this(context, Collections.<T>emptyList());
    }

    public CommonBaseAdapter(Context context, List<T> listData){
        this.mContext = context;
        this.mListData = listData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据源
     * @param listData
     */
    public void setListData(List<T> listData){
        this.mListData = listData;
    }

    @Override
    public void setItemChildClickListener(IAdapterChildClickListener.OnItemChildClickListener itemChildClickListener) {
        this.mItemChildClickListener = itemChildClickListener;
    }

    @Override
    public void setItemChildLongClickListener(IAdapterChildClickListener.OnItemChildLongClickListener itemChildLongClickListener) {
        this.mItemChildLongClickListener = itemChildLongClickListener;
    }

    @Override
    public void setItemChildTouchClickListener(IAdapterChildClickListener.OnItemChildTouchClickListener itemChildTouchClickListener) {
        this.mItemChildTouchClickListener = itemChildTouchClickListener;
    }

    @Override
    public IAdapterChildClickListener.OnItemChildClickListener getItemChildClickListener() {
        return mItemChildClickListener;
    }

    @Override
    public IAdapterChildClickListener.OnItemChildLongClickListener getItemChildLongClickListener() {
        return mItemChildLongClickListener;
    }

    @Override
    public IAdapterChildClickListener.OnItemChildTouchClickListener getItemChildTouchClickListener() {
        return mItemChildTouchClickListener;
    }

    @Override
    public int getCount() {
        if (mListData == null){
            return 0;
        }
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        if (mListData == null){
            return null;
        }
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(getLayoutID(), parent, false);
            viewHolder = new ViewHolder(this, convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        bindHolder(viewHolder, position);
        return convertView;
    }

    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void notifyDataSetChanged(ListView listView, int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            getView(position, view, listView);
        }
    }

    protected abstract int getLayoutID();

    protected abstract void bindHolder(ViewHolder holder, int position);

    public static final class ViewHolder implements IAdapter.IViewHolder{

        ViewHolderHelper<CommonBaseAdapter> mViewHolderHelper;

        ViewHolder(CommonBaseAdapter adapter, View itemView){
            mViewHolderHelper = new ViewHolderHelper<>(adapter, itemView);
        }

        @Override
        public <T extends View> T getItemView(int id) {
            return mViewHolderHelper.getItemView(id);
        }

        @Override
        public void addOnClickListener(int id, int position) {
            mViewHolderHelper.addOnClickListener(id, position);
        }

        @Override
        public void addOnClickListener(View view, int position) {
            mViewHolderHelper.addOnClickListener(view, position);
        }

        @Override
        public void addOnLongClickListener(int id, int position) {
            mViewHolderHelper.addOnLongClickListener(id, position);
        }

        @Override
        public void addOnLongClickListener(View view, int position) {
            mViewHolderHelper.addOnLongClickListener(view, position);
        }

        @Override
        public void addOnTouchClickListener(int id, int position) {
            mViewHolderHelper.addOnTouchClickListener(id, position);
        }

        @Override
        public void addOnTouchClickListener(View view, int position) {
            mViewHolderHelper.addOnTouchClickListener(view, position);
        }
    }
}
