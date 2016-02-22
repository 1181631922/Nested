package com.fanyafeng.nested.ExpandListView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.nested.R;

import java.util.List;

/**
 * Created by fanyafeng on 2016/2/22,0022.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {
    private String imageUri = "http://www.apkbus.com/data/attachment/forum/201402/27/154958qgczo5a17ia3u3c4.png";
    private Context context;
    private List<ExpandBean> expandBeanList;

    public ExpandAdapter(Context context, List<ExpandBean> expandBeanList) {
        this.context = context;
        this.expandBeanList = expandBeanList;
    }

    @Override
    public int getGroupCount() {
        return expandBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandBeanList.get(groupPosition).getChild().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandBeanList.get(groupPosition).getGroup();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandBeanList.get(groupPosition).getChild().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    class GroupHolder {
        TextView tv_expand_name;
        TextView tv_expand_edit;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_parent_expand, null);
            groupHolder=new GroupHolder();
            groupHolder.tv_expand_name = (TextView) convertView.findViewById(R.id.tv_expand_name);
            groupHolder.tv_expand_edit = (TextView) convertView.findViewById(R.id.tv_expand_edit);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.tv_expand_name.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    class ChildHolder {
        TextView tv_expand_child_name;
        SimpleDraweeView iv_expand_child_icon;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_child_expand, null);
            childHolder=new ChildHolder();
            childHolder.tv_expand_child_name = (TextView) convertView.findViewById(R.id.tv_expand_child_name);
            childHolder.iv_expand_child_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_expand_child_icon);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.tv_expand_child_name.setText(getChild(groupPosition, childPosition).toString());
        childHolder.iv_expand_child_icon.setImageURI(Uri.parse(imageUri));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
