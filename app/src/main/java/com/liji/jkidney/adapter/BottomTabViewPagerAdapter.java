package com.liji.jkidney.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liji.jkidney.activity.check.FragmentChecks;
import com.liji.jkidney.activity.post.FragmentPost;
import com.liji.jkidney.activity.tool.FragmentTool;
import com.liji.jkidney.activity.user.FragmentMy;

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
        fragments.add(new FragmentPost());
        fragments.add(new FragmentChecks());
        fragments.add(new FragmentTool());
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
