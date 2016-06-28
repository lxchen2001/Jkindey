package com.liji.jkidney.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.liji.jkidney.R;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ActLifeHealthy extends ActBase {

    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    String title = "";

    //分页
    private int page = 1;

    //每页加载文章的数量
    private int num = 10;

    @Override
    void create(Bundle savedInstanceState) {
        x.view().inject(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_act_life_healthy;
    }

    @Override
    void initView(Bundle savedInstanceState) {

//        recyclerView = (RecyclerView) findViewById(R.id.rlv_content);
//        headView = (CustomeHeadView) findViewById(R.id.headview);

        title = this.getIntent().getStringExtra("title");
        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });


//        Parameters para = new Parameters();
//
//        para.put("num", "10");
//        para.put("page", "1");
//        ApiStoreSDK.execute("http://apis.baidu.com/txapi/health/health",
//                ApiStoreSDK.GET,
//                para,
//                new ApiCallBack() {
//
//                    @Override
//                    public void onSuccess(int status, String responseString) {
//                        Log.i("sdkdemo", "onSuccess");
//                        mTextView.setText(responseString);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("sdkdemo", "onComplete");
//                    }
//
//                    @Override
//                    public void onError(int status, String responseString, Exception e) {
//                        Log.i("sdkdemo", "onError, status: " + status);
//                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
//                        mTextView.setText(getStackTrace(e));
//                    }
//
//                });

    }

    @Override
    void setData(Bundle savedInstanceState) {

    }
}
