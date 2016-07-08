package com.liji.jkidney.activity.compute;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.Type;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.rey.material.widget.Switch;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 肾小球过滤率计算公式
 * http://wenku.baidu.com/link?url=18O5liSzHH70YzbHLFyaoSJvfbEprFsliolxL7jsezRXJONoQuiILhLA1BG_BV4NLg-PHMvaEZE1KrHqZbnVXJdFKMGsUDbHv_-SKCNaq27
 */
@ContentView(R.layout.activity_act_compute_shenxiaoqiu)
public class ActComputeShenxiaoqiu extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.switch_sex)
    Switch switchSex;
    @ViewInject(R.id.switch_danwei)
    Switch switch_danwei;
    @ViewInject(R.id.tv_sex)
    TextView tvSex;
    @ViewInject(R.id.tv_jigan_danwei)
    TextView tv_jigan_danwei;
    @ViewInject(R.id.tv_result)
    TextView tvResult;
    @ViewInject(R.id.et_nianling)
    EditText etNianling;
    @ViewInject(R.id.et_tizhong)
    EditText etTizhong;
    @ViewInject(R.id.et_shengao)
    EditText et_shengao;
    @ViewInject(R.id.et_jigan)
    EditText etJigan;
    @ViewInject(R.id.btn_compute)
    Button btnCompute;
    @ViewInject(R.id.ll_result)
    LinearLayout llResult;
    @ViewInject(R.id.ll_advice)
    LinearLayout llAdvice;

    int sex = Type.Sex_M;//0
    int danwei = Type.Jigan_u;
    Integer age = 0;
    Double weight = new Double(0.0);
    Double jigan = new Double(0.0);
    Double height = new Double(0.0);
    double CCRresult = 0.0;//Ccr=(140-年龄)×体重(kg)/72×Scr(mg/dl) 或 Ccr=[(140-年龄)×体重(kg)]/[0.818×Scr(umol/L)]
    double BSA = 0.0;//为体表面积=1+[(身高+体重)-160]/100

    @Override
    protected void initView(Bundle savedInstanceState) {
        headview.setTitle("肾小球过滤率计算");
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        //sex
        switchSex.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch view, boolean checked) {
                if (checked) {
                    sex = Type.Sex_W;
                    tvSex.setText("女");
                } else {
                    sex = Type.Sex_M;
                    tvSex.setText("男");
                }
            }
        });

        //umol/L
        switch_danwei.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch view, boolean checked) {
                if (checked) {
                    danwei = Type.Jigan_u;
                    tv_jigan_danwei.setText("肌酐(umol/L)");
                } else {
                    danwei = Type.Jigan_m;
                    tv_jigan_danwei.setText("肌酐(mg/dl)");
                }
            }
        });


        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }


        });

    }

    private void doSubmit() {
        if (TextUtils.isEmpty(etTizhong.getText().toString().trim())) {
            JToastUtils.showToast(ActComputeShenxiaoqiu.this, "请输入体重");
            return;
        } else {
            weight = Double.parseDouble(etTizhong.getText().toString().trim());
        }

        if (TextUtils.isEmpty(etNianling.getText().toString().trim())) {
            JToastUtils.showToast(ActComputeShenxiaoqiu.this, "输入年龄");
            return;
        } else {
            age = Integer.parseInt(etNianling.getText().toString().trim());
        }

        if (TextUtils.isEmpty(et_shengao.getText().toString().trim())) {
            JToastUtils.showToast(ActComputeShenxiaoqiu.this, "请输入身高");
            return;
        } else {
            height = Double.parseDouble(et_shengao.getText().toString().trim());
        }

        if (TextUtils.isEmpty(etJigan.getText().toString().trim())) {
            JToastUtils.showToast(ActComputeShenxiaoqiu.this, "请输入肌酐");
            return;
        } else {
            jigan = Double.parseDouble(etJigan.getText().toString().trim());
        }


        if (danwei == Type.Jigan_m) {
            CCRresult = (140 - age) * weight / (0.818 * jigan * 88.4);
        } else {
            CCRresult = (140 - age) * weight / (0.818 * jigan);
        }
        if (sex == Type.Sex_W) {//女
            CCRresult = CCRresult * 0.85;
        }

        BSA = 1 + (height + weight - 160) * 1.0 / 100;
        double GFR = (0.84 * CCRresult * 1.73) / BSA;

        tvResult.setText("" + GFR);


    }


}
