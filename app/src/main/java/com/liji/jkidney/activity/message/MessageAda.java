package com.liji.jkidney.activity.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.post.ActAuthorDetail;
import com.liji.jkidney.activity.post.PostListPhotoAda;
import com.liji.jkidney.model.message.MMessage;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by liji on 16-7-16.
 */
public class MessageAda extends BaseQuickAdapter<MMessage> {

    public MessageAda(List<MMessage> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MMessage message) {
        baseViewHolder.setText(R.id.item_tv_content, "" + message.getContent());
        baseViewHolder.setText(R.id.item_tv_time, "" + message.getTime());


    }
}
