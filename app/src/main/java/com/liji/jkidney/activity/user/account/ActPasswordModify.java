package com.liji.jkidney.activity.user.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 修改密码
 */
@ContentView(R.layout.activity_password_modify)
public class ActPasswordModify extends ActBase {


    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_old)
    EditText etOld;
    @ViewInject(R.id.et_new)
    EditText etNew;
    @ViewInject(R.id.btn_modify)
    Button btnModify;
    @ViewInject(R.id.ll_login)
    LinearLayout llLogin;

    String oldPwd;
    String newPwd;

    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("修改密码");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });


    }

    private void doSubmit() {
        oldPwd = etOld.getText().toString().trim();
        newPwd = etNew.getText().toString().trim();
        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)) {
            JToastUtils.showToast(ActPasswordModify.this, "密码不能为空");
            return;
        }


        BmobUser.updateCurrentUserPassword(ActPasswordModify.this, oldPwd, newPwd, new UpdateListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActPasswordModify.this, "密码修改成功，可以用新密码进行登录啦!");
                MyUser.logOut(ActPasswordModify.this);
                startActivity(new Intent(ActPasswordModify.this, ActLogin.class));
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActPasswordModify.this, "密码修改失败：" + s);
            }
        });

    }


}
