package com.liji.jkidney.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.info.M_ChcekInfo;

import java.util.List;

/**
 * 检查项目adapters
 * Created by liji on 2016/4/10.
 */
public class ChecksAda extends BaseQuickAdapter<M_ChcekInfo> {
    public ChecksAda(List<M_ChcekInfo> m_chcekInfos) {
        super(R.layout.item_checks, m_chcekInfos);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, M_ChcekInfo m_chcekInfo) {
        baseViewHolder.setBackgroundRes(R.id.item_img, m_chcekInfo.getCheckResourceId());
        baseViewHolder.setText(R.id.item_txt, m_chcekInfo.getCheckName());
        baseViewHolder.setText(R.id.item_content, m_chcekInfo.getCheckContent());
    }
}
