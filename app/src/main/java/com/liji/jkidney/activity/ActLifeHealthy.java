package com.liji.jkidney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.adapter.LifeHealthyInfoAda;
import com.liji.jkidney.model.info.M_Life_Healthy;
import com.liji.jkidney.model.info.URL;
import com.liji.jkidney.utils.JLogUtils;
import com.liji.jkidney.utils.JSONHandleUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_act_life_healthy)
public class ActLifeHealthy extends ActBase implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    LifeHealthyInfoAda lifeHealthyInfoAda;

    boolean isRefresh = true;
    String title = "";

    //分页
    private int page = 1;

    //每页加载文章的数量
    private int num = 10;

    List<M_Life_Healthy> life_healthies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public  void initView(Bundle savedInstanceState) {
        title = this.getIntent().getStringExtra("title");
        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });


        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                reLoadData();
            }
        });

        setData(savedInstanceState);
    }


    private void setData(Bundle savedInstanceState) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActLifeHealthy.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        lifeHealthyInfoAda = new LifeHealthyInfoAda(life_healthies);
        lifeHealthyInfoAda.openLoadAnimation();
        lifeHealthyInfoAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                M_Life_Healthy life_healthy = (M_Life_Healthy) lifeHealthyInfoAda.getData().get(i);
                Intent intent = new Intent();
                intent.setClass(ActLifeHealthy.this, ActWebShow.class);
                intent.putExtra("title", "" + life_healthy.getTitle());
                intent.putExtra("url", "" + life_healthy.getUrl());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(lifeHealthyInfoAda);
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);

        //加载更多
        lifeHealthyInfoAda.openLoadMore(10, true);
        lifeHealthyInfoAda.setOnLoadMoreListener(this);
    }

    /**
     * 刷新数据
     */
    private void reLoadData() {
        page = 1;
        isRefresh = true;
        requestData(page);
    }

    /**
     * 加载更多
     */
    private void loadMoreData() {
        page++;
        isRefresh = false;
        requestData(page);
    }

    /**
     * 请求数据
     *
     * @param page
     */
    private void requestData(int page) {
        Parameters para = new Parameters();
        para.put("num", "10");
        para.put("page", "" + page);
        ApiStoreSDK.execute(URL.url_life_healthy,
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        JLogUtils.Json(responseString);
                        try {
                            life_healthies = JSONHandleUtils.parseResponseArray(responseString, M_Life_Healthy.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (isRefresh) {
                            lifeHealthyInfoAda.setNewData(life_healthies);
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            lifeHealthyInfoAda.notifyDataChangedAfterLoadMore(life_healthies, true);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {

                    }
                });
    }

    @Override
    public void onRefresh() {
        JLogUtils.D("onRefresh");
        reLoadData();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMoreData();
    }
}
