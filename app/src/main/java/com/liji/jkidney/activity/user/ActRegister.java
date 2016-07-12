package com.liji.jkidney.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.activity.MainActivity;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.listener.SaveListener;

@ContentView(R.layout.activity_register)
public class ActRegister extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_username)
    EditText etUsername;
    @ViewInject(R.id.et_password)
    EditText etPassword;
    @ViewInject(R.id.et_password_confirm)
    EditText etPasswordConfirm;

    @ViewInject(R.id.btn_register)
    Button btnRegister;

    String username;
    String password;
    String passwordConfirm;


    @Override
    public void initView(Bundle savedInstanceState) {

        headview.setTitle("注册");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });

    }


    private void doSubmit() {
        username = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            JToastUtils.showToast(ActRegister.this, "用户名不能为空");
            return;
        }

        password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            JToastUtils.showToast(ActRegister.this, "密码不能为空");
            return;
        }

        passwordConfirm = etPasswordConfirm.getText().toString().trim();
        if (TextUtils.isEmpty(passwordConfirm)) {
            JToastUtils.showToast(ActRegister.this, "确认密码不能为空");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            JToastUtils.showToast(ActRegister.this, "两次密码不一致");
            return;
        }

        MyUser userRegister = new MyUser();
        userRegister.setUsername(username);
        userRegister.setPassword(password);
        userRegister.setComfirmPwd(passwordConfirm);
        userRegister.signUp(ActRegister.this, new SaveListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActRegister.this, "注册成功");
                gotoActMainAct();

            }


            @Override
            public void onFailure(int i, String s) {

            }
        });


    }


    /**
     * 跳转到首页
     */
    private void gotoActMainAct() {
        Intent intent = new Intent();
        intent.setClass(ActRegister.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
