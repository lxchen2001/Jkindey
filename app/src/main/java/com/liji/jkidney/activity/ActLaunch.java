package com.liji.jkidney.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.baidu.autoupdatesdk.AppUpdateInfo;
import com.baidu.autoupdatesdk.AppUpdateInfoForInstall;
import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.CPCheckUpdateCallback;
import com.liji.jkidney.R;
import com.liji.jkidney.dialog.DialogUpdate;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_launch)
public class ActLaunch extends ActBase {
    /* 强制更新 */
    private boolean forceUpdate = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(ActLaunch.this, MainActivity.class));
            ActLaunch.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mHandler.sendEmptyMessageDelayed(0, 1000);
//
//            }
//        }).start();

        setBaiDuUpdate();
    }

    private void moveToNext() {
        startActivity(new Intent(ActLaunch.this, MainActivity.class));
        ActLaunch.this.finish();

    }


    private void setBaiDuUpdate() {
        //        //静默更新
        //        BDAutoUpdateSDK.silenceUpdateAction(this);
        //查询更新
        BDAutoUpdateSDK.cpUpdateCheck(this, new MyCPCheckUpdateCallback());
    }

    private class MyCPCheckUpdateCallback implements CPCheckUpdateCallback {

        @Override
        public void onCheckUpdateCallback(AppUpdateInfo info, AppUpdateInfoForInstall infoForInstall) {
            if (infoForInstall != null && !TextUtils.isEmpty(infoForInstall.getInstallPath())) {//本地有最新更新包
                DialogUpdate update = new DialogUpdate(forceUpdate, info, infoForInstall, ActLaunch.this);
                update.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        moveToNext();
                    }


                });
                update.show();
            } else if (info != null) {//有更新，本地无下载，去下载，并监听下载进度
                DialogUpdate update = new DialogUpdate(forceUpdate, info, infoForInstall, ActLaunch.this);
                update.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        moveToNext();
                    }
                });
                update.show();
            } else {//当前版本为最新版本，暂无更新
                moveToNext();
            }

        }

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }


}
