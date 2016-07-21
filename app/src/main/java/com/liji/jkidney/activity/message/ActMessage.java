package com.liji.jkidney.activity.message;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.message.MMessage;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.JViewsUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.Recyclerview.RecycleViewDivider;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

@ContentView(R.layout.activity_act_message)
public class ActMessage extends ActBase implements SwipeRefreshLayout.OnRefreshListener {
    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isRefresh = true;
    MyUser myUser;

    MessageAda messageAda;
    List<MMessage> messageList = new ArrayList<>();


    @Override
    protected void initView(Bundle savedInstanceState) {
        myUser = User.getCurrentUser(ActMessage.this);
        headView.setTitle("我的消息");
        headView.setBackgroundColor(getResources().getColor(R.color.color_tab_check));
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });


        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActMessage.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        //设置item的分割线
        recyclerview.addItemDecoration(new RecycleViewDivider(ActMessage.this, LinearLayoutManager.HORIZONTAL));


        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        });

        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);

        messageAda = new MessageAda(messageList);
        recyclerview.setAdapter(messageAda);

    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void loadData() {
        isRefresh = true;
        BmobQuery<MMessage> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("author", User.getCurrentUser(ActMessage.this));
        query.setLimit(1000);
        query.findObjects(ActMessage.this, new FindListener<MMessage>() {
            @Override
            public void onSuccess(List<MMessage> list) {
                swipeRefreshLayout.setRefreshing(false);
                if (list.isEmpty() || (list != null && list.size() == 0)) {
                    messageAda.setEmptyView(JViewsUtils.getEmptyView(ActMessage.this, recyclerview));
                } else {
                    if (isRefresh) {
                        messageAda.setNewData(list);
                    }
                }

            }

            @Override
            public void onError(int i, String s) {
                swipeRefreshLayout.setRefreshing(false);
                JToastUtils.showToast(ActMessage.this, "" + s);

            }
        });


    }
}
