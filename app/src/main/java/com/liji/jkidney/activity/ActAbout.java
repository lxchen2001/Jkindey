package com.liji.jkidney.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.liji.jkidney.R;
import com.liji.jkidney.model.Config;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_about)
public class ActAbout extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.ll_blog)
    LinearLayout llBlog;
    @ViewInject(R.id.ll_github)
    LinearLayout llGithub;

    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("关于开发者");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        llBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL(Config.URL_BLOG);
            }
        });

        llGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL(Config.URL_GITHUB);
            }
        });

    }

    private void gotoURL(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        startActivity(intent);
    }


}
