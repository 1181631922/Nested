package com.fanyafeng.nested.ExpandListView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.nested.ChangeData.ChangeDataBean;
import com.fanyafeng.nested.ChangeData.ChangeDataDialog;
import com.fanyafeng.nested.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyafeng on 2016/2/22,0022.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ExpandBean> expandBeanList;

    private GroupHolder groupHolder;
    private ChildHolder childHolder;


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
        CheckBox check_parent;
        SimpleDraweeView iv_expand_icon;
        TextView tv_expand_name;
        TextView tv_expand_edit;
        TextView tv_expand_get;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_parent_expand, null);
            groupHolder = new GroupHolder();
            groupHolder.check_parent = (CheckBox) convertView.findViewById(R.id.check_parent);
            groupHolder.iv_expand_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_expand_icon);
            groupHolder.tv_expand_name = (TextView) convertView.findViewById(R.id.tv_expand_name);
            groupHolder.tv_expand_get = (TextView) convertView.findViewById(R.id.tv_expand_get);
            groupHolder.tv_expand_edit = (TextView) convertView.findViewById(R.id.tv_expand_edit);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
//        checkbox状态
        groupHolder.check_parent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    expandBeanList.get(groupPosition).getGroup().setGroupIsChecked(true);
                } else {
                    expandBeanList.get(groupPosition).getGroup().setGroupIsChecked(false);
                }
                notifyDataSetChanged();
            }
        });
//        是否处于选中状态
        if (expandBeanList.get(groupPosition).getGroup().isGroupIsChecked()) {
            groupHolder.check_parent.setChecked(true);
        } else {
            groupHolder.check_parent.setChecked(false);
        }
