package com.liji.jkidney.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.dev.androidutils.utils.citypickerWheelView.widget.CityPickerView;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.listener.UpdateListener;

/**
 * 个人信息修改
 */
@ContentView(R.layout.activity_user_info_update)
public class ActUserInfoUpdate extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.et_nickname)
    EditText etNickname;
    @ViewInject(R.id.et_age)
    EditText etAge;
    @ViewInject(R.id.tv_sex)
    TextView tvSex;
    @ViewInject(R.id.ll_sex)
    LinearLayout llSex;
    @ViewInject(R.id.et_career)
    EditText etCareer;
    @ViewInject(R.id.et_mail)
    EditText etMail;
    @ViewInject(R.id.tv_address)
    TextView tvAddress;
    @ViewInject(R.id.tv_detail)
    TextView tvDetail;
    @ViewInject(R.id.ll_address)
    LinearLayout llAddress;
    @ViewInject(R.id.ll_detail)
    LinearLayout llDetail;
    MyUser userLocal;
    String nickname;
    Integer age;
    String sex;
    String career;
    String address;
    String info;
    String mail;

    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("更新信息");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        setDefaultInfo();
        headview.setRightImgAction(R.drawable.ic_confirm, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                doSubmit();
            }
        });
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPickerView cityPickerView = new CityPickerView(ActUserInfoUpdate.this);
                cityPickerView.show();
                cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        address = citySelected[0] + citySelected[1] + citySelected[2];
                        tvAddress.setText("" + address);
                    }
                });
            }
        });
    }

    private void setDefaultInfo() {
        userLocal = User.getCurrentUser(ActUserInfoUpdate.this);
        nickname = userLocal.getNickname();
        age = userLocal.getAge();
        sex = userLocal.getSex();
        mail = userLocal.getEmail();
        address = userLocal.getAddress();
        info = userLocal.getInfo();
        career = userLocal.getCareer();
        etNickname.setText("" + nickname);
        etAge.setText("" + age);
        tvSex.setText("" + sex);
        etMail.setText("" + mail);
        tvAddress.setText("" + address);
        tvDetail.setText("" + info);
        etCareer.setText("" + career);
    }


    private void doSubmit() {

        MyUser user = new MyUser();
        nickname = etNickname.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "昵称不能为空");
            return;
        } else {
            user.setNickname(nickname);
        }


        if (TextUtils.isEmpty(etAge.getText().toString().trim())) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "年龄不能为空");
            return;
        } else {
            age = Integer.parseInt(etAge.getText().toString().trim());
            user.setAge(age);
        }

        sex = tvSex.getText().toString().trim();
        if (TextUtils.isEmpty(sex)) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "性别不能为空");
            return;
        } else {
            user.setSex(sex);
        }

        career = etCareer.getText().toString().trim();
        if (TextUtils.isEmpty(career)) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "职业不能为空");
            return;
        } else {
            user.setCareer(career);
        }

        if (TextUtils.isEmpty(address)) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "地址不能为空");
            return;
        } else {
            user.setAddress(address);
        }

        info = tvDetail.getText().toString().trim();
        user.setInfo("" + info);

        mail = etMail.getText().toString().trim();
        if (TextUtils.isEmpty(mail) || mail.equals("null")) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "邮箱不能为空");
            return;
        } else {
            user.setEmail(mail);
        }

        user.update(ActUserInfoUpdate.this, userLocal.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActUserInfoUpdate.this, "更新成功");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActUserInfoUpdate.this, "更新失败： " + s);
            }
        });


    }


}
