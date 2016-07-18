package com.liji.jkidney.activity.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bmob.v3.listener.UpdateListener;

/**
 * 帖子详情
 */
@ContentView(R.layout.activity_post_detail)
public class ActPostDetail extends ActBase {

    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.item_head_ico)
    RoundImageView itemHeadIco;
    @ViewInject(R.id.item_tv_nickname)
    TextView itemTvNickname;
    @ViewInject(R.id.item_tv_time)
    TextView itemTvTime;
    @ViewInject(R.id.tv_title)
    TextView tvTitle;
    @ViewInject(R.id.item_tv_content)
    TextView itemTvContent;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;

    PostDetailPhotoAda ada;
    M_Post post;

    public static String POST_DETAIL = "post";
    ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void initView(Bundle savedInstanceState) {

        post = (M_Post) this.getIntent().getSerializableExtra(POST_DETAIL);

        setDefaultData(post);

        headview.setTitle("详情");
        headview.setBackgroundColor(getResources().getColor(R.color.color_tab_post));
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        itemHeadIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActPostDetail.this, ActAuthorDetail.class);
                intent.putExtra(ActAuthorDetail.AUTHOR, post.getAuthor());
                startActivity(intent);
            }
        });

    }


    public void setDefaultData(M_Post defaultData) {
        if (defaultData == null)
            return;
        tvTitle.setText("" + defaultData.getTitle());
        itemTvTime.setText("" + defaultData.getTime());
        itemTvNickname.setText("" + defaultData.getAuthor().getNickname());
        itemTvContent.setText("" + defaultData.getContent());
        imageLoader.displayImage(defaultData.getAuthor().getHeadimg(), itemHeadIco, new ImageSize(80, 80));
        if (defaultData.getPostImg() != null && defaultData.getPostImg().size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            recyclerview.setLayoutManager(new GridLayoutManager(ActPostDetail.this, 1));
            ada = new PostDetailPhotoAda(defaultData.getPostImg());
            recyclerview.setNestedScrollingEnabled(false);
            recyclerview.setAdapter(ada);
        } else {
            recyclerview.setVisibility(View.GONE);
        }

        M_Post setSeeNum = new M_Post();
        setSeeNum.setSeeNum(defaultData.getSeeNum() + 1);
        setSeeNum.update(ActPostDetail.this, defaultData.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActPostDetail.this, "" + s);
            }
        });


    }
}
