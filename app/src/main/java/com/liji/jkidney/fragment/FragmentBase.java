package com.liji.jkidney.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 */
public abstract class FragmentBase extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getOnCreateView(inflater, container, savedInstanceState);
    }


    public abstract View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
