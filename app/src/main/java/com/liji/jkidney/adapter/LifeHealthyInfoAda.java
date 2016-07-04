package com.liji.jkidney.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.info.M_Life_Healthy;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 作者：liji on 2016/6/29 13:49
 * 邮箱：lijiwork@sina.com
 */
public class LifeHealthyInfoAda extends BaseQuickAdapter<M_Life_Healthy> {
    ImageLoader imageLoader = ImageLoader.getInstance();
    public LifeHealthyInfoAda(List<M_Life_Healthy> data) {
        super(R.layout.item_info_life, data);
    }


    @Override
    protected void convert(BaseViewHolder holder, M_Life_Healthy info) {
        imageLoader.displayImage(info.getPicUrl(), (ImageView) holder.getView(R.id.item_img));
        holder.setText(R.id.item_title,info.getTitle());
        holder.setText(R.id.item_time,info.getCtime());
        holder.setText(R.id.item_from,info.getDescription());

    }
}
