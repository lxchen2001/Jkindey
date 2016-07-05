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
    @ViewInject(R.id.et_nickname)
    EditText etNickname;
    @ViewInject(R.id.tv_age)
    TextView tvAge;
    @ViewInject(R.id.ll_age)
    LinearLayout llAge;
    @ViewInject(R.id.et_career)
    EditText etCareer;
    @ViewInject(R.id.et_mail)
    EditText etMail;
    @ViewInject(R.id.tv_address)
    TextView tvAddress;
    @ViewInject(R.id.ll_address)
    LinearLayout llAddress;
    @ViewInject(R.id.ll_detail)
    LinearLayout llDetail;
    @ViewInject(R.id.btn_register)
    Button btnRegister;

    String username;
    String password;
    String nickname;
    String sex;
    String career;
    String mail;
    String address;
    String info;

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

    @Override
    public void setData(Bundle savedInstanceState) {


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

        nickname = etNickname.getText().toString().trim();
        sex = "男";
        career = etCareer.getText().toString().trim();
        mail = etMail.getText().toString().trim();
        address = "北京";
        info = "自由自在的飞翔...";

        MyUser userRegister = new MyUser();
        userRegister.setUsername(username);
        userRegister.setPassword(password);
        userRegister.setNickname(nickname);
        userRegister.setSex(sex);
        userRegister.setCareer(career);
        userRegister.setEmail(mail);
        userRegister.setAddress(address);
        userRegister.setInfo(info);
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
