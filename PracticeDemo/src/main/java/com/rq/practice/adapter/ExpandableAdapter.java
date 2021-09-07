package com.rq.practice.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    // 获得组数
    @Override
    public int getGroupCount() {
        return 0;
    }

    // 获得组的子项个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    // 获得某组数据
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    // 获得指定子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    // 获取组ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    // 获取子项ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    // 分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
    @Override
    public boolean hasStableIds() {
        return false;
    }

    // 获取组视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    // 获取指定组的指定子项视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    // 子项是否可选
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
