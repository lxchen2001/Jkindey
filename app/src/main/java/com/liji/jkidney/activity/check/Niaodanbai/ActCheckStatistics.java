package com.liji.jkidney.activity.check.Niaodanbai;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MCheckTypeNiaodanbai;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查结果柱状图表示
 */
@ContentView(R.layout.activity_check_statistics)
public class ActCheckStatistics extends ActBase {
    @ViewInject(R.id.tabs)
    private PagerSlidingTabStrip tabs;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.pager)
    private ViewPager pager;
    String title;
    MyUser user = new MyUser();
    List<MCheckTypeNiaodanbai> checkTypeNiaodanbaiList = new ArrayList<>();
    ActCheckStatisticsPagerAdapter adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        user = (MyUser) this.getIntent().getSerializableExtra("user") == null ? User.getCurrentUser(this) : user;
        title = this.getIntent().getStringExtra("name");
        checkTypeNiaodanbaiList = (List<MCheckTypeNiaodanbai>) this.getIntent().getSerializableExtra("data");

        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        initData(checkTypeNiaodanbaiList);
    }

    private void initData(List<MCheckTypeNiaodanbai> list) {
        if (list != null && list.size() > 0) {
            adapter = new ActCheckStatisticsPagerAdapter(getSupportFragmentManager(), checkTypeNiaodanbaiList);
            pager.setAdapter(adapter);
            final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                    .getDisplayMetrics());
            pager.setPageMargin(pageMargin);
            tabs.setViewPager(pager);
        }

    }
}
