package com.liji.jkidney.activity;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liji.jkidney.R;
import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

public abstract class ActBase extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView(savedInstanceState);
    }


    protected abstract void initView(Bundle savedInstanceState);

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
