package com.fanyafeng.nested.ChangeData;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.nested.BaseActivity;
import com.fanyafeng.nested.R;

public class PreviousActivity extends BaseActivity {
    private TextView tv_needchange, tv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);
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
        findViewById(R.id.btn_dialogfragment).setOnClickListener(this);
        tv_needchange = (TextView) findViewById(R.id.tv_needchange);
        tv_num = (TextView) findViewById(R.id.tv_num);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_dialogfragment:
                ChangeDataBean changeDataBean = new ChangeDataBean(123, "password");
                ChangeDataDialog changeDataDialog = new ChangeDataDialog(this, R.style.mystyle, R.layout.layout_dialog_input, changeDataBean, new ChangeDataDialog.InputListener() {
                    @Override
                    public void getNameAndPassword(String number, String password) {
                        Toast.makeText(PreviousActivity.this, number + password, Toast.LENGTH_SHORT).show();
                        tv_num.setText(number);
                        tv_needchange.setText(password);
                    }
                });
                changeDataDialog.getWindow().setGravity(Gravity.BOTTOM);
                changeDataDialog.show();

                break;
        }
    }

}
