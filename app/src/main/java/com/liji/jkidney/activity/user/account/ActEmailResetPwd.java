package com.liji.jkidney.activity.user.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.JValidator;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;

/**
 * 邮箱重置密码
 */
@ContentView(R.layout.activity_email_reset_pwd)
public class ActEmailResetPwd extends ActBase {
    String email;

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_email)
    EditText etEmail;
    @ViewInject(R.id.btn_verify)
    Button btnVerify;

    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("重置密码");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }

        });
    }

    private void doSubmit() {
        email = etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email) || !JValidator.isEmail(email)) {
            JToastUtils.showToast(ActEmailResetPwd.this, "邮箱格式不正确");
            return;
        }

        BmobUser.resetPasswordByEmail(ActEmailResetPwd.this, email, new ResetPasswordByEmailListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActEmailResetPwd.this, "重置密码请求成功，请到" + email + "邮箱进行密码重置操作");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActEmailResetPwd.this, "重置密码请求失败：" + s);
            }
        });
    }
}
