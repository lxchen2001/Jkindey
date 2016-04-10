package com.liji.jkidney.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.liji.jkidney.R;
import com.liji.jkidney.adapter.ChecksFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChecks extends Fragment {


    @ViewInject(R.id.fragment_checks_gridview)
    GridView mGridView;

    ChecksFragmentAdapter mChecksFragmentAdapter;

    public FragmentChecks() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks_gridview, container, false);
        ViewUtils.inject(this, view);


        mChecksFragmentAdapter = new ChecksFragmentAdapter(getActivity());
        mGridView.setAdapter(mChecksFragmentAdapter);


        return view;
    }

}
