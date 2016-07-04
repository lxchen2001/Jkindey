package com.liji.jkidney.activity.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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

    @Override
    public void initView(Bundle savedInstanceState) {

        headview.setTitle("注册");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
    }

    @Override
    public void setData(Bundle savedInstanceState) {

    }


}
