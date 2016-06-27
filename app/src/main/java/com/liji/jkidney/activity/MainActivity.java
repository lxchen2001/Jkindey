package com.liji.jkidney.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.liji.jkidney.R;
import com.liji.jkidney.adapter.BottomTabViewPagerAdapter;

import org.xutils.x;

import java.util.ArrayList;


public class MainActivity extends ActBase {

    private BottomTabViewPagerAdapter adapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    // UI
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;


    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    void initView(Bundle savedInstanceState) {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);

        AHBottomNavigationItem infoItem = new AHBottomNavigationItem(getResources().getString(R.string.fragment_info), R.drawable.ic_menu_jingxuan_sel);
        AHBottomNavigationItem checkItem = new AHBottomNavigationItem(getResources().getString(R.string.fragment_checks), R.drawable.ic_menu_jiancha_sel);
        AHBottomNavigationItem myItem = new AHBottomNavigationItem(getResources().getString(R.string.fragment_my), R.drawable.ic_menu_my_sel);

        bottomNavigationItems.add(infoItem);
        bottomNavigationItems.add(checkItem);
        bottomNavigationItems.add(myItem);

        bottomNavigation.addItems(bottomNavigationItems);
        adapter = new BottomTabViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);
                return true;
            }
        });
    }

    @Override
    void setData(Bundle savedInstanceState) {

    }
}
