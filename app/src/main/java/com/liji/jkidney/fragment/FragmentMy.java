package com.liji.jkidney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.RoundImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class FragmentMy extends FragmentBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.item_head_ico)
    RoundImageView roundImageView;

    @ViewInject(R.id.item_ll_setting)
    LinearLayout item_ll_setting;


    @ViewInject(R.id.item_ll_fav)
    LinearLayout item_ll_fav;

    public FragmentMy() {

    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        x.view().inject(this, view);

        headView.setTitle("我的");
        headView.setRightImgAction(R.drawable.my_edit_selector, new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                Toast.makeText(getContext(), "editting", Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), ActLogin.class));
            }
        });

        item_ll_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "fav", Toast.LENGTH_SHORT).show();
            }
        });

        item_ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "setting", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
