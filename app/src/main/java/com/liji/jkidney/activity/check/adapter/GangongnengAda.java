package com.liji.jkidney.activity.check.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.check.MCheckType;

import java.util.List;

/**
 * 作者：liji on 2016/7/5 13:33
 * 邮箱：lijiwork@sina.com
 */
public class GangongnengAda extends BaseQuickAdapter<MCheckType> {

    public GangongnengAda(List<MCheckType> list) {
        super(R.layout.item_check_gangongneng, list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MCheckType m_check_niaodanbai) {
        baseViewHolder.setText(R.id.item_txt_time, "" + m_check_niaodanbai.getList().get(0).getTime() + " — 肝功能");
        baseViewHolder.setText(R.id.item_content_1, "谷丙转氨酶：" + m_check_niaodanbai.getList().get(0).getValue() + " u/L");
        baseViewHolder.setText(R.id.item_content_2, "谷草转氨酶：" + m_check_niaodanbai.getList().get(1).getValue() + " u/L");

    }
}
