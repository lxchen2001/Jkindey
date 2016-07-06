package com.liji.jkidney.activity.check.Niaodanbai;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liji.jkidney.model.check.MCheckTypeNiaodanbai;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liji on 2016/7/6 13:23
 * 邮箱：lijiwork@sina.com
 */
public class ActCheckStatisticsPagerAdapter extends FragmentPagerAdapter {

    List<MCheckTypeNiaodanbai> checkTypeNiaodanbais = new ArrayList<>();
    String[] TITLE = new String[]{"24小时总尿量(ml/24h)", "24小时尿蛋白总量(g/24h)"};

    public ActCheckStatisticsPagerAdapter(FragmentManager fm, List<MCheckTypeNiaodanbai> checkTypeNiaodanbais) {
        super(fm);
        this.checkTypeNiaodanbais = checkTypeNiaodanbais;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentCheckStatistics.newInstance(checkTypeNiaodanbais, position);
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }
}
