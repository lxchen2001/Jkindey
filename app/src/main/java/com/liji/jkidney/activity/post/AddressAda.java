package com.liji.jkidney.activity.post;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.post.MAddress;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by liji on 16-7-16.
 */
public class AddressAda extends BaseQuickAdapter<MAddress> {

    public AddressAda(List<MAddress> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MAddress address) {
        baseViewHolder.setText(R.id.item_tv_name, "" + address.getTitle());
        baseViewHolder.setText(R.id.item_tv_address, "" + address.getAddress());

    }
}
