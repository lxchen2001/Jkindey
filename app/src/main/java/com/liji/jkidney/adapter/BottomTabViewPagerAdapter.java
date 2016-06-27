package com.liji.jkidney.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liji.jkidney.fragment.FragmentChecks;
import com.liji.jkidney.fragment.FragmentInfo;
import com.liji.jkidney.fragment.FragmentMy;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liji on 2016/6/27 14:37
 * 邮箱：lijiwork@sina.com
 */
public class BottomTabViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();

    public BottomTabViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.clear();
        fragments.add(new FragmentInfo());
        fragments.add(new FragmentChecks());
        fragments.add(new FragmentMy());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
