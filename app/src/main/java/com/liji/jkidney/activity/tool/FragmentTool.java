package com.liji.jkidney.activity.tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liji.jkidney.R;
import com.liji.jkidney.fragment.FragmentBase;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


public class FragmentTool extends FragmentBase implements View.OnClickListener {


    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.ll_type_note)
    LinearLayout ll_type_note;
    @ViewInject(R.id.ll_type_compute)
    LinearLayout ll_type_compute;


    private String[] mChecksNames = {"记事本", "计算器"};


    public FragmentTool() {

    }


    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool, container, false);
        x.view().inject(this, view);
        headView.setTitle(getResources().getString(R.string.fragment_tool));
        headView.setBackgroundColor(getResources().getColor(R.color.color_tab_tool));

        initView();
        return view;
    }

    private void initView() {
        ll_type_note.setOnClickListener(this);
        ll_type_compute.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_type_note) {//笔记本
            Intent intent = new Intent();
            intent.setClass(getContext(), ActNoteList.class);
            intent.putExtra(ActNoteList.INTENT_TITLE, mChecksNames[0]);
            startActivity(intent);

        } else if (v.getId() == R.id.ll_type_compute) {//肌酐清除率
            Intent intent = new Intent();
            intent.setClass(getContext(), ActCompute.class);
            intent.putExtra(ActCompute.INTENT_TITLE, mChecksNames[1]);
            startActivity(intent);

        }
    }
}
