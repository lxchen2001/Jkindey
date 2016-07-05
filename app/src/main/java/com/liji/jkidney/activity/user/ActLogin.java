package com.liji.jkidney.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

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

    String username;
    String password;


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


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }


        });


    }




    private void doSubmit() {
        username = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            JToastUtils.showToast(ActLogin.this, "用户名不能为空");
            return;
        }

        password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            JToastUtils.showToast(ActLogin.this, "密码不能为空");
            return;
        }

        BmobUser.loginByAccount(ActLogin.this, username, password, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (myUser != null) {
                    JToastUtils.showToast(ActLogin.this, "登录成功");
                    finish();
                } else {
                    JToastUtils.showToast(ActLogin.this, "" + e.getMessage());
                }

            }
        });

    }

}
