package com.rq.practice.adapter;

import android.content.Context;
import android.widget.Button;

import com.rq.practice.R;
import com.rq.practice.adapter.base.CommonBaseAdapter;

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ${user}
 * @version [版本号, ${date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PraListViewAdapter extends CommonBaseAdapter<String> {

    public PraListViewAdapter(Context context){
        super(context);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.pra_listview_item;
    }

    @Override
    protected void bindHolder(ViewHolder holder, int position) {
        Button button = holder.getItemView(R.id.text_item);
        button.setText(String.valueOf(position));
    }
}
