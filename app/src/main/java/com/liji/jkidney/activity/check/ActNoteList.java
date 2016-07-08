package com.liji.jkidney.activity.check;

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
import com.liji.jkidney.activity.check.adapter.NoteAda;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.model.Type;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MNote;
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
 * 记事本
 */
@ContentView(R.layout.activity_note)
public class ActNoteList extends ActBase implements SwipeRefreshLayout.OnRefreshListener {
    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    @ViewInject(R.id.fab)
    FloatingActionButton fab;

    @ViewInject(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isRefresh = true;
    MyUser user;
    private static int i = 0;
    NoteAda noteAda;
    List<MNote> mNotesList = new ArrayList<>();

    private String title = "";
    public static String INTENT_TITLE = "title";

    @Override
    protected void initView(Bundle savedInstanceState) {

        title = this.getIntent().getStringExtra(INTENT_TITLE);
        user = User.getCurrentUser(this);
        headView.setTitle("" + title);
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
                    Intent intent = new Intent(ActNoteList.this, ActNoteOperation.class);
                    intent.putExtra(ActNoteOperation.INTENT_TYPE, Type.Note_Add);
                    intent.putExtra(ActNoteOperation.INTENT_Data, (Serializable) new MNote());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActNoteList.this, ActLogin.class);
                    startActivity(intent);
                }
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActNoteList.this);
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
        //设置数据适配器
        noteAda = new NoteAda(mNotesList);
        noteAda.openLoadAnimation();
        noteAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ActNoteList.this, ActNoteOperation.class);
                intent.putExtra(ActNoteOperation.INTENT_TYPE, Type.Note_Edit);
                intent.putExtra(ActNoteOperation.INTENT_Data, (Serializable) noteAda.getData().get(i));
                startActivity(intent);


            }
        });
        recyclerview.setAdapter(noteAda);
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void loadData() {

        isRefresh = true;
        if (user != null) {
            BmobQuery<MNote> query = new BmobQuery<>();
            query.addWhereEqualTo("author", user);
            query.setLimit(1000);
            query.findObjects(ActNoteList.this, new FindListener<MNote>() {
                @Override
                public void onSuccess(List<MNote> list) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (list.isEmpty() || (list != null && list.size() == 0)) {
                        noteAda.setEmptyView(JViewsUtils.getEmptyView(ActNoteList.this, recyclerview));
                    } else {
                        if (isRefresh) {
                            noteAda.setNewData(list);
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
            noteAda.setEmptyView(JViewsUtils.getEmptyView(ActNoteList.this, recyclerview));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //避免加载两次
        if (i != 0) {
            user = User.getCurrentUser(this);
            loadData();
        } else {
            i++;
        }
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
