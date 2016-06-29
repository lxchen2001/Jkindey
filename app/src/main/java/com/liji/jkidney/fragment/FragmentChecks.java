package com.liji.jkidney.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liji.jkidney.R;
import com.liji.jkidney.adapter.ChecksAda;
import com.liji.jkidney.model.M_ChcekInfo;
import com.liji.jkidney.widget.CustomeHeadView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChecks extends FragmentBase {


    @ViewInject(R.id.rlv_content)
    RecyclerView recyclerView;

    @ViewInject(R.id.headview)
    CustomeHeadView headView;

    ChecksAda checksAda;
    List<M_ChcekInfo> m_chcekInfos = new ArrayList<>();

    private String[] mChecksNames = {"肝功能", "肾功能", "尿蛋白", "尿常规", "体重", "血糖", "血压"};
    private int[] mChecksResId = {R.drawable.ic_xueye, R.drawable.ic_shengongneng,
            R.drawable.ic_niaodanbai, R.drawable.ic_niaochanggui,
            R.drawable.ic_tizhong,
            R.drawable.ic_xuetang, R.drawable.ic_xueya};
    private String[] mCheckContents = {"肝功能检查的目的在于探测肝脏有无疾病、肝脏损害程度以及查明肝病原因、判断预后和鉴别发生黄疸的病因等。",
            "肾功能（renal function）是指肾脏排泄体内代谢废物，维持机体钠、钾、钙等电解质的稳定及酸碱平衡的功能，肾功能检查包括血肌酐、血尿素氮、血及尿β2—微球蛋白、尿白蛋白、尿免疫球蛋白G、尿分泌型免疫球蛋白A等.",
            "尿内出现蛋白称为蛋白尿，也即尿蛋白。正常尿液中含少量小分子蛋白，普通尿常规检查测不出，当尿中蛋白增加，尿常规检查可以测出即为蛋白尿。蛋白尿是肾脏病的常见表现，全身性疾病亦可出现蛋白尿。",
            "尿常规是医学检验“三大常规”项目之一，不少肾脏病变早期就可以出现蛋白尿或者尿沉渣中有形成分。对于某些全身性病变以及身体其他脏器影响尿液改变的疾病如糖尿病、血液病、肝胆疾患、流行性出血热等的诊断，也有很重要的参考价值。同时，尿液的化验检查还可以反映一些疾病的治疗效果及预后。通过此项检查可以判断相应的病征。",
            "体重body weight为 裸体或穿着已知重量的工作衣称量得到的身体重量,体重增长除与骨的增长关系密切以外，还与肌肉，脂肪等的增长有关系。",
            "血清中的糖称为血糖，绝大多数情况下都是葡萄糖。体内各组织细胞活动所需的能量大部分来自葡萄糖，所以血糖必须保持一定的水平才能维持体内各器官和组织的需要。正常人在清晨空腹血糖浓度为80～120毫克%。空腹血糖浓度超过130毫克%称为高血糖。如果血糖浓度超过160～180毫克%，就有一部分葡萄糖随尿排出，这就是糖尿。血糖浓度低于70毫克%称为低血糖。",
            "人的血液输送到全身各部位需要一定的压力，这个压力就是血压。血压是血液在血管内流动时，作用于血管壁的压力，它是推动血液在血管内流动的动力。心室收缩，血液从心室流入动脉，此时血液对动脉的压力最高，称为收缩压（systolic blood pressure，SBP ）。心室舒张，动脉血管弹性回缩，血液仍慢慢继续向前流动，但血压下降，此时的压力称为舒张压（diastolic blood pressure，DBP）。"
    };


    private List<M_ChcekInfo> getM_chcekInfos() {
        List<M_ChcekInfo> chcekInfos = new ArrayList<>();
        for (int i = 0; i < mCheckContents.length; i++) {
            M_ChcekInfo m_chcekInfo = new M_ChcekInfo();
            m_chcekInfo.setCheckName(mChecksNames[i]);
            m_chcekInfo.setCheckResourceId(mChecksResId[i]);
            m_chcekInfo.setCheckContent(mCheckContents[i]);
            chcekInfos.add(m_chcekInfo);
        }
        return chcekInfos;
    }

    public FragmentChecks() {

    }

    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks_gridview, container, false);
        x.view().inject(this, view);

        headView.setTitle("检查");

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        m_chcekInfos = getM_chcekInfos();

        checksAda = new ChecksAda(m_chcekInfos);
        recyclerView.setAdapter(checksAda);

        return view;
    }

}
