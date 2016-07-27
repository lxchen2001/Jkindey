package com.liji.jkidney.activity.check.line;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.CheckTypeId;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MCheckType;
import com.liji.jkidney.model.user.MyUser;
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
    private int type = CheckTypeId.Gangongneng;
    MyUser user = new MyUser();
    List<MCheckType> checkData = new ArrayList<>();
    ActCheckStatisticsPagerAdapter adapter;


    public static String INTENT_USER = "user";
    public static String INTENT_CHECK = "check";
    public static String INTENT_TYPE = "type";
    public static String INTENT_NAME = "name";

    @Override
    protected void initView(Bundle savedInstanceState) {
        user = (MyUser) this.getIntent().getSerializableExtra(INTENT_USER);
        if (user == null) {
            user = User.getCurrentUser(this);
        }
        title = this.getIntent().getStringExtra(INTENT_NAME);
        type = this.getIntent().getIntExtra(INTENT_TYPE, CheckTypeId.Gangongneng);
        checkData = (List<MCheckType>) this.getIntent().getSerializableExtra(INTENT_CHECK);

        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        initData(checkData);
    }

    private void initData(List<MCheckType> list) {
        if (list != null && list.size() > 0) {
            adapter = new ActCheckStatisticsPagerAdapter(getSupportFragmentManager(), checkData, type);
            pager.setAdapter(adapter);
            final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                    .getDisplayMetrics());
            pager.setPageMargin(pageMargin);
            tabs.setViewPager(pager);
        }

    }
}
