package com.liji.jkidney.activity.user.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.activity.message.ActMessage;
import com.liji.jkidney.activity.post.ActPostAdd;
import com.liji.jkidney.activity.post.ActPostDetail;
import com.liji.jkidney.activity.post.PostAda;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.JViewsUtils;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.Recyclerview.RecycleViewDivider;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

@ContentView(R.layout.activity_my_post)
public class ActMyPost extends ActBase implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isRefresh = true;
    private int i = 0;

    private List<M_Post> m_postList = new ArrayList<>();
    private PostAda postAda = null;

    MyUser user;

    @Override
    protected void initView(Bundle savedInstanceState) {
        user = User.getCurrentUser(ActMyPost.this);
        headView.setTitle("我的帖子");
        headView.setBackgroundColor(getResources().getColor(R.color.color_tab_post));


        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActMyPost.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        //设置item的分割线
        recyclerview.addItemDecoration(new RecycleViewDivider(ActMyPost.this, LinearLayoutManager.HORIZONTAL));


        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        });


        postAda = new PostAda(ActMyPost.this, m_postList);
        //设置数据适配器
        postAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ActMyPost.this, ActPostDetail.class);
                intent.putExtra(ActPostDetail.POST_DETAIL, (Serializable) postAda.getData().get(i));
                startActivity(intent);

            }
        });
        postAda.openLoadAnimation();
        recyclerview.setAdapter(postAda);


        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void loadData() {
        isRefresh = true;
        BmobQuery<M_Post> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("author", user);
        query.include("author");
        query.findObjects(ActMyPost.this, new FindListener<M_Post>() {
            @Override
            public void onSuccess(List<M_Post> list) {
                swipeRefreshLayout.setRefreshing(false);
                if (list.isEmpty() || (list != null && list.size() == 0)) {
                    postAda.setEmptyView(JViewsUtils.getEmptyView(ActMyPost.this, recyclerview));
                } else {
                    if (isRefresh) {
                        postAda.setNewData(list);
                    }
                }

            }

            @Override
            public void onError(int i, String s) {
                swipeRefreshLayout.setRefreshing(false);
                JToastUtils.showToast(ActMyPost.this, "" + s);

            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
