package com.liji.jkidney.activity.post;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.fragment.FragmentBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.JViewsUtils;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.Recyclerview.RecycleViewDivider;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPost extends FragmentBase implements SwipeRefreshLayout.OnRefreshListener {


    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    @ViewInject(R.id.fab)
    FloatingActionButton fab;

    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isRefresh = true;
    private int i = 0;

    private List<M_Post> m_postList = new ArrayList<>();
    private PostAda postAda = null;

    MyUser user;

    public FragmentPost() {
    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        x.view().inject(this, view);

        headView.setTitle(getResources().getString(R.string.fragment_info));
        headView.setBackgroundColor(getResources().getColor(R.color.color_tab_post));


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        //设置item的分割线
        recyclerview.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));


        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        });


        postAda = new PostAda(getContext(), m_postList);
        //设置数据适配器
        postAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(getContext(), ActPostDetail.class);
                intent.putExtra(ActPostDetail.POST_DETAIL, (Serializable) postAda.getData().get(i));
                startActivity(intent);

            }
        });
        postAda.openLoadAnimation();
        recyclerview.setAdapter(postAda);

        //添加记录
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(getContext(), ActPostAdd.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), ActLogin.class);
                    startActivity(intent);
                }
            }
        });


        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user = User.getCurrentUser(getContext());
        if (i != 0) {
            loadData();
        } else {
            i++;
        }
    }

    private void loadData() {
        isRefresh = true;
        BmobQuery<M_Post> query = new BmobQuery<>();
        query.order("-createdAt");
        query.include("author");
        query.findObjects(getContext(), new FindListener<M_Post>() {
            @Override
            public void onSuccess(List<M_Post> list) {
                swipeRefreshLayout.setRefreshing(false);
                if (list.isEmpty() || (list != null && list.size() == 0)) {
                    postAda.setEmptyView(JViewsUtils.getEmptyView(getContext(), recyclerview));
                } else {
                    if (isRefresh) {
                        postAda.setNewData(list);
                    }
                }

            }

            @Override
            public void onError(int i, String s) {
                swipeRefreshLayout.setRefreshing(false);
                JToastUtils.showToast(getContext(), "" + s);

            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
