package com.liji.jkidney.activity.info;

import android.os.Bundle;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.info.URL;
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
    private int type;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public  void initView(Bundle savedInstanceState) {
        title = this.getIntent().getStringExtra("title");
        id = this.getIntent().getStringExtra("id");
        type = this.getIntent().getIntExtra("type", 1);

        //根据总类或者相应的URL
        url = getUrl(type);

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
     * 根据总类或者相应的URL
     *
     * @param type
     * @return
     */
    private String getUrl(int type) {
        String urlDeafult = null;
        switch (type) {
            case 1://健康知识
                urlDeafult = URL.url_knowledge_show;
                break;

            case 2://健康信息
                urlDeafult = URL.url_news_show;
                break;
        }
        return urlDeafult;
    }

    /**
     * 请求数据
     */
    private void requestData(String id) {
        Parameters para = new Parameters();
        para.put("id", id);
        ApiStoreSDK.execute(url,
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        JLogUtils.Json(responseString);
                        try {
                            String urlDetail = null;
                            if (type == 1) {//健康知识
                                urlDetail = JSONHandleUtils.getKnowledgeDetailUrl(responseString);
                            } else if (type == 2) {//健康信息
                                urlDetail = JSONHandleUtils.getInfoDetailUrl(responseString);
                            }
                            webView.loadUrl(urlDetail);
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



}
