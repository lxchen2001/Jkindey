package com.liji.jkidney.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.check.Niaodanbai.ActCheckNiaodanbai;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChecks extends FragmentBase implements View.OnClickListener {
    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    @ViewInject(R.id.ll_type_1)
    LinearLayout llType1;
    @ViewInject(R.id.ll_type_2)
    LinearLayout llType2;
    @ViewInject(R.id.ll_type_3)
    LinearLayout llType3;
    @ViewInject(R.id.ll_type_4)
    LinearLayout llType4;
    @ViewInject(R.id.ll_type_5)
    LinearLayout llType5;
    @ViewInject(R.id.ll_type_6)
    LinearLayout llType6;
    @ViewInject(R.id.ll_type_7)
    LinearLayout llType7;
    @ViewInject(R.id.ll_type_8)
    LinearLayout llType8;
    @ViewInject(R.id.ll_type_9)
    LinearLayout llType9;
    private String[] mChecksNames = {"肝功能", "肾功能", "尿蛋白", "尿常规", "体重", "血糖", "血压"};
    private String[] mCheckContents = {"肝功能检查的目的在于探测肝脏有无疾病、肝脏损害程度以及查明肝病原因、判断预后和鉴别发生黄疸的病因等。",
            "肾功能（renal function）是指肾脏排泄体内代谢废物，维持机体钠、钾、钙等电解质的稳定及酸碱平衡的功能，肾功能检查包括血肌酐、血尿素氮、血及尿β2—微球蛋白、尿白蛋白、尿免疫球蛋白G、尿分泌型免疫球蛋白A等.",
            "尿内出现蛋白称为蛋白尿，也即尿蛋白。正常尿液中含少量小分子蛋白，普通尿常规检查测不出，当尿中蛋白增加，尿常规检查可以测出即为蛋白尿。蛋白尿是肾脏病的常见表现，全身性疾病亦可出现蛋白尿。",
            "尿常规是医学检验“三大常规”项目之一，不少肾脏病变早期就可以出现蛋白尿或者尿沉渣中有形成分。对于某些全身性病变以及身体其他脏器影响尿液改变的疾病如糖尿病、血液病、肝胆疾患、流行性出血热等的诊断，也有很重要的参考价值。同时，尿液的化验检查还可以反映一些疾病的治疗效果及预后。通过此项检查可以判断相应的病征。",
            "体重body weight为 裸体或穿着已知重量的工作衣称量得到的身体重量,体重增长除与骨的增长关系密切以外，还与肌肉，脂肪等的增长有关系。",
            "血清中的糖称为血糖，绝大多数情况下都是葡萄糖。体内各组织细胞活动所需的能量大部分来自葡萄糖，所以血糖必须保持一定的水平才能维持体内各器官和组织的需要。正常人在清晨空腹血糖浓度为80～120毫克%。空腹血糖浓度超过130毫克%称为高血糖。如果血糖浓度超过160～180毫克%，就有一部分葡萄糖随尿排出，这就是糖尿。血糖浓度低于70毫克%称为低血糖。",
            "人的血液输送到全身各部位需要一定的压力，这个压力就是血压。血压是血液在血管内流动时，作用于血管壁的压力，它是推动血液在血管内流动的动力。心室收缩，血液从心室流入动脉，此时血液对动脉的压力最高，称为收缩压（systolic blood pressure，SBP ）。心室舒张，动脉血管弹性回缩，血液仍慢慢继续向前流动，但血压下降，此时的压力称为舒张压（diastolic blood pressure，DBP）。"
    };

    public FragmentChecks() {

    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks_gridview, container, false);
        x.view().inject(this, view);

        headView.setTitle("检查");
        initClick();
        return view;
    }

    private void initClick() {
        llType1.setOnClickListener(this);
        llType2.setOnClickListener(this);
        llType3.setOnClickListener(this);
        llType4.setOnClickListener(this);
        llType5.setOnClickListener(this);
        llType6.setOnClickListener(this);
        llType7.setOnClickListener(this);
        llType8.setOnClickListener(this);
        llType9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_type_1:

                break;
            case R.id.ll_type_2:

                break;
            case R.id.ll_type_3:
                intent.setClass(getContext(), ActCheckNiaodanbai.class);
                intent.putExtra("name", mChecksNames[2]);
                intent.putExtra("info", mCheckContents[2]);
                getContext().startActivity(intent);
                break;
            case R.id.ll_type_4:

                break;
            case R.id.ll_type_5:

                break;
            case R.id.ll_type_6:

                break;
            case R.id.ll_type_7:

                break;
            case R.id.ll_type_8:

                break;
            case R.id.ll_type_9:

                break;

        }
    }

}
