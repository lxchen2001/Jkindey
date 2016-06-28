package com.liji.jkidney.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActLifeHealthy;
import com.liji.jkidney.adapter.ChecksFragmentAdapter;
import com.liji.jkidney.adapter.InfoFragmentAdapter;
import com.liji.jkidney.model.M_ChcekInfo;
import com.liji.jkidney.model.M_Info;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfo extends FragmentBase {

    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    InfoFragmentAdapter infoFragmentAdapter;
    List<M_Info> infos = new ArrayList<>();

    private String[] mNewsType = {"生活健康", "健康资讯", "健康知识"};
    private int[] mNewsTypePic = {R.drawable.ic_life_healthy, R.drawable.ic_healthy_news, R.drawable.ic_healthy_knowledge};
    private String[] mNewsTypeContent = {"健康热点资讯，科学传播，拒绝谣言", "提供及时的健康信息 ，做一个及时的健康新闻资讯平台， 提供专业的健康新闻资讯。", "实用的健康知识,健康小常识,生活小常识,健康保健知识,健康饮食,养生保健知识,居家常识.."};


    public FragmentInfo() {

    }

    private List<M_Info> getInfoList() {
        List<M_Info> infoLists = new ArrayList<>();
        for (int i = 0; i < mNewsType.length; i++) {
            M_Info info = new M_Info();
            info.setmNewsType(mNewsType[i]);
            info.setmNewsTypeContent(mNewsTypeContent[i]);
            info.setmNewsTypePic(mNewsTypePic[i]);
            infoLists.add(info);

        }
        return infoLists;
    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ViewUtils.inject(this, view);
        headView.setTitle("资讯");

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        infos = getInfoList();

        infoFragmentAdapter = new InfoFragmentAdapter(getContext(), infos, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                int pos = (Integer) obj[0];
                Intent go = new Intent();
                switch (pos) {
                    case 0:
                        go.setClass(getContext(), ActLifeHealthy.class);
                        go.putExtra("title", mNewsType[pos]);
                        getContext().startActivity(go);
                        break;
                }
            }
        });
        recyclerView.setAdapter(infoFragmentAdapter);
        return view;
    }

}
