package com.liji.jkidney.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.liji.jkidney.utils.JValidator;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.listener.SaveListener;

/**
 * 邮箱注册
 */
@ContentView(R.layout.activity_register_email)
public class ActRegisterEMail extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_username)
    EditText etUsername;
    @ViewInject(R.id.et_password)
    EditText etPassword;

    @ViewInject(R.id.btn_register)
    Button btnRegister;

    String email;
    String password;

    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("邮箱注册");
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
        email = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            JToastUtils.showToast(ActRegisterEMail.this, "邮箱不能为空");
            return;
        } else if (!JValidator.isEmail(email)) {
            JToastUtils.showToast(ActRegisterEMail.this, "邮箱格式不正确");
            return;
        }

        password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            JToastUtils.showToast(ActRegisterEMail.this, "密码不能为空");
            return;
        }


        MyUser userRegister = new MyUser();
        userRegister.setUsername(email);
        userRegister.setPassword(password);
        userRegister.setNickname("无名");
        userRegister.setAge(0);
        userRegister.setEmail(email);
        userRegister.setSex("男");
        userRegister.setCareer("未知");
        userRegister.setAddress("中国");
        userRegister.setInfo("这个人很懒，什么都没有留下...");
        userRegister.signUp(ActRegisterEMail.this, new SaveListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActRegisterEMail.this, "邮箱注册成功，请到" + email + "邮箱中进行激活。");
                gotoActLogin();

            }


            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActRegisterEMail.this, "邮箱注册失败: " + s);
            }
        });


    }

    /**
     * 跳转到首页
     */
    private void gotoActLogin() {
        Intent intent = new Intent();
        intent.setClass(ActRegisterEMail.this, ActLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
