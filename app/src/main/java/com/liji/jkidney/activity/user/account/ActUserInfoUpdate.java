package com.liji.jkidney.activity.user.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.dev.androidutils.utils.SexSelectPop.SexSelectWheelView;
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

import java.util.regex.Pattern;

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
    @ViewInject(R.id.tv_usename)
    TextView tvUsename;
    @ViewInject(R.id.ll_sex)
    LinearLayout llSex;
    @ViewInject(R.id.et_career)
    EditText etCareer;

    @ViewInject(R.id.tv_address)
    TextView tvAddress;
    @ViewInject(R.id.tv_detail)
    TextView tvDetail;
    @ViewInject(R.id.ll_address)
    LinearLayout llAddress;
    @ViewInject(R.id.ll_detail)
    LinearLayout llDetail;

    @ViewInject(R.id.item_ll_password)
    LinearLayout item_ll_password;


    MyUser userLocal;
    String nickname;
    Integer age;
    String sexValue;
    String career;
    String address;
    String info;


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

        llSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SexSelectWheelView sexSelectWheelView = new SexSelectWheelView(ActUserInfoUpdate.this);
                sexSelectWheelView.setOnSexSelectListener(new SexSelectWheelView.OnSexSelectListener() {
                    @Override
                    public void onSelected(String sex) {
                        sexValue = sex;
                        tvSex.setText("" + sexValue);
                    }
                });
                sexSelectWheelView.show();
            }
        });

        llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActUserInfoUpdate.this, ActPersonalNote.class);
                intent.putExtra(ActPersonalNote.NOTE, info);
                startActivityForResult(intent, 0);
            }
        });


        //密码相关
        item_ll_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActUserInfoUpdate.this, ActPasswordRest.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                info = data.getStringExtra(ActPersonalNote.NOTE);
                tvDetail.setText("" + info);
            }
        }
    }

    private void setDefaultInfo() {
        userLocal = User.getCurrentUser(ActUserInfoUpdate.this);
        tvUsename.setText("" + userLocal.getUsername());
        nickname = userLocal.getNickname();
        age = userLocal.getAge();
        sexValue = userLocal.getSex();
        address = userLocal.getAddress();
        info = userLocal.getInfo();
        career = userLocal.getCareer();
        etNickname.setText("" + nickname);
        etAge.setText("" + age);
        tvSex.setText("" + sexValue);
        tvAddress.setText("" + address);
        tvDetail.setText("" + info);
        etCareer.setText("" + career);


    }


    private void doSubmit() {

        MyUser user = new MyUser();
        nickname = etNickname.getText().toString().trim();
        if (!Pattern.matches("^[0-9a-zA-Z_\\u4e00-\\u9fa5\\*]{1,20}$", nickname)) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "昵称由大小写英文字母、中文、下划线、数字组成，且不超过20个字符");
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

        sexValue = tvSex.getText().toString().trim();
        if (TextUtils.isEmpty(sexValue)) {
            JToastUtils.showToast(ActUserInfoUpdate.this, "性别不能为空");
            return;
        } else {
            user.setSex(sexValue);
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
