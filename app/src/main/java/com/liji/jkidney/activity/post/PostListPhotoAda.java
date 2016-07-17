package com.liji.jkidney.activity.post;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by liji on 16-7-17.
 */
public class PostListPhotoAda extends BaseQuickAdapter<String> {
    ImageLoader imageLoader = ImageLoader.getInstance();

    public PostListPhotoAda(List<String> data) {
        super(R.layout.item_post_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String url) {
        ImageView imageView = baseViewHolder.getView(R.id.item_img);
        imageLoader.displayImage(url, imageView, new ImageSize(100, 100));

    }
}
