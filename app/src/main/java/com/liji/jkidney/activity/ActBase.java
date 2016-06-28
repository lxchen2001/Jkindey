package com.liji.jkidney.activity;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liji.jkidney.R;

import org.xutils.x;

public abstract class ActBase extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        create(savedInstanceState);
        initView(savedInstanceState);
        setData(savedInstanceState);

    }

    abstract void create(Bundle savedInstanceState);

    abstract int getLayoutId();

    abstract void initView(Bundle savedInstanceState);

    abstract void setData(Bundle savedInstanceState);

}
