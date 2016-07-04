package com.liji.jkidney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActInfoShowDetail;
import com.liji.jkidney.adapter.HealthyKnowledgeAda;
import com.liji.jkidney.model.info.M_HealthyKnowledgeList;
import com.liji.jkidney.model.info.URL;
import com.liji.jkidney.utils.JLogUtils;
import com.liji.jkidney.utils.JSONHandleUtils;

import org.json.JSONException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康知识每个tab对应下面的fragment
 */
public class FragmentHealthyKnowledgeViewpager extends FragmentBase  implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String ARG_ID = "id";

    private int id;

    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isRefresh = true;

    //分页
    private int page = 1;

    //每页加载文章的数量
    private int num = 20;

    List<M_HealthyKnowledgeList> healthyInfoLists = new ArrayList<>();
    HealthyKnowledgeAda healthyKnowledgeAda;



    public FragmentHealthyKnowledgeViewpager() {
    }

    public static FragmentHealthyKnowledgeViewpager newInstance(int id) {
        FragmentHealthyKnowledgeViewpager f = new FragmentHealthyKnowledgeViewpager();
        Bundle b = new Bundle();
        b.putInt(ARG_ID, id);
        f.setArguments(b);
        return f;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = this.getArguments().getInt(ARG_ID);
    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthyinfo_viewpager, container, false);
        x.view().inject(this, view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        healthyKnowledgeAda = new HealthyKnowledgeAda(healthyInfoLists);
        healthyKnowledgeAda.openLoadAnimation();


        //健康知识详情
        healthyKnowledgeAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                M_HealthyKnowledgeList life_healthy = (M_HealthyKnowledgeList) healthyKnowledgeAda.getData().get(i);
                Intent intent = new Intent();
                intent.setClass(getContext(), ActInfoShowDetail.class);
                intent.putExtra("title", "" + life_healthy.getTitle());
                intent.putExtra("id", "" + life_healthy.getId());
                intent.putExtra("type", 1);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(healthyKnowledgeAda);
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);

        //加载更多
        healthyKnowledgeAda.openLoadMore(10, true);
        healthyKnowledgeAda.setOnLoadMoreListener(this);


        //自动加载
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                reLoadData();
            }
        });

        return view;
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
        para.put("id", id);
        para.put("rows", "20");
        para.put("page", "" + page);
        ApiStoreSDK.execute(URL.url_knowledge_list,
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        JLogUtils.Json(responseString);
                        try {
                            healthyInfoLists = JSONHandleUtils.parseResponseArray(responseString, M_HealthyKnowledgeList.class, 2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (isRefresh) {
                            healthyKnowledgeAda.setNewData(healthyInfoLists);
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            healthyKnowledgeAda.notifyDataChangedAfterLoadMore(healthyInfoLists, true);
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
        reLoadData();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMoreData();
    }
}
