package com.liji.jkidney.activity.check.line;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liji.jkidney.model.CheckTypeId;
import com.liji.jkidney.model.check.MCheckType;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liji on 2016/7/6 13:23
 * 邮箱：lijiwork@sina.com
 */
public class ActCheckStatisticsPagerAdapter extends FragmentPagerAdapter {

    List<MCheckType> checkTypeList = new ArrayList<>();
    private int type = 0;

    String[] TYPE_1 = new String[]{"谷丙转氨酶(u/L)",
            "谷草转氨酶(u/L)",
            "γ—谷氨酰转肽酶(u/L)",
            "碱性磷酸酶(u/L)",
            "总胆红素(umol/L)",
            "直接胆红素(umol/L)",
            "间接胆红素(umol/L)",
            "总胆汁酸(umol/L)",
            "前白蛋白(mg/L)",
            "总蛋白(g/L)",
            "白蛋白(g/L)",
            "球蛋白(g/L)",
            "白球蛋白比",
            "总胆固醇(mmol/L)"
    };
    String[] TYPE_2 = new String[]{
            "尿素氮(mmol/L)",
            "肌酐(umol/L)",
            "总蛋白(g/L)",
            "白蛋白(g/L)",
            "球蛋白(g/L)",
            "白球蛋白比",
            "总胆固醇(mmol/L)",
            "尿酸(umol)",
            "胱抑素C(mg/L)"
    };
    String[] TYPE_3 = new String[]{"24小时总尿量(ml/24h)", "24小时尿蛋白总量(g/24h)"};
    String[] TYPE_4 = new String[]{"体重(kg)"};
    String[] TYPE_5 = new String[]{"血糖(mmol/L)"};
    String[] TYPE_6 = new String[]{"收缩压(mmhg)", "舒张压(mmhg)"};

    public ActCheckStatisticsPagerAdapter(FragmentManager fm, List<MCheckType> checkTypeList, int type) {
        super(fm);
        this.type = type;
        this.checkTypeList = checkTypeList;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence charSequence = "";
        switch (type) {
            case CheckTypeId.Gangongneng://肝功能
                charSequence = TYPE_1[position];
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                charSequence = TYPE_2[position];
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                charSequence = TYPE_3[position];
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                break;
            case CheckTypeId.Tizhong:// 体重
                charSequence = TYPE_4[position];
                break;
            case CheckTypeId.Xuetang:// 血糖
                charSequence = TYPE_5[position];
                break;
            case CheckTypeId.Xueya:// 血压
                charSequence = TYPE_6[position];
                break;
        }
        return charSequence;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentCheckStatistics.newInstance(checkTypeList, position, type);
    }

    @Override
    public int getCount() {
        int count = 0;
        switch (type) {
            case CheckTypeId.Gangongneng://肝功能
                count = TYPE_1.length;
                break;
            case CheckTypeId.Shengongneng:// 肾功能
                count = TYPE_2.length;
                break;
            case CheckTypeId.Niaodanbai:// 尿蛋白
                count = TYPE_3.length;
                break;
            case CheckTypeId.Niaochanggui:// 尿常规
                break;
            case CheckTypeId.Tizhong:// 体重
                count = TYPE_4.length;
                break;
            case CheckTypeId.Xuetang:// 血糖
                count = TYPE_5.length;
                break;
            case CheckTypeId.Xueya:// 血压
                count = TYPE_6.length;
                break;
        }
        return count;
    }
}
