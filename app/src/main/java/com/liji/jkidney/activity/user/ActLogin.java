package com.liji.jkidney.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class ActLogin extends ActBase {


    @ViewInject(R.id.et_phone)
    EditText et_phone;


    @ViewInject(R.id.et_password)
    EditText et_password;


    @ViewInject(R.id.btn_login)
    Button btnLogin;


    @ViewInject(R.id.tv_register)
    TextView tv_register;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;


    @Override
    public void initView(Bundle savedInstanceState) {

        headView.setTitle("登录");
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActLogin.this, ActRegister.class));
            }
        });



    }

    @Override
    public void setData(Bundle savedInstanceState) {

    }
}
