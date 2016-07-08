package com.liji.jkidney.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.check.ActCheckRecordList;
import com.liji.jkidney.activity.check.ActNoteList;
import com.liji.jkidney.activity.compute.ActCompute;
import com.liji.jkidney.model.CheckTypeId;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChecks extends FragmentBase implements View.OnClickListener {
    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.ll_type_1)
    LinearLayout llType1;
    @ViewInject(R.id.ll_type_2)
    LinearLayout llType2;
    @ViewInject(R.id.ll_type_3)
    LinearLayout llType3;
    @ViewInject(R.id.ll_type_4)
    LinearLayout llType4;
    @ViewInject(R.id.ll_type_5)
    LinearLayout llType5;
    @ViewInject(R.id.ll_type_6)
    LinearLayout llType6;
    @ViewInject(R.id.ll_type_7)
    LinearLayout llType7;
    @ViewInject(R.id.ll_type_8)
    LinearLayout llType8;
    @ViewInject(R.id.ll_type_9)
    LinearLayout llType9;
    private String[] mChecksNames = {"肝功能", "肾功能", "尿蛋白", "血压", "体重", "血糖", "记事本", "计算器"};

    public FragmentChecks() {

    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks_gridview, container, false);
        x.view().inject(this, view);

        headView.setTitle("检查");
        initClick();
        return view;
    }

    private void initClick() {
        llType1.setOnClickListener(this);
        llType2.setOnClickListener(this);
        llType3.setOnClickListener(this);
        llType4.setOnClickListener(this);
        llType5.setOnClickListener(this);
        llType6.setOnClickListener(this);
        llType7.setOnClickListener(this);
        llType8.setOnClickListener(this);
        llType9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ll_type_7) {//笔记本
            Intent intent = new Intent();
            intent.setClass(getContext(), ActNoteList.class);
            intent.putExtra(ActNoteList.INTENT_TITLE, mChecksNames[6]);
            startActivity(intent);


        } else if (v.getId() == R.id.ll_type_8) {//肌酐清除率
            Intent intent = new Intent();
            intent.setClass(getContext(), ActCompute.class);
            intent.putExtra(ActCompute.INTENT_TITLE, mChecksNames[7]);
            startActivity(intent);

        } else {
            String name = "";
            String info = "";
            int type = 0;
            Intent intent = new Intent();
            intent.setClass(getContext(), ActCheckRecordList.class);
            switch (v.getId()) {
                case R.id.ll_type_1:
                    name = mChecksNames[0];
                    type = CheckTypeId.Gangongneng;
                    break;
                case R.id.ll_type_2:
                    name = mChecksNames[1];
                    type = CheckTypeId.Shengongneng;
                    break;
                case R.id.ll_type_3:
                    name = mChecksNames[2];
                    type = CheckTypeId.Niaodanbai;
                    break;
                case R.id.ll_type_4:
                    name = mChecksNames[3];
                    type = CheckTypeId.Xueya;
                    break;
                case R.id.ll_type_5:
                    name = mChecksNames[4];
                    type = CheckTypeId.Tizhong;
                    break;
                case R.id.ll_type_6:
                    name = mChecksNames[5];
                    type = CheckTypeId.Xuetang;
                    break;

            }
            intent.putExtra("name", name);
            intent.putExtra("info", info);
            intent.putExtra("type", type);
            getContext().startActivity(intent);
        }

    }

}
