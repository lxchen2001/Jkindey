package com.liji.jkidney.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.liji.jkidney.R;
import com.liji.jkidney.adapter.BottomTabViewPagerAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends ActBase {

    private BottomTabViewPagerAdapter adapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    // UI
    @ViewInject(R.id.view_pager)
    private AHBottomNavigationViewPager viewPager;

    @ViewInject(R.id.bottom_navigation)
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        AHBottomNavigationItem infoItem = new AHBottomNavigationItem((R.string.fragment_info), R.drawable.ic_menu_post, R.color.color_tab_post);
        AHBottomNavigationItem toolItem = new AHBottomNavigationItem((R.string.fragment_tool), R.drawable.ic_menu_tool, R.color.color_tab_tool);
        AHBottomNavigationItem checkItem = new AHBottomNavigationItem((R.string.fragment_checks), R.drawable.ic_menu_check, R.color.color_tab_check);
        AHBottomNavigationItem myItem = new AHBottomNavigationItem((R.string.fragment_my), R.drawable.ic_menu_my, R.color.color_tab_my);

        bottomNavigationItems.add(infoItem);
        bottomNavigationItems.add(checkItem);
        bottomNavigationItems.add(toolItem);
        bottomNavigationItems.add(myItem);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setColored(true);


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


}
