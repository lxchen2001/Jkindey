package com.liji.jkidney.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.fragment.FragmentChecks;
import com.liji.jkidney.fragment.FragmentInfo;
import com.liji.jkidney.fragment.FragmentMy;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class ActFrame extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection info = newSection(getResources().getString(R.string.fragment_info), R.drawable.ic_menu_jingxuan_sel, new FragmentInfo());
        MaterialSection checks = newSection(getResources().getString(R.string.fragment_checks), R.drawable.ic_menu_jiancha_sel, new FragmentChecks());
        MaterialSection my = newSection(getResources().getString(R.string.fragment_my), R.drawable.ic_menu_my_sel, new FragmentMy());
        this.addSection(info);
        this.addSection(checks);
        this.addSection(my);

        setDrawerHeaderCustom(getCustomeHeadView());
        //返回到首页
        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);

    }

    /**
     * 自定义头部布局
     *
     * @return
     */
    private View getCustomeHeadView() {

        View view = LayoutInflater.from(this).inflate(R.layout.custome_drawermenu_headview, null);
        RelativeLayout rl_headimg = (RelativeLayout) view.findViewById(R.id.rl_headimg);
        ImageView img_headview = (ImageView) view.findViewById(R.id.img_user);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_username);
        TextView tv_nickname = (TextView) view.findViewById(R.id.tv_nickname);
//        KUser user=BmobUser.getCurrentUser(this,KUser.class);
//        tv_name.setText(user.getUsername());
//        tv_nickname.setText(user.getSignAture());
////        rl_headimg.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent=new Intent();
////                intent.setClass(ActDrawerMenu.this,ActPersonalCenter.class);
////                startActivity(intent);
////            }
////        });
        return view;
    }

}
