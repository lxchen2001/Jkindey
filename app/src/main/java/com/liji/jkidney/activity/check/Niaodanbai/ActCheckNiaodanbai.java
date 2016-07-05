package com.liji.jkidney.activity.check.Niaodanbai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.adapter.LifeHealthyInfoAda;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.M_Check_niaodanbai;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JViewsUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 尿蛋白检测
 */
@ContentView(R.layout.activity_check_niaodanbai)
public class ActCheckNiaodanbai extends ActBase implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    @ViewInject(R.id.fab)
    FloatingActionButton fab;

    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isRefresh = true;

    NiaodanbaiAda niaodanbaiAda;
    List<M_Check_niaodanbai> m_check_niaodanbais = new ArrayList<>();

    String name;
    String checkInfo;
    MyUser user;

    @Override
    public void initView(Bundle savedInstanceState) {

        name = this.getIntent().getStringExtra("name");
        checkInfo = this.getIntent().getStringExtra("info");

        headView.setTitle("" + name);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });


        //添加记录
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(ActCheckNiaodanbai.this, ActCheckNiaodanbaiAdd.class);
                    intent.putExtra("user", (Serializable) user);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActCheckNiaodanbai.this, ActLogin.class);
                    startActivity(intent);
                }
            }
        });


        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActCheckNiaodanbai.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);


        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        });

        niaodanbaiAda = new NiaodanbaiAda(m_check_niaodanbais);
        niaodanbaiAda.openLoadAnimation();
        niaodanbaiAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                updatedata(i);
            }


        });
        recyclerview.setAdapter(niaodanbaiAda);
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        user = User.getCurrentUser(this);
        loadData();
    }

    private void updatedata(int i) {
        M_Check_niaodanbai check_niaodanbai = (M_Check_niaodanbai) niaodanbaiAda.getData().get(i);
        Intent intent = new Intent(ActCheckNiaodanbai.this, ActCheckNiaodanbaiUpdate.class);
        intent.putExtra("check", (Serializable) check_niaodanbai);
        intent.putExtra("user", (Serializable) user);
        startActivity(intent);
    }

    /**
     * 重新加载数据
     */
    private void loadData() {
        isRefresh = true;

        if (user != null) {
            BmobQuery<M_Check_niaodanbai> query = new BmobQuery<>();
            query.addWhereEqualTo("author", user);
            query.setLimit(1000);
            query.findObjects(ActCheckNiaodanbai.this, new FindListener<M_Check_niaodanbai>() {
                @Override
                public void onSuccess(List<M_Check_niaodanbai> list) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (list.isEmpty() || (list != null && list.size() == 0)) {
                        niaodanbaiAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckNiaodanbai.this, recyclerview));
                    } else {
                        if (isRefresh) {
                            niaodanbaiAda.setNewData(list);
                        }
                    }
                }

                @Override
                public void onError(int i, String s) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        } else {
            swipeRefreshLayout.setRefreshing(false);
            niaodanbaiAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckNiaodanbai.this, recyclerview));
        }

    }

    @Override
    public void onRefresh() {
        loadData();
    }

}
