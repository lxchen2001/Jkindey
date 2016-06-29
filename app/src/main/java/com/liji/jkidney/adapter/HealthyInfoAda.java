package com.liji.jkidney.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.M_HealthyInfoList;
import com.liji.jkidney.model.M_Life_Healthy;
import com.liji.jkidney.utils.JTimeUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.util.List;

/**
 * 作者：liji on 2016/6/29 13:49
 * 邮箱：lijiwork@sina.com
 */
public class HealthyInfoAda extends BaseQuickAdapter<M_HealthyInfoList> {
    ImageLoader imageLoader = ImageLoader.getInstance();

    public HealthyInfoAda(List<M_HealthyInfoList> data) {
        super(R.layout.item_healthy_info, data);
    }


    @Override
    protected void convert(BaseViewHolder holder, M_HealthyInfoList info) {
        imageLoader.displayImage(info.getImg(), (ImageView) holder.getView(R.id.item_img));
        holder.setText(R.id.item_title, info.getTitle());
        holder.setText(R.id.item_reading_count, "" + info.getCount());

        try {
            holder.setText(R.id.item_time, "" + JTimeUtils.getTime(info.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
