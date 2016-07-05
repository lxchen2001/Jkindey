package com.liji.jkidney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liji.jkidney.R;
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

    @Override
    protected void initView(Bundle savedInstanceState) {


        headview.setTitle("设置");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        btnLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut(ActSetting.this);
                JToastUtils.showToast(ActSetting.this, "成功退出");
                finish();
            }
        });


    }


}
