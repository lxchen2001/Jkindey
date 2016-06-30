package com.liji.jkidney.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liji.jkidney.fragment.FragmentHealthyInfoViewPager;
import com.liji.jkidney.fragment.FragmentHealthyKnowledgeViewpager;
import com.liji.jkidney.model.M_HealthyInfoClassify;
import com.liji.jkidney.model.M_HealthyKnowledgeClassicfy;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康知识顶部tab，viewpager滑动
 * 作者：liji on 2016/6/29 16:12
 * 邮箱：lijiwork@sina.com
 */
public class HealthyKnowledgeViewPagerAdapter extends FragmentPagerAdapter {

    List<M_HealthyKnowledgeClassicfy> healthyKnowledgeClassicfies = new ArrayList<>();

    public HealthyKnowledgeViewPagerAdapter(FragmentManager fm, List<M_HealthyKnowledgeClassicfy> mHealthyInfoList) {
        super(fm);
        this.healthyKnowledgeClassicfies = mHealthyInfoList;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return healthyKnowledgeClassicfies.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentHealthyKnowledgeViewpager.newInstance(healthyKnowledgeClassicfies.get(position).getId());
    }

    @Override
    public int getCount() {
        return healthyKnowledgeClassicfies.size();
    }
}
