package com.liji.jkidney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.liji.jkidney.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_launch)
public class ActLaunch extends ActBase {

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessageDelayed(0, 1000);

            }
        }).start();
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }




}
