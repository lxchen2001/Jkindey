package com.liji.jkidney.activity.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.activity.check.ActCheckStatistics;
import com.liji.jkidney.activity.post.comment.PostCpmmentAda;
import com.liji.jkidney.activity.user.ActLogin;
import com.liji.jkidney.model.User;
import com.liji.jkidney.model.check.MCheckType;
import com.liji.jkidney.model.post.MComment;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.pop.PopPostComment;
import com.liji.jkidney.utils.JTimeUtils;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.Recyclerview.RecycleViewDivider;
import com.liji.jkidney.widget.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
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
    @ViewInject(R.id.tv_no_comment)
    TextView tv_no_comment;
    @ViewInject(R.id.item_tv_time)
    TextView itemTvTime;
    @ViewInject(R.id.tv_title)
    TextView tvTitle;
    @ViewInject(R.id.item_tv_content)
    TextView itemTvContent;
    @ViewInject(R.id.tv_comment_num)
    TextView tv_comment_num;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;
    @ViewInject(R.id.recyclerviewComment)
    RecyclerView recyclerviewCommnet;
    @ViewInject(R.id.ll_comment)
    RelativeLayout llComment;
    PostCpmmentAda postCpmmentAda;
    PostDetailPhotoAda ada;
    M_Post post;
    List<MComment> commentList = new ArrayList<>();

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

        llComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popComment();
            }

        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(ActPostDetail.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewCommnet.setLayoutManager(layoutManager);
//        //设置item的分割线
//        recyclerviewCommnet.addItemDecoration(new RecycleViewDivider(ActPostDetail.this, LinearLayoutManager.HORIZONTAL));
        postCpmmentAda = new PostCpmmentAda(ActPostDetail.this, commentList);
        postCpmmentAda.openLoadAnimation();
        recyclerviewCommnet.setAdapter(postCpmmentAda);

        //查询帖子评论
        loadCommentData();


    }

    /**
     * 添加评论
     */
    private void popComment() {
        if (User.getCurrentUser(ActPostDetail.this) != null) {
            PopPostComment comment = new PopPostComment(ActPostDetail.this, new com.liji.jkidney.utils.XCallbackListener() {
                @Override
                protected void callback(Object... obj) {
                    String commnetStr = (String) obj[0];
                    doComment(commnetStr);
                }
            });
            comment.show();
        } else {
            Intent intent = new Intent(ActPostDetail.this, ActLogin.class);
            startActivity(intent);
        }
    }


    /**
     * 查询帖子评论
     */
    private void loadCommentData() {
        BmobQuery<MComment> query = new BmobQuery<>();
        M_Post postComment = new M_Post();
        postComment.setObjectId(post.getObjectId());
        query.addWhereEqualTo("post", postComment);
        query.include("author");
        query.order("-createdAt");
        query.findObjects(ActPostDetail.this, new FindListener<MComment>() {
            @Override
            public void onSuccess(List<MComment> list) {
                if (list != null && list.size() > 0) {
                    postCpmmentAda.setNewData(list);
                    tv_no_comment.setVisibility(View.GONE);
                } else {
                    tv_no_comment.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int i, String s) {
                JToastUtils.showToast(ActPostDetail.this, "" + s);

            }
        });


    }

    /**
     * 发表评论
     *
     * @param comemnt
     */
    private void doComment(String comemnt) {
        MyUser user = User.getCurrentUser(ActPostDetail.this);
        M_Post postComment = new M_Post();
        postComment.setObjectId(post.getObjectId());
        MComment mComment = new MComment();
        mComment.setAuthor(user);
        mComment.setContent(comemnt);
        mComment.setPost(postComment);
        mComment.setTime(JTimeUtils.getCurrentTime(0));
        mComment.save(ActPostDetail.this, new SaveListener() {
            @Override
            public void onSuccess() {
                JToastUtils.showToast(ActPostDetail.this, "评论成功！");
                updateCommentNum();
                loadCommentData();
            }


            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActPostDetail.this, "评论失败：" + s);
            }
        });

    }

    /**
     * 更新评论数量
     */
    private void updateCommentNum() {
        M_Post commentNum = new M_Post();
        commentNum.setCommentNum(post.getCommentNum() + 1);
        commentNum.update(ActPostDetail.this, post.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {
                JToastUtils.showToast(ActPostDetail.this, "" + s);
            }
        });

    }


    /**
     * 加载默认数据
     *
     * @param defaultData
     */
    public void setDefaultData(M_Post defaultData) {
        if (defaultData == null)
            return;
        tvTitle.setText("" + defaultData.getTitle());
        itemTvTime.setText("" + defaultData.getTime());
        itemTvNickname.setText("" + defaultData.getAuthor().getNickname());
        itemTvContent.setText("" + defaultData.getContent());
        tv_comment_num.setText("" + defaultData.getCommentNum());
        imageLoader.displayImage(defaultData.getAuthor().getHeadimg(), itemHeadIco, new ImageSize(80, 80));
        if (defaultData.getPostImg() != null && defaultData.getPostImg().size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            recyclerview.setLayoutManager(new GridLayoutManager(ActPostDetail.this, 1));
            ada = new PostDetailPhotoAda(ActPostDetail.this, defaultData.getPostImg());
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
