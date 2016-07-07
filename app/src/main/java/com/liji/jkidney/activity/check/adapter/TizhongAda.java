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
public class TizhongAda extends BaseQuickAdapter<MCheckType> {

    public TizhongAda(List<MCheckType> list) {
        super(R.layout.item_check_tizhong, list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MCheckType m_check_niaodanbai) {
        baseViewHolder.setText(R.id.item_txt_time, "" + m_check_niaodanbai.getList().get(0).getTime() + " — 体重");
        baseViewHolder.setText(R.id.item_content_1, "体重：" + m_check_niaodanbai.getList().get(0).getValue() + " kg");

    }
}
