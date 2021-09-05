package com.rq.practice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rq.practice.R;

import java.util.ArrayList;
import java.util.List;

public class NativeRecyclerAdapter extends RecyclerView.Adapter<NativeRecyclerAdapter.ViewHolder> {

    private Context context;

    private List<String> dataList = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public NativeRecyclerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public NativeRecyclerAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<String> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = layoutInflater.inflate(R.layout.native_recycler_adapter_item, viewGroup, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nativeText.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataList == null){
            return 0;
        }
        return dataList.size();
    }


    public static final class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nativeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nativeText = itemView.findViewById(R.id.native_recycler_text);
        }
    }
}
