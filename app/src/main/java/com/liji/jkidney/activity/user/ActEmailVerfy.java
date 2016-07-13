package com.liji.jkidney.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liji.dev.androidutils.utils.citypickerWheelView.widget.CityPickerView;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.JValidator;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.listener.EmailVerifyListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 邮箱验证
 */

@ContentView(R.layout.activity_email_verfy)
public class ActEmailVerfy extends ActBase {

    public static final String EMAI_LVERIFY = "email";
    public static final String EMAI_TYPE = "type";
    String email;

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_email)
    EditText etEmail;
    @ViewInject(R.id.btn_verify)
    Button btnVerify;


    //邮箱可编辑
    private static final int EIDT_YES = 0;

    //邮箱不可编辑
    private static final int EIDT_NO = 1;
    private int type = EIDT_YES;

    @Override
    protected void initView(Bundle savedInstanceState) {
        email = this.getIntent().getStringExtra(EMAI_LVERIFY);
        type = this.getIntent().getIntExtra(EMAI_TYPE, EIDT_YES);
        etEmail.setText("" + email);
        headview.setTitle("邮箱验证");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                sendEmail();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }

        });

        if (type == EIDT_NO) {
            etEmail.setFocusable(false);
            etEmail.setFocusableInTouchMode(false);
        } else {
            etEmail.setFocusable(true);
            etEmail.setFocusableInTouchMode(true);
        }
    }

    private void doSubmit() {
        email = etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email) || email.equals("null")) {
            JToastUtils.showToast(ActEmailVerfy.this, "邮箱不能为空");
            return;
        } else if (!JValidator.isEmail(email)) {
            JToastUtils.showToast(ActEmailVerfy.this, "邮箱格式不正确");
            return;
        }


        if (User.getCurrentUser(ActEmailVerfy.this).getEmailVerified() == null) {
            MyUser user = new MyUser();
            user.setEmail(email);
            user.update(ActEmailVerfy.this, User.getCurrentUser(ActEmailVerfy.this).getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    JToastUtils.showToast(ActEmailVerfy.this, "请求验证邮件成功，请到" + email + "邮箱中进行激活。");
                    sendEmail();
                }

                @Override
                public void onFailure(int i, String s) {
                    JToastUtils.showToast(ActEmailVerfy.this, "请求验证邮件失败：" + s);
                }
            });
        } else {
            MyUser.requestEmailVerify(ActEmailVerfy.this, email, new EmailVerifyListener() {
                @Override
                public void onSuccess() {
                    JToastUtils.showToast(ActEmailVerfy.this, "请求验证邮件成功，请到" + email + "邮箱中进行激活。");
                    sendEmail();
                }

                @Override
                public void onFailure(int i, String s) {
                    JToastUtils.showToast(ActEmailVerfy.this, "请求验证邮件失败：" + s);
                }
            });
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            sendEmail();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void sendEmail() {
        Intent intent = new Intent();
        intent.putExtra(EMAI_LVERIFY, email);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
