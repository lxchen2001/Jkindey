package com.liji.jkidney.activity.post;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.imagezoom.util.ImageZoom;
import com.liji.jkidney.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liji on 16-7-17.
 */
public class PostDetailPhotoAda extends BaseQuickAdapter<String> {
    ImageLoader imageLoader = ImageLoader.getInstance();

    private List<String> data = new ArrayList<>();
    private Context context;

    public PostDetailPhotoAda(Context context, List<String> data) {
        super(R.layout.act_post_detail_photo, data);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final String url) {
        ImageView imageView = baseViewHolder.getView(R.id.item_img);
        imageLoader.displayImage(url, imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示大图
                ImageZoom.show(context, url, data);
            }
        });


    }
}
