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

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_parent_expand, null);
        TextView tv_expand_name = (TextView) view.findViewById(R.id.tv_expand_name);
        tv_expand_name.setText(getGroup(groupPosition).toString());
        TextView tv_expand_edit = (TextView) view.findViewById(R.id.tv_expand_edit);
        tv_expand_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "编辑被点击,第几组：" + groupPosition, Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_child_expand, null);
        TextView tv_expand_child_name = (TextView) view.findViewById(R.id.tv_expand_child_name);
        tv_expand_child_name.setText(getChild(groupPosition, childPosition).toString());
        tv_expand_child_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Parent和Child分别为：" + groupPosition + " , " + childPosition, Toast.LENGTH_SHORT).show();
            }
        });
        TextView line = new TextView(context);
        SimpleDraweeView iv_expand_child_icon = (SimpleDraweeView) view.findViewById(R.id.iv_expand_child_icon);
        iv_expand_child_icon.setImageURI(Uri.parse(imageUri));
        TextView tv_is_edit = (TextView) view.findViewById(R.id.tv_is_edit);

        linearLayout.addView(view, params);
        line.setBackgroundColor(Color.BLUE);
        line.setHeight(1);
        if (childPosition != expandBeanList.get(groupPosition).getChild().size() - 1) {
            linearLayout.addView(line,params);
        }
        return linearLayout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
