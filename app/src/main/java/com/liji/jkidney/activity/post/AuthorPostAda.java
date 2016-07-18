package com.liji.jkidney.activity.post;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liji.jkidney.R;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.utils.JLogUtils;
import com.liji.jkidney.utils.JTimeUtils;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * 个人中心的帖子
 * Created by liji on 16-7-16.
 */
public class AuthorPostAda extends BaseQuickAdapter<M_Post> {
    private Context context;
    private PostListPhotoAda ada;

    public AuthorPostAda(Context context, List<M_Post> data) {
        super(R.layout.item_author_post, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final M_Post m_post) {
        try {
            String[] date = m_post.getTime().substring(0, 10).split("-");
            String day = date[2];
            String month = date[1];
            String year = date[0];
            baseViewHolder.setText(R.id.item_tv_title, "" + m_post.getTitle());
            baseViewHolder.setText(R.id.item_tv_content, "" + m_post.getContent());
            baseViewHolder.setText(R.id.item_tv_day, "" + day);
            baseViewHolder.setText(R.id.item_tv_month, "" + JTimeUtils.getDate(month));
        } catch (Exception e) {
            JLogUtils.E(e);
        }

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
