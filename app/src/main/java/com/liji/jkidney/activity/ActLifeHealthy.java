package com.liji.jkidney.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.liji.jkidney.R;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.x;

public class ActLifeHealthy extends ActBase {

    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    String title = "";

    @Override
    int getLayoutId() {
        return R.layout.activity_act_life_healthy;
    }

    @Override
    void initView(Bundle savedInstanceState) {
        x.view().inject(this);

        title = this.getIntent().getStringExtra("title");
        headView.setTitle(""+title);

        Parameters para = new Parameters();

        para.put("num", "10");
        para.put("page", "1");
        ApiStoreSDK.execute("http://apis.baidu.com/txapi/health/health",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        mTextView.setText(responseString);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("sdkdemo", "onComplete");
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        Log.i("sdkdemo", "onError, status: " + status);
                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                        mTextView.setText(getStackTrace(e));
                    }

                });

    }

    @Override
    void setData(Bundle savedInstanceState) {

    }
}