//        是否有优惠券
        if (expandBeanList.get(groupPosition).getGroup().isGroupIsCoupon()) {
            groupHolder.tv_expand_get.setVisibility(View.VISIBLE);
        } else {
            groupHolder.tv_expand_get.setVisibility(View.GONE);
        }
        groupHolder.iv_expand_icon.setImageURI(Uri.parse(expandBeanList.get(groupPosition).getGroup().getGroupImage()));
        groupHolder.tv_expand_name.setText(expandBeanList.get(groupPosition).getGroup().getGroupName());
        if (expandBeanList.get(groupPosition).getGroup().isGroupIsEdit()) {
            groupHolder.tv_expand_edit.setText("完成");
        } else {
            groupHolder.tv_expand_edit.setText("编辑");
        }
        groupHolder.tv_expand_edit.setOnClickListener(new GroupViewClick(groupPosition));
        return convertView;
    }

    /**
     * 使某个组处于编辑状态
     * <p>
     * groupPosition组的位置
     */
    class GroupViewClick implements View.OnClickListener {
        private int groupPosition;

        public GroupViewClick(int groupPosition) {
            this.groupPosition = groupPosition;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == groupHolder.tv_expand_edit.getId()) {
                if (expandBeanList.get(groupPosition).getGroup().isGroupIsEdit()) {
                    expandBeanList.get(groupPosition).getGroup().setGroupIsEdit(false);
                } else {
                    expandBeanList.get(groupPosition).getGroup().setGroupIsEdit(true);
                }
                notifyDataSetChanged();
            }
        }
    }

    class ChildHolder {
        CheckBox check_child;
        TextView tv_expand_child_name;
        TextView tv_done_edit;
        SimpleDraweeView iv_expand_child_icon;
        LinearLayout layout_is_edit;
        Button btn_count_reduce;
        EditText content_fu_count;
        Button btn_count_add;
        TextView tv_count_edit;
        TextView tv_count_del;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_child_expand, null);
            childHolder = new ChildHolder();
            childHolder.check_child = (CheckBox) convertView.findViewById(R.id.check_child);
            childHolder.tv_expand_child_name = (TextView) convertView.findViewById(R.id.tv_expand_child_name);
            childHolder.tv_done_edit = (TextView) convertView.findViewById(R.id.tv_done_edit);
            childHolder.iv_expand_child_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_expand_child_icon);
            childHolder.layout_is_edit = (LinearLayout) convertView.findViewById(R.id.layout_is_edit);
            childHolder.btn_count_reduce = (Button) convertView.findViewById(R.id.btn_count_reduce);
            childHolder.content_fu_count = (EditText) convertView.findViewById(R.id.content_fu_count);
            childHolder.btn_count_add = (Button) convertView.findViewById(R.id.btn_count_add);
            childHolder.tv_count_edit = (TextView) convertView.findViewById(R.id.tv_count_edit);
            childHolder.tv_count_del = (TextView) convertView.findViewById(R.id.tv_count_del);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.check_child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        if (expandBeanList.get(groupPosition).getGroup().isGroupIsChecked()) {
            expandBeanList.get(groupPosition).getChild().get(childPosition).setChildIsChecked(true);
        } else {
            expandBeanList.get(groupPosition).getChild().get(childPosition).setChildIsChecked(false);
        }
        if (expandBeanList.get(groupPosition).getChild().get(childPosition).isChildIsChecked()) {
            childHolder.check_child.setChecked(true);
        } else {
            childHolder.check_child.setChecked(false);
        }

        if (expandBeanList.get(groupPosition).getGroup().isGroupIsEdit()) {
            childHolder.tv_done_edit.setVisibility(View.GONE);
            childHolder.layout_is_edit.setVisibility(View.VISIBLE);
        } else {
            childHolder.tv_done_edit.setVisibility(View.VISIBLE);
            childHolder.layout_is_edit.setVisibility(View.GONE);
        }
        childHolder.tv_expand_child_name.setText(expandBeanList.get(groupPosition).getChild().get(childPosition).getName());
        childHolder.tv_done_edit.setText("X" + expandBeanList.get(groupPosition).getChild().get(childPosition).getCount() + "　道符");
        childHolder.content_fu_count.setText(String.valueOf(expandBeanList.get(groupPosition).getChild().get(childPosition).getCount()));
        childHolder.iv_expand_child_icon.setImageURI(Uri.parse(expandBeanList.get(groupPosition).getChild().get(childPosition).getChildImage()));
        childHolder.btn_count_add.setOnClickListener(new ChildViewClick(groupPosition, childPosition));
        childHolder.btn_count_reduce.setOnClickListener(new ChildViewClick(groupPosition, childPosition));
        childHolder.tv_count_del.setOnClickListener(new DelChildViewClick(groupPosition, childPosition));
        childHolder.tv_count_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeDataBean changeDataBean = new ChangeDataBean(123, "password");
                ChangeDataDialog changeDataDialog = new ChangeDataDialog(context, R.style.mystyle, R.layout.layout_dialog_input, changeDataBean, new ChangeDataDialog.InputListener() {
                    @Override
                    public void getNameAndPassword(String number, String password) {
                        Toast.makeText(context, number + password, Toast.LENGTH_SHORT).show();
                    }
                });
                changeDataDialog.getWindow().setGravity(Gravity.BOTTOM);
                changeDataDialog.show();
            }
        });

        return convertView;
    }


    /**
     * 删除childview
     * <p>
     * groupPosition  组的位置
     * childPosition  子的位置
     */
    class DelChildViewClick implements View.OnClickListener {
        private int groupPosition;
        private int childPosition;

        public DelChildViewClick(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            expandBeanList.get(groupPosition).getChild().remove(childPosition);
            if (expandBeanList.get(groupPosition).getChild().size() <= 0) {
                expandBeanList.remove(groupPosition);
                expandBeanList.get(groupPosition).getGroup().setGroupIsEdit(false);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 增加，减少数据操作
     * <p>
     * groupPosition  组的位置
     * childPosition  子的位置
     */
    class ChildViewClick implements View.OnClickListener {
        private int groupPosition;
        private int childPosition;

        public ChildViewClick(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            int mycount = expandBeanList.get(groupPosition).getChild().get(childPosition).getCount();

            if (v.getId() == childHolder.btn_count_add.getId()) {
                if (mycount < 99) {
                    expandBeanList.get(groupPosition).getChild().get(childPosition).setCount(++mycount);
                } else {
                    Toast.makeText(context, "已经超出最大数量", Toast.LENGTH_SHORT).show();
                }
            } else if (v.getId() == childHolder.btn_count_reduce.getId()) {
                if (mycount > 1) {
                    expandBeanList.get(groupPosition).getChild().get(childPosition).setCount(--mycount);
                } else {
                    Toast.makeText(context, "符的个数不能小于1", Toast.LENGTH_SHORT).show();
                }
            }
            notifyDataSetChanged();
        }
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
