package com.liji.jkidney.activity.check.Niaodanbai;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.check.MCheckTypeNiaodanbai;
import com.liji.jkidney.model.check.M_Check_niaodanbai;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：liji on 2016/7/5 13:33
 * 邮箱：lijiwork@sina.com
 */
public class NiaodanbaiAda extends BaseQuickAdapter<MCheckTypeNiaodanbai> {

    public NiaodanbaiAda(List<MCheckTypeNiaodanbai> list) {
        super(R.layout.item_check_niaodanbai, list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MCheckTypeNiaodanbai m_check_niaodanbai) {
        baseViewHolder.setText(R.id.item_txt_time, "" + m_check_niaodanbai.getList().get(0).getTime() + " — 尿蛋白定量");
        baseViewHolder.setText(R.id.item_content_1, "24小时尿量：" + m_check_niaodanbai.getList().get(0).getValue() + " ml/24h");
        baseViewHolder.setText(R.id.item_content_2, "24小时尿蛋白定量：" + m_check_niaodanbai.getList().get(1).getValue() + " g/24h");

    }
}
