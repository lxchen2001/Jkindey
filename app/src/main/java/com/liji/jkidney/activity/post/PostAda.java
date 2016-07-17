package com.liji.jkidney.activity.post;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by liji on 16-7-16.
 */
public class PostAda extends BaseQuickAdapter<M_Post> {
    private Context context;
    private PostListPhotoAda ada;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public PostAda(Context context, List<M_Post> data) {
        super(R.layout.item_post, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, M_Post m_post) {
        baseViewHolder.setText(R.id.item_tv_nickname, "" + m_post.getAuthor().getNickname());
        baseViewHolder.setText(R.id.item_tv_title, "" + m_post.getTitle());
        baseViewHolder.setText(R.id.item_tv_content, "" + m_post.getContent());
        baseViewHolder.setText(R.id.item_tv_time, "" + m_post.getTime());
        baseViewHolder.setText(R.id.item_tv_see, "" + m_post.getSeeNum());
        baseViewHolder.setText(R.id.item_tv_comment, "15");

        RoundImageView imageView = baseViewHolder.getView(R.id.item_head_ico);
        imageLoader.displayImage(m_post.getAuthor().getHeadimg(), imageView, new ImageSize(80, 80));


        RecyclerView recyclerView = baseViewHolder.getView(R.id.recyclerview);
        if (m_post.getPostImg() != null && m_post.getPostImg().size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            ada = new PostListPhotoAda(m_post.getPostImg());
            recyclerView.setAdapter(ada);
        } else {
            recyclerView.setVisibility(View.GONE);
        }


    }
}