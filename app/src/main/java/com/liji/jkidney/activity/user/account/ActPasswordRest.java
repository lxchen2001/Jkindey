package com.liji.jkidney.activity.user.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.BindView;

/**
 * 密码安全，重置密码
 */
@ContentView(R.layout.activity_act_password_rest)
public class ActPasswordRest extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.item_ll_modify)
    LinearLayout itemLlModify;
    @ViewInject(R.id.item_ll_forget)
    LinearLayout itemLlForget;
    @ViewInject(R.id.ll_email)
    LinearLayout ll_email;

    @ViewInject(R.id.tv_mail)
    TextView tvMail;

    @ViewInject(R.id.img_go)
    ImageView imgGo;

    @ViewInject(R.id.img_email)
    ImageView imgEmail;

    String mail;
    MyUser userLocal;


    //邮箱可编辑
    private static final int EIDT_YES = 0;

    //邮箱不可编辑
    private static final int EIDT_NO = 1;


    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("账户安全");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        itemLlModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });

        itemLlForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActPasswordRest.this, ActEmailResetPwd.class));
            }
        });

        initData();
    }

    private void doSubmit() {
        startActivity(new Intent(ActPasswordRest.this, ActPasswordModify.class));
    }

    private void initData() {
        userLocal = User.getCurrentUser(ActPasswordRest.this);
        mail = userLocal.getEmail();
        tvMail.setText("" + mail);
        if (TextUtils.isEmpty(mail)) {//为空或者没有验证，则跳转页面进行输入邮箱验证
            imgGo.setVisibility(View.VISIBLE);
            imgEmail.setBackgroundResource(R.drawable.ic_verify_no);
            ll_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoEmail(mail, EIDT_YES);
                }
            });
        } else {
            if (userLocal.getEmailVerified() != null && (!userLocal.getEmailVerified())) {//没有验证
                imgGo.setVisibility(View.VISIBLE);
                imgEmail.setBackgroundResource(R.drawable.ic_verify_no);
                ll_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoEmail(mail, EIDT_NO);
                    }
                });

            } else {
                imgEmail.setBackgroundResource(R.drawable.ic_verify);
                imgGo.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 邮箱验证操作
     */
    private void gotoEmail(String email, int type) {
        Intent intent = new Intent(ActPasswordRest.this, ActEmailVerfy.class);
        intent.putExtra(ActEmailVerfy.EMAI_LVERIFY, email);
        intent.putExtra(ActEmailVerfy.EMAI_TYPE, type);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                tvMail.setText("" + data.getStringExtra(ActEmailVerfy.EMAI_LVERIFY));
            }
        }
    }


}
