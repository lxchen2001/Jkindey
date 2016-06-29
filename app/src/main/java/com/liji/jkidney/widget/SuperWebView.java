package com.liji.jkidney.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.a.a.V;
import com.liji.jkidney.R;

/**
 * 对webview进行封装
 * 包含加载进度，加载失败等操作
 * 作者：liji on 2016/6/29 09:45
 * 邮箱：lijiwork@sina.com
 */
public class SuperWebView extends WebView {

    private ProgressBar progressbar;

    public SuperWebView(Context context) {
        this(context, null);
    }

    public SuperWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig(context, attrs);
    }

    private void initConfig(Context context, AttributeSet attrs) {

        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_statue_selector);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        WebSettings webSettings = this.getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowFileAccess(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        // 设置webview自适应屏幕大小
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);//关键点
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
         * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }

        webSettings.setJavaScriptEnabled(true); // Support JavaScript
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });


        this.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                view.setVisibility(View.VISIBLE);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    progressbar.setVisibility(View.VISIBLE);
                    progressbar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);

            }

        });
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }


}
