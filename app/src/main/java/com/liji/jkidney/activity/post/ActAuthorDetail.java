package com.liji.jkidney.activity.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liji.jkidney.R;
import com.liji.jkidney.activity.ActBase;
import com.liji.jkidney.model.post.M_Post;
import com.liji.jkidney.model.user.MyUser;
import com.liji.jkidney.utils.JToastUtils;
import com.liji.jkidney.utils.JViewsUtils;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.widget.CustomeHeadView;
import com.liji.jkidney.widget.Recyclerview.MyLinearLayoutManager;
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
import cn.bmob.v3.listener.FindListener;

@ContentView(R.layout.activity_author_detail)
public class ActAuthorDetail extends ActBase implements SwipeRefreshLayout.OnRefreshListener {


    @ViewInject(R.id.headview)
    CustomeHeadView headview;
    @ViewInject(R.id.head_bg)
    ImageView headBg;
    @ViewInject(R.id.item_head_ico)
    RoundImageView itemHeadIco;
    @ViewInject(R.id.item_tv_nickname)
    TextView itemTvNickname;
    @ViewInject(R.id.item_tv_asign)
    TextView itemTvAsign;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;
//    @ViewInject(R.id.swipeLayout)
//    SwipeRefreshLayout swipeLayout;

    MyUser author;
    ImageLoader imageLoader = ImageLoader.getInstance();
    public static final String AUTHOR = "author";

    private List<M_Post> m_postList = new ArrayList<>();
    private AuthorPostAda postAda = null;
    boolean isRefresh = true;

    @Override
    protected void initView(Bundle savedInstanceState) {
        author = (MyUser) this.getIntent().getSerializableExtra(AUTHOR);
        headview.setTitle("TA的圈");
        headview.setBackgroundColor(getResources().getColor(R.color.color_tab_post));
        headview.setBack(new XCallbackListener() {
            @Override
            protected void callback(Object... obj) {
                finish();
            }
        });

        itemTvNickname.setText("" + author.getNickname());
        itemTvAsign.setText("" + author.getInfo());
        imageLoader.displayImage(author.getHeadimg(), itemHeadIco, new ImageSize(120, 120));


        recyclerview.setLayoutManager(new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerview.setNestedScrollingEnabled(false);
        //设置item的分割线
        recyclerview.addItemDecoration(new RecycleViewDivider(ActAuthorDetail.this, LinearLayoutManager.HORIZONTAL));

        postAda = new AuthorPostAda(ActAuthorDetail.this, m_postList);
        //设置数据适配器
        postAda.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(ActAuthorDetail.this, ActPostDetail.class);
                intent.putExtra(ActPostDetail.POST_DETAIL, (Serializable) postAda.getData().get(i));
                startActivity(intent);
            }
        });
        postAda.openLoadAnimation();
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(postAda);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        isRefresh = true;
        BmobQuery<M_Post> query = new BmobQuery<>();
        query.order("createdAt");
        query.addWhereEqualTo("author", author);
        query.include("author");
        query.findObjects(ActAuthorDetail.this, new FindListener<M_Post>() {
            @Override
            public void onSuccess(List<M_Post> list) {
//                swipeLayout.setRefreshing(false);
                if (list.isEmpty() || (list != null && list.size() == 0)) {
                    postAda.setEmptyView(JViewsUtils.getEmptyView(ActAuthorDetail.this, recyclerview));
                } else {
                    if (isRefresh) {
                        postAda.setNewData(list);
                    }
                }

            }

            @Override
            public void onError(int i, String s) {
//                swipeLayout.setRefreshing(false);
                JToastUtils.showToast(ActAuthorDetail.this, "" + s);

            }
        });
    }

    @Override
    public void onRefresh() {
        loadData();
    }

}
