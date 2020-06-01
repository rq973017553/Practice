package com.rq.practice.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.rq.practice.R;
import com.rq.practice.adapter.base.BaseRecyclerAdapter;
import com.rq.practice.bean.RecyclerListBean;

import java.util.List;

public class RecyclerListAdapter extends BaseRecyclerAdapter<RecyclerListBean> {

    public RecyclerListAdapter(Context context, List<RecyclerListBean> listData) {
        super(context, listData);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.pra_recycler_view_item;
    }

    @Override
    protected void bindHolder(RecyclerViewHolder holder, int position) {
        RecyclerListBean recyclerListBean = mListData.get(position);
        TextView itemText = holder.getTextView(R.id.item_text);
        Button button = holder.getButton(R.id.item_button);
        itemText.setText(recyclerListBean.getText());
        holder.addOnClickListener(button, position);
        holder.addOnClickListener(itemText, position);
    }
}
