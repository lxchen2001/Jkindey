package com.liji.jkidney.activity.info;

import android.os.Bundle;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.SuperWebView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_act_web_show)
public class ActWebShow extends ActBase {

    @ViewInject(R.id.webview)
    SuperWebView webView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public  void initView(Bundle savedInstanceState) {

        title = this.getIntent().getStringExtra("title");
        url = this.getIntent().getStringExtra("url");

        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        webView.loadUrl(url);

    }



}
