package com.liji.jkidney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.liji.jkidney.R;

import org.xutils.view.annotation.ViewInject;

public class FragmentMy extends FragmentBase {

    public FragmentMy() {

    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ViewUtils.inject(this, view);


        return view;
    }


}
