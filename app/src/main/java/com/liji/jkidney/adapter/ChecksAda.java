package com.liji.jkidney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.M_ChcekInfo;
import com.liji.jkidney.utils.XCallbackListener;

import java.util.ArrayList;
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
