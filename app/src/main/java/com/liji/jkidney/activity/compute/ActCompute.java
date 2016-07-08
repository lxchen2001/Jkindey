package com.liji.jkidney.activity.compute;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 计算肌酐清除率和肾小球过滤率
 */
@ContentView(R.layout.activity_compute)
public class ActCompute extends ActBase implements View.OnClickListener {
    public static String INTENT_TITLE = "title";
    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.ll_jigan)
    LinearLayout llJigan;
    @ViewInject(R.id.ll_shenxiaoqiu)
    LinearLayout llShenxiaoqiu;
    private String title = "";


    @Override
    protected void initView(Bundle savedInstanceState) {

        title = this.getIntent().getStringExtra(INTENT_TITLE);
        headview.setTitle("" + title);
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        //肌酐清除率
        llJigan.setOnClickListener(this);

        //肾小球过滤率计算
        llShenxiaoqiu.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_jigan://肌酐清除率
                intent.setClass(ActCompute.this, ActComputeJigan.class);
                break;

            case R.id.ll_shenxiaoqiu://肾小球过滤率计算
                intent.setClass(ActCompute.this, ActComputeShenxiaoqiu.class);
                break;
        }
        startActivity(intent);
    }
}
