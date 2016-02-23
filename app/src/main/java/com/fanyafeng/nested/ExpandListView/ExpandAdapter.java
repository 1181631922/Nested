package com.fanyafeng.nested.ExpandListView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.nested.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyafeng on 2016/2/22,0022.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {
    private String imageUri = "http://www.apkbus.com/data/attachment/forum/201402/27/154958qgczo5a17ia3u3c4.png";
    private Context context;
    private List<ExpandBean> expandBeanList;

    private GroupHolder groupHolder;
    private ChildHolder childHolder;

    private List<String> stringList;

    public ExpandAdapter(Context context, List<ExpandBean> expandBeanList) {
        this.context = context;
        this.expandBeanList = expandBeanList;
        stringList = new ArrayList<>();
        for (int i = 0; i < expandBeanList.size(); i++) {
            stringList.add(i, "编辑");
        }
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_parent_expand, null);
            groupHolder = new GroupHolder();
            groupHolder.tv_expand_name = (TextView) convertView.findViewById(R.id.tv_expand_name);
            groupHolder.tv_expand_edit = (TextView) convertView.findViewById(R.id.tv_expand_edit);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.tv_expand_name.setText(getGroup(groupPosition).toString());
        groupHolder.tv_expand_edit.setText(stringList.get(groupPosition));
        groupHolder.tv_expand_edit.setOnClickListener(new GroupViewClick(groupPosition));
        return convertView;
    }

    class GroupViewClick implements View.OnClickListener {
        private int groupPosition;

        public GroupViewClick(int groupPosition) {
            this.groupPosition = groupPosition;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == groupHolder.tv_expand_edit.getId()) {
//                Toast.makeText(context, "点击的position：" + groupPosition, Toast.LENGTH_SHORT).show();
//                groupHolder.tv_expand_edit.setText("点击");
                if (stringList.get(groupPosition).equals("编辑")) {
                    stringList.set(groupPosition, "完成");
                } else {
                    stringList.set(groupPosition, "编辑");
                }
                notifyDataSetChanged();
            }
        }
    }

    class ChildHolder {
        TextView tv_expand_child_name;
        TextView tv_done_edit;
        SimpleDraweeView iv_expand_child_icon;
        LinearLayout layout_is_edit;
        Button btn_count_reduce;
        EditText content_fu_count;
        Button btn_count_add;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_child_expand, null);
            childHolder = new ChildHolder();
            childHolder.tv_expand_child_name = (TextView) convertView.findViewById(R.id.tv_expand_child_name);
            childHolder.tv_done_edit = (TextView) convertView.findViewById(R.id.tv_done_edit);
            childHolder.iv_expand_child_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_expand_child_icon);
            childHolder.layout_is_edit = (LinearLayout) convertView.findViewById(R.id.layout_is_edit);
            childHolder.btn_count_reduce = (Button) convertView.findViewById(R.id.btn_count_reduce);
            childHolder.content_fu_count = (EditText) convertView.findViewById(R.id.content_fu_count);
            childHolder.btn_count_add = (Button) convertView.findViewById(R.id.btn_count_add);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        if (stringList.get(groupPosition).equals("完成")) {
            childHolder.tv_done_edit.setVisibility(View.GONE);
            childHolder.layout_is_edit.setVisibility(View.VISIBLE);
        } else {
            childHolder.tv_done_edit.setVisibility(View.VISIBLE);
            childHolder.layout_is_edit.setVisibility(View.GONE);
        }
        final ChildItemBean childItemBean = (ChildItemBean) getChild(groupPosition, childPosition);
        childHolder.tv_expand_child_name.setText(childItemBean.getName());
        childHolder.tv_done_edit.setText("X" + childItemBean.getCount() + "　道符");
        childHolder.content_fu_count.setText(String.valueOf(childItemBean.getCount()));
        childHolder.iv_expand_child_icon.setImageURI(Uri.parse(imageUri));
        childHolder.btn_count_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mycount = Integer.parseInt(childHolder.content_fu_count.getText().toString().trim());
                childItemBean.setCount(mycount++);
                childHolder.content_fu_count.setText(String.valueOf(mycount++));
                Toast.makeText(context, "符增加按钮被点击" + mycount++, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
