package com.liji.jkidney.activity.post;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by liji on 16-7-17.
 */
public class PostDetailPhotoAda extends BaseQuickAdapter<String> {
    ImageLoader imageLoader = ImageLoader.getInstance();

    public PostDetailPhotoAda(List<String> data) {
        super(R.layout.act_post_detail_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String url) {
        ImageView imageView = baseViewHolder.getView(R.id.item_img);
        imageLoader.displayImage(url, imageView);

    }
}
