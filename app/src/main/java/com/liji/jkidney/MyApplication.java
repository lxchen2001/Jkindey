package com.liji.jkidney;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.liji.jkidney.model.Config;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


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

        //ApiStoreSDK
        ApiStoreSDK.init(this, Config.ApiStoreSDKID);


        //设置图片加载方式
        initImageLoader();


    }

    /**
     * 设置图片加载方式
     */
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)//缓存一百张图片
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}
