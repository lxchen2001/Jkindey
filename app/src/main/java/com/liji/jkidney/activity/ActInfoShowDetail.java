package com.liji.jkidney.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.liji.jkidney.R;
import com.liji.jkidney.model.M_HealthyInfoList;
import com.liji.jkidney.model.URL;
import com.liji.jkidney.utils.JLogUtils;
import com.liji.jkidney.utils.JSONHandleUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.SuperWebView;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_act_info_show_detail)
public class ActInfoShowDetail extends ActBase {

    @ViewInject(R.id.webview)
    SuperWebView webView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    private String title;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void initView(Bundle savedInstanceState) {
        title = this.getIntent().getStringExtra("title");
        id = this.getIntent().getStringExtra("id");

        headView.setTitle("" + title);
        headView.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });


        requestData(id);

    }

    /**
     * 请求数据
     *
     */
    private void requestData(String id) {
        Parameters para = new Parameters();
        para.put("id", id);
        ApiStoreSDK.execute(URL.url_news_show,
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        JLogUtils.Json(responseString);
                        try {
                            String url = JSONHandleUtils.getInfoDetailUrl(responseString);
                            webView.loadUrl(url);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            JLogUtils.E(e);
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


    @Override
    void setData(Bundle savedInstanceState) {

    }
}
