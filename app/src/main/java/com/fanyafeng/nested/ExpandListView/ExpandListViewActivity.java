package com.fanyafeng.nested.ExpandListView;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.nested.BaseActivity;
import com.fanyafeng.nested.ChangeData.ChangeDataBean;
import com.fanyafeng.nested.ChangeData.ChangeDataDialog;
import com.fanyafeng.nested.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpandListViewActivity extends BaseActivity {
    private ExpandableListView expand_listview;
    private String imageUri = "http://www.apkbus.com/data/attachment/forum/201402/27/154958qgczo5a17ia3u3c4.png";
    private List<ExpandBean> expandBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        expand_listview = (ExpandableListView) findViewById(R.id.expand_listview);
        expand_listview.setGroupIndicator(null);

        List<ChildItemBean> weiChildItemBeanList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            ChildItemBean childItemBean = new ChildItemBean(false, imageUri, "夏侯" + i, i);
            weiChildItemBeanList.add(childItemBean);
        }

        List<ChildItemBean> shuChildItemBeanList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            ChildItemBean childItemBean = new ChildItemBean(false, imageUri, "赵云" + i, i);
            shuChildItemBeanList.add(childItemBean);
        }

        List<ChildItemBean> wuChildItemBeanList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            ChildItemBean childItemBean = new ChildItemBean(false, imageUri, "周瑜" + i, i);
            wuChildItemBeanList.add(childItemBean);
        }

        List<ChildItemBean> qunChildItemBeanList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ChildItemBean childItemBean = new ChildItemBean(false, imageUri, "华佗" + i, i);
            qunChildItemBeanList.add(childItemBean);
        }

        GroupItemBean groupItemBean0 = new GroupItemBean(false, imageUri, "魏国", false, false);
        ExpandBean expandBean0 = new ExpandBean(groupItemBean0, weiChildItemBeanList);
        expandBeanList.add(0, expandBean0);
        GroupItemBean groupItemBean1 = new GroupItemBean(false, imageUri, "蜀国", true, false);
        ExpandBean expandBean1 = new ExpandBean(groupItemBean1, shuChildItemBeanList);
        expandBeanList.add(1, expandBean1);
        GroupItemBean groupItemBean2 = new GroupItemBean(false, imageUri, "吴国", false, false);
        ExpandBean expandBean2 = new ExpandBean(groupItemBean2, wuChildItemBeanList);
        expandBeanList.add(2, expandBean2);
        GroupItemBean groupItemBean3 = new GroupItemBean(false, imageUri, "群国", true, false);
        ExpandBean expandBean3 = new ExpandBean(groupItemBean3, qunChildItemBeanList);
        expandBeanList.add(3, expandBean3);

    }

    private void initData() {
//同listview，可以添加头和脚
//        expand_listview.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_dialog_input, null));
//        expand_listview.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_dialog_input, null));
//        expand_listview.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_dialog_input, null));
        ExpandAdapter expandAdapter = new ExpandAdapter(this, expandBeanList);
        expand_listview.setAdapter(expandAdapter);
//        expand_listview.setAdapter(expandableListAdapter);
//        将子项全部展开
        for (int i = 0; i < expandBeanList.size(); i++) {
            expand_listview.expandGroup(i);
        }
//        这是parent不能点击
        expand_listview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    final ExpandableListAdapter expandableListAdapter = new BaseExpandableListAdapter() {


        private String[] generalsTypes = new String[]{"魏国", "蜀国", "吴国"};
        private String[][] generals = new String[][]{
                {"夏侯淳", "甄姬", "许褚", "郭嘉", "司马", "杨修"},
                {"马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云", "马谡"},
                {"吕蒙", "陆逊", "孙权", "周瑜", "孙尚香"}
        };
        private String[][] images = new String[][]{
                {"夏侯淳", "甄姬", "许褚", "郭嘉", "司马", "杨修"},
                {"马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云", "马谡"},
                {"吕蒙", "陆逊", "孙权", "周瑜", "孙尚香"}
        };

        //group个数
        @Override
        public int getGroupCount() {
            return generalsTypes.length;
        }

        //        相应的group下的child个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return generals[groupPosition].length;
        }

        //得到对应的group数据
        @Override
        public Object getGroup(int groupPosition) {
            return generalsTypes[groupPosition];
        }

        //得到相应group下child的数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return generals[groupPosition][childPosition];
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

            View view = LayoutInflater.from(ExpandListViewActivity.this).inflate(R.layout.layout_parent_expand, null);
            TextView tv_expand_name = (TextView) view.findViewById(R.id.tv_expand_name);
            tv_expand_name.setText(getGroup(groupPosition).toString());
            TextView tv_expand_edit = (TextView) view.findViewById(R.id.tv_expand_edit);
            tv_expand_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ExpandListViewActivity.this, "编辑被点击,第几组：" + groupPosition, Toast.LENGTH_SHORT).show();

                }
            });
            return view;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            LinearLayout linearLayout = new LinearLayout(ExpandListViewActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View view = LayoutInflater.from(ExpandListViewActivity.this).inflate(R.layout.layout_child_expand, null);
            TextView tv_expand_child_name = (TextView) view.findViewById(R.id.tv_expand_child_name);
            tv_expand_child_name.setText(getChild(groupPosition, childPosition).toString());
            tv_expand_child_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ExpandListViewActivity.this, "Parent和Child分别为：" + groupPosition + " , " + childPosition, Toast.LENGTH_SHORT).show();
                }
            });
            TextView line = new TextView(ExpandListViewActivity.this);
            SimpleDraweeView iv_expand_child_icon = (SimpleDraweeView) view.findViewById(R.id.iv_expand_child_icon);
            iv_expand_child_icon.setImageURI(Uri.parse(imageUri));
            TextView tv_is_edit = (TextView) view.findViewById(R.id.tv_done_edit);

            linearLayout.addView(view, params);
            line.setTextColor(Color.BLACK);
            line.setHeight(1);
            if (!isLastChild) {
                linearLayout.addView(line);
            }
            return linearLayout;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    };


}
