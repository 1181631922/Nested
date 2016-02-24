package com.fanyafeng.nested.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.nested.BaseActivity;
import com.fanyafeng.nested.ChangeData.PreviousActivity;
import com.fanyafeng.nested.ExpandListView.ExpandListViewActivity;
import com.fanyafeng.nested.R;
import com.fanyafeng.nested.RatingBar.RatingBarActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    private void initData() {
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this, ExpandListViewActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(MainActivity.this, PreviousActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(MainActivity.this, RatingBarActivity.class));
                break;
        }
    }
}
