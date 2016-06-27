package com.liji.jkidney.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.liji.jkidney.R;
import com.liji.jkidney.adapter.ChecksFragmentAdapter;
import com.liji.jkidney.model.M_ChcekInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChecks extends FragmentBase {


    //    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    ChecksFragmentAdapter mChecksFragmentAdapter;
    List<M_ChcekInfo> m_chcekInfos = new ArrayList<>();

    private String[] mChecksNames = {"肝功能", "肾功能", "尿蛋白", "尿常规", "尿四项", "体重", "血糖", "血压"};
    private int[] mChecksResId = {R.drawable.ic_xueye, R.drawable.ic_shengongneng,
            R.drawable.ic_niaodanbai, R.drawable.ic_niaochanggui,
            R.drawable.ic_niaosixiang, R.drawable.ic_tizhong,
            R.drawable.ic_xuetang, R.drawable.ic_xueya};
    private String[] mCheckContents = {};

    private List<M_ChcekInfo> getM_chcekInfos() {
        List<M_ChcekInfo> chcekInfos = new ArrayList<>();
        for (int i = 0; i < mCheckContents.length; i++) {
            M_ChcekInfo m_chcekInfo = new M_ChcekInfo();
            m_chcekInfo.setCheckName(mChecksNames[i]);
            m_chcekInfo.setCheckResourceId(mChecksResId[i]);
            chcekInfos.add(m_chcekInfo);
        }
        return chcekInfos;
    }

    public FragmentChecks() {

    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks_gridview, container, false);
//        ViewUtils.inject(this, view);

        recyclerView = (RecyclerView) view.findViewById(R.id.rlv_content);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        m_chcekInfos = getM_chcekInfos();

        mChecksFragmentAdapter = new ChecksFragmentAdapter(getContext(), m_chcekInfos);
        recyclerView.setAdapter(mChecksFragmentAdapter);

        return view;
    }

}
