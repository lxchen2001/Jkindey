package com.liji.jkidney;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.liji.jkidney.model.Config;

/**
 * 作者：liji on 2016/6/24 14:56
 * 邮箱：lijiwork@sina.com
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, Config.APPID, Config.APPKEY);
    }
}
