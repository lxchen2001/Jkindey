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
import com.liji.jkidney.model.M_Info;
import com.liji.jkidney.utils.XCallbackListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查项目adapters
 * Created by liji on 2016/4/10.
 */
public class InfoAda extends BaseQuickAdapter<M_Info> {

    public InfoAda(List<M_Info> infoList) {
        super(R.layout.item_info, infoList);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, M_Info m_info) {
        baseViewHolder.setText(R.id.item_txt, m_info.getmNewsType());
        baseViewHolder.setText(R.id.item_content, m_info.getmNewsTypeContent());
        baseViewHolder.setBackgroundRes(R.id.item_img, m_info.getmNewsTypePic());
    }

}
