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
public class XuetangAda extends BaseQuickAdapter<MCheckType> {

    public XuetangAda(List<MCheckType> list) {
        super(R.layout.item_check_xuetang, list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MCheckType m_check_niaodanbai) {
        baseViewHolder.setText(R.id.item_txt_time, "" + m_check_niaodanbai.getList().get(0).getTime() + " — 血糖");
        baseViewHolder.setText(R.id.item_content_1, "血糖：" + m_check_niaodanbai.getList().get(0).getValue() + " mmol/L");

    }
}
