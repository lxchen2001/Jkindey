package com.liji.jkidney.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.model.User;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

@ContentView(R.layout.activity_setting)
public class ActSetting extends ActBase {


    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.btn_loginout)
    Button btnLoginout;

    @ViewInject(R.id.tv_version)
    TextView tvVersion;

    @ViewInject(R.id.ll_about)
    LinearLayout llAbout;


    @Override
    protected void initView(Bundle savedInstanceState) {


        headview.setTitle("设置");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        if (User.getCurrentUser(this) != null) {
            btnLoginout.setVisibility(View.VISIBLE);
            btnLoginout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BmobUser.logOut(ActSetting.this);
                    JToastUtils.showToast(ActSetting.this, "成功退出");
                    finish();
                }
            });
        } else {
            btnLoginout.setVisibility(View.GONE);
        }


        try {
            tvVersion.setText("当前版本号：" + this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActSetting.this, ActAbout.class));
            }
        });


    }


}
