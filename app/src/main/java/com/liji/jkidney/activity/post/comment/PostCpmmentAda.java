package com.liji.jkidney.activity.post.comment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.post.ActAuthorDetail;
import com.liji.jkidney.model.post.MComment;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by liji on 16-7-16.
 */
public class PostCpmmentAda extends BaseQuickAdapter<MComment> {
    private Context context;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public PostCpmmentAda(Context context, List<MComment> data) {
        super(R.layout.item_post_comment, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MComment m_post) {
        baseViewHolder.setText(R.id.item_tv_nickname, "" + m_post.getAuthor().getNickname());
        baseViewHolder.setText(R.id.item_tv_content, "" + m_post.getContent());
        baseViewHolder.setText(R.id.item_tv_time, "" + m_post.getTime());

        RoundImageView imageView = baseViewHolder.getView(R.id.item_head_ico);
        imageLoader.displayImage(m_post.getAuthor().getHeadimg(), imageView, new ImageSize(80, 80));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActAuthorDetail.class);
                intent.putExtra(ActAuthorDetail.AUTHOR, m_post.getAuthor());
                context.startActivity(intent);
            }
        });

    }
}
