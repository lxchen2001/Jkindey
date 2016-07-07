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
import com.liji.jkidney.activity.check.adapter.GangongnengAda;
import com.liji.jkidney.activity.check.adapter.NiaochangguiAda;
import com.liji.jkidney.activity.check.adapter.NiaodanbaiAda;
import com.liji.jkidney.activity.check.adapter.ShengongnengAda;
import com.liji.jkidney.activity.check.adapter.TizhongAda;
import com.liji.jkidney.activity.check.adapter.XuetangAda;
import com.liji.jkidney.activity.check.adapter.XueyaAda;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.model.CheckTypeId;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MCheckType;
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
public class ActCheckRecordList extends ActBase implements SwipeRefreshLayout.OnRefreshListener {

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
    GangongnengAda gangongnengAda;
    ShengongnengAda shengongnengAda;
    NiaochangguiAda niaochangguiAda;
    TizhongAda tizhongAda;
    XuetangAda xuetangAda;
    XueyaAda xueyaAda;
    List<MCheckType> checkTypeList = new ArrayList<>();

    String name;
    MyUser user;

    private int i = 0;

    //默认第一种为肝功能
    private int type = CheckTypeId.Gangongneng;

    @Override
    public void initView(Bundle savedInstanceState) {

        name = this.getIntent().getStringExtra("name");
        type = this.getIntent().getIntExtra("type", CheckTypeId.Gangongneng);

        headView.setTitle("" + name);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        //柱状统计图
        headView.setRightImgAction(R.drawable.ic_tongji, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                if (user != null) {
                    Intent intent = new Intent(ActCheckRecordList.this, ActCheckStatistics.class);
                    intent.putExtra(ActCheckStatistics.INTENT_USER, (Serializable) user);
                    intent.putExtra(ActCheckStatistics.INTENT_NAME, name);
                    intent.putExtra(ActCheckStatistics.INTENT_TYPE, type);
                    intent.putExtra(ActCheckStatistics.INTENT_CHECK, (Serializable) getCheckTypeList());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActCheckRecordList.this, ActLogin.class);
                    startActivity(intent);
                }

            }
        });


        //添加记录
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(ActCheckRecordList.this, ActCheckRecordOperation.class);
                    intent.putExtra(ActCheckRecordOperation.INTENT_CHECK, (Serializable) new MCheckType());
                    intent.putExtra(ActCheckRecordOperation.INTENT_USER, (Serializable) user);
                    intent.putExtra(ActCheckRecordOperation.INTENT_TYPE, type);
                    intent.putExtra(ActCheckRecordOperation.INTENT_ISADD, true);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActCheckRecordList.this, ActLogin.class);
                    startActivity(intent);
                }
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActCheckRecordList.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);


        //自动刷新
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadData(type);
            }
        });
        //设置数据适配器
        setAdapter(type);
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        user = User.getCurrentUser(this);

        //避免加载两次
        if (i != 0) {
            loadData(type);
        } else {
            i++;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        i = 0;
    }

    /**
     * 修改记录
     *
     * @param i
     * @param checkType
     */
    private void updatedata(int i, MCheckType checkType) {
        Intent intent = new Intent(ActCheckRecordList.this, ActCheckRecordOperation.class);
        intent.putExtra(ActCheckRecordOperation.INTENT_CHECK, (Serializable) checkType);
        intent.putExtra(ActCheckRecordOperation.INTENT_USER, (Serializable) user);
        intent.putExtra(ActCheckRecordOperation.INTENT_TYPE, i);
        intent.putExtra(ActCheckRecordOperation.INTENT_ISADD, false);
        startActivity(intent);
    }

    /**
     * 获取列表数据传入柱状图统计
     *
     * @return
     */
    private List<MCheckType> getCheckTypeList() {
        List<MCheckType> list = new ArrayList<>();
        switch (type) {
            case CheckTypeId.Gangongneng://肝功能
                list = gangongnengAda.getData();
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                list = shengongnengAda.getData();
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                list = niaodanbaiAda.getData();
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                list = niaochangguiAda.getData();
                break;
            case CheckTypeId.Tizhong:// 体重
                list = tizhongAda.getData();
                break;
            case CheckTypeId.Xuetang:// 血糖
                list = xuetangAda.getData();
                break;
            case CheckTypeId.Xueya:// 血压
                list = xueyaAda.getData();
                break;
        }
        return list;
    }

    /**
     * 重新加载数据
     */
    private void loadData(final int typeCheck) {
        isRefresh = true;
        if (user != null) {
            BmobQuery<MCheckType> query = new BmobQuery<>();
            query.addWhereEqualTo("author", user);
            query.addWhereEqualTo("type", typeCheck);
            query.setLimit(1000);
            query.findObjects(ActCheckRecordList.this, new FindListener<MCheckType>() {
                @Override
                public void onSuccess(List<MCheckType> list) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (list.isEmpty() || (list != null && list.size() == 0)) {
                        setEmptyView(typeCheck);
                    } else {
                        if (isRefresh) {
                            setData(typeCheck, list);
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
            setEmptyView(typeCheck);
        }

    }

    /**
     * 检查结果设置
     *
     * @param typelist
     * @param list
     */
    public void setData(int typelist, List<MCheckType> list) {
        switch (typelist) {
            case CheckTypeId.Gangongneng://肝功能
                gangongnengAda.setNewData(list);
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                shengongnengAda.setNewData(list);
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                niaodanbaiAda.setNewData(list);
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                niaochangguiAda.setNewData(list);
                break;
            case CheckTypeId.Tizhong:// 体重
                tizhongAda.setNewData(list);
                break;
            case CheckTypeId.Xuetang:// 血糖
                xuetangAda.setNewData(list);
                break;
            case CheckTypeId.Xueya:// 血压
                xueyaAda.setNewData(list);
                break;
        }
    }


    /**
     * 无数据时设置空view提示
     *
     * @param typeEmpty
     */

    public void setEmptyView(int typeEmpty) {
        switch (typeEmpty) {
            case CheckTypeId.Gangongneng://肝功能
                gangongnengAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                shengongnengAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                niaodanbaiAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                niaochangguiAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;
            case CheckTypeId.Tizhong:// 体重
                tizhongAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;
            case CheckTypeId.Xuetang:// 血糖
                xuetangAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;
            case CheckTypeId.Xueya:// 血压
                xueyaAda.setEmptyView(JViewsUtils.getEmptyView(ActCheckRecordList.this, recyclerview));
                break;

        }
    }

    @Override
    public void onRefresh() {
        loadData(type);
    }

    /**
     * 设置数据适配器
     *
     * @param adapterType
     */
    public void setAdapter(int adapterType) {
        switch (adapterType) {
            case CheckTypeId.Gangongneng://肝功能
                gangongnengAda = new GangongnengAda(checkTypeList);
                gangongnengAda.openLoadAnimation();
                gangongnengAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Gangongneng, (MCheckType) gangongnengAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(gangongnengAda);
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                shengongnengAda = new ShengongnengAda(checkTypeList);
                shengongnengAda.openLoadAnimation();
                shengongnengAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Shengongneng, (MCheckType) shengongnengAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(shengongnengAda);
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                niaodanbaiAda = new NiaodanbaiAda(checkTypeList);
                niaodanbaiAda.openLoadAnimation();
                niaodanbaiAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Niaodanbai, (MCheckType) niaodanbaiAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(niaodanbaiAda);
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                niaochangguiAda = new NiaochangguiAda(checkTypeList);
                niaochangguiAda.openLoadAnimation();
                niaochangguiAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Niaochanggui, (MCheckType) niaochangguiAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(niaochangguiAda);
                break;
            case CheckTypeId.Tizhong:// 体重
                tizhongAda = new TizhongAda(checkTypeList);
                tizhongAda.openLoadAnimation();
                tizhongAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Tizhong, (MCheckType) tizhongAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(tizhongAda);
                break;
            case CheckTypeId.Xuetang:// 血糖
                xuetangAda = new XuetangAda(checkTypeList);
                xuetangAda.openLoadAnimation();
                xuetangAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Xuetang, (MCheckType) xuetangAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(xuetangAda);
                break;
            case CheckTypeId.Xueya:// 血压
                xueyaAda = new XueyaAda(checkTypeList);
                xueyaAda.openLoadAnimation();
                xueyaAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        updatedata(CheckTypeId.Xueya, (MCheckType) xueyaAda.getData().get(i));
                    }
                });
                recyclerview.setAdapter(xueyaAda);
                break;


        }
    }
}
