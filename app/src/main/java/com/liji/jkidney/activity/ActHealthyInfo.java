package com.liji.jkidney.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.liji.jkidney.R;
import com.liji.jkidney.adapter.HealthyInfoViewPagerAdapter;
import com.liji.jkidney.model.info.M_HealthyInfoClassify;
import com.liji.jkidney.model.info.URL;
import com.liji.jkidney.utils.JLogUtils;
import com.liji.jkidney.utils.JSONHandleUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_healthy_info)
public class ActHealthyInfo extends ActBase {

    @ViewInject(R.id.tabs)
    private PagerSlidingTabStrip tabs;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.pager)
    private ViewPager pager;
    HealthyInfoViewPagerAdapter classicfyAda;
    List<M_HealthyInfoClassify> mHealthyInfoClassifyList = new ArrayList<>();


    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public  void initView(Bundle savedInstanceState) {
        title = this.getIntent().getStringExtra("title");
        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });
        dataLoad();
    }

    /**
     * 加载健康资讯顶部tab分类
     */
    private void dataLoad() {
        Parameters para = new Parameters();
        ApiStoreSDK.execute(URL.url_news_classify,
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        JLogUtils.Json(responseString);
                        try {
                            mHealthyInfoClassifyList = JSONHandleUtils.parseResponseArray(responseString, M_HealthyInfoClassify.class, 1);
                            classicfyAda = new HealthyInfoViewPagerAdapter(getSupportFragmentManager(), mHealthyInfoClassifyList);
                            pager.setAdapter(classicfyAda);
                            final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                                    .getDisplayMetrics());
                            pager.setPageMargin(pageMargin);
                            tabs.setViewPager(pager);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {

                    }
                });
    }


}
