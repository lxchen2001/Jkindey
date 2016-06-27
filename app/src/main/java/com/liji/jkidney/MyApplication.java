package com.liji.jkidney;

import android.app.Application;

import com.liji.jkidney.model.Config;

import org.xutils.x;

import cn.bmob.v3.Bmob;

/**
 * 作者：liji on 2016/6/24 14:56
 * 邮箱：lijiwork@sina.com
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //bmob init
        Bmob.initialize(this, Config.APPID);

        //xutils init
        x.Ext.init(this);

    }
}
