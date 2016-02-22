package com.fanyafeng.nested.ChangeData;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.fanyafeng.nested.R;

/**
 * Created by fanyafeng on 2016/2/19,0019.
 */
public class ChangeDataDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private int layoutRes;
    private ChangeDataBean changeDataBean;

    private EditText et_input_name, et_input_password;
    private InputListener inputListener;

    public ChangeDataDialog(Context context, int theme, int layoutRes, ChangeDataBean changeDataBean,InputListener inputListener) {
        super(context,theme);
        this.context = context;
        this.layoutRes = layoutRes;
        this.changeDataBean = changeDataBean;
        this.inputListener=inputListener;
    }

    public interface InputListener {
        void getNameAndPassword(String number, String password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutRes);
        et_input_name = (EditText) findViewById(R.id.et_input_name);
        et_input_password = (EditText) findViewById(R.id.et_input_password);
        findViewById(R.id.btn_input_cancle).setOnClickListener(this);
        findViewById(R.id.btn_input_enter).setOnClickListener(this);

        Window window = this.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        this.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        ChangeDataDialog.this.setCanceledOnTouchOutside(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input_cancle:
                et_input_name.setText(changeDataBean.getNumber() + "");
                et_input_password.setText(changeDataBean.getPassword());
                break;
            case R.id.btn_input_enter:
                inputListener.getNameAndPassword(et_input_name.getText().toString(),et_input_password.getText().toString());
                ChangeDataDialog.this.dismiss();
                break;
        }
    }


}
