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

public class ExpandListViewActivity extends BaseActivity {
    private ExpandableListView expand_listview;
    private String imageUri = "http://www.apkbus.com/data/attachment/forum/201402/27/154958qgczo5a17ia3u3c4.png";

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
    }

    private void initData() {
//同listview，可以添加头和脚
//        expand_listview.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_dialog_input, null));
//        expand_listview.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_dialog_input, null));
//        expand_listview.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_dialog_input, null));
        expand_listview.setAdapter(expandableListAdapter);
//        将子项全部展开
        for (int i = 0; i < 3; i++) {
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

        int[] logos = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
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
        private int[][] generallogos = new int[][]{
                {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher},
                {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher},
                {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher}
        };

        TextView getTextView() {
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
            TextView textView = new TextView(ExpandListViewActivity.this);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(36, 0, 0, 0);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            return textView;
        }

        @Override
        public int getGroupCount() {
            return generalsTypes.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return generals[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return generalsTypes[groupPosition];
        }

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
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(ExpandListViewActivity.this).inflate(R.layout.layout_parent_expand, null);
            TextView tv_expand_name = (TextView) view.findViewById(R.id.tv_expand_name);
            tv_expand_name.setText(getGroup(groupPosition).toString());
            TextView tv_expand_edit = (TextView) view.findViewById(R.id.tv_expand_edit);
            tv_expand_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeDataBean changeDataBean = new ChangeDataBean(123, "password");
                    ChangeDataDialog changeDataDialog = new ChangeDataDialog(ExpandListViewActivity.this, R.style.mystyle, R.layout.layout_dialog_input, changeDataBean, new ChangeDataDialog.InputListener() {
                        @Override
                        public void getNameAndPassword(String number, String password) {
                            Toast.makeText(ExpandListViewActivity.this, number + password, Toast.LENGTH_SHORT).show();
                        }
                    });
                    changeDataDialog.getWindow().setGravity(Gravity.BOTTOM);
                    changeDataDialog.show();
                }
            });
            return view;


//            LinearLayout linearLayout = new LinearLayout(ExpandListViewActivity.this);
//            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//            ImageView logo = new ImageView(ExpandListViewActivity.this);
//            logo.setImageResource(logos[groupPosition]);
//            linearLayout.addView(logo);
//            TextView textView = getTextView();
//            textView.setTextColor(Color.BLACK);
//            textView.setText(getGroup(groupPosition).toString());
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(ExpandListViewActivity.this, "点我啊", Toast.LENGTH_SHORT).show();
//                }
//            });
//            linearLayout.addView(textView);
//            return linearLayout;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//            LinearLayout ll = new LinearLayout(ExpandListViewActivity.this);
//            ll.setPadding(200, 0, 0, 0);
//            ll.setOrientation(LinearLayout.HORIZONTAL);
//            ImageView generallogo = new ImageView(ExpandListViewActivity.this);
//            generallogo.setImageResource(generallogos[groupPosition][childPosition]);
//            ll.addView(generallogo);
//            TextView textView = getTextView();
//            textView.setText(getChild(groupPosition, childPosition).toString());
//            ll.addView(textView);
//            return ll;
            LinearLayout linearLayout = new LinearLayout(ExpandListViewActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View view = LayoutInflater.from(ExpandListViewActivity.this).inflate(R.layout.layout_child_expand, null);
            TextView tv_expand_child_name = (TextView) view.findViewById(R.id.tv_expand_child_name);
            tv_expand_child_name.setText(getChild(groupPosition, childPosition).toString());
            TextView line = new TextView(ExpandListViewActivity.this);
            SimpleDraweeView iv_expand_child_icon=(SimpleDraweeView)view.findViewById(R.id.iv_expand_child_icon);
            iv_expand_child_icon.setImageURI(Uri.parse(imageUri));
            linearLayout.addView(view,params);
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
