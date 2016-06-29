package com.liji.jkidney.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liji.jkidney.fragment.FragmentHealthyInfoViewPager;
import com.liji.jkidney.model.M_HealthyInfoClassify;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康资讯顶部tab，viewpager滑动
 * 作者：liji on 2016/6/29 16:12
 * 邮箱：lijiwork@sina.com
 */
public class HealthyInfoViewPagerAdapter extends FragmentPagerAdapter {

    List<M_HealthyInfoClassify> healthyInfoClassifyList = new ArrayList<>();

    public HealthyInfoViewPagerAdapter(FragmentManager fm, List<M_HealthyInfoClassify> mHealthyInfoList) {
        super(fm);
        this.healthyInfoClassifyList = mHealthyInfoList;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return healthyInfoClassifyList.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentHealthyInfoViewPager.newInstance(healthyInfoClassifyList.get(position).getId());
    }

    @Override
    public int getCount() {
        return healthyInfoClassifyList.size();
    }
}
