package com.rq.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rq.practice.R;
import com.rq.practice.adapter.base.BaseRecyclerAdapter;
import com.rq.practice.bean.Item;

import java.util.ArrayList;
import java.util.List;

class ComplexAdapter extends RecyclerView.Adapter<ComplexAdapter.ComplexViewHolder> {

    private final List<Item> mComplexItems = new ArrayList<>();

    private Context mContext;

    public ComplexAdapter(Context context) {
        this.mContext = context;
    }

    public void setComplexItems(List<Item> complexItems){
        this.mComplexItems.addAll(complexItems);
    }

    @NonNull
    @Override
    public ComplexViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.complex_item, viewGroup, false);
        return new ComplexViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplexViewHolder complexViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ComplexViewHolder extends RecyclerView.ViewHolder{
        public ComplexViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
