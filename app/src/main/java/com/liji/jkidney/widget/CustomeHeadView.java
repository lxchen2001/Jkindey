package com.liji.jkidney.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liji.jkidney.R;
import com.liji.jkidney.utils.XCallbackListener;

/**
 * 作者：liji on 2016/6/27 10:04
 * 邮箱：lijiwork@sina.com
 */
public class CustomeHeadView extends RelativeLayout {

    private ImageView item_head_img_back;
    private ImageView item_head_right_img_action;
    private ImageView item_head_right_img_action2;
    private TextView item_head_tv_title;
    private TextView item_head_tv_action;
    private RelativeLayout item_head_rl_bg;
    private final Integer LOC_TYPE_LEFT = 0;//左边
    private final Integer LOC_TYPE_CENTER = 1;//中间
    private final Integer LOC_TYPE_RIGHT = 2;//右边

    private LayoutInflater mLayoutInflater;

    public CustomeHeadView(Context context) {
        super(context);
        initView(context);
    }

    public CustomeHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomeHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mLayoutInflater = LayoutInflater.from(getContext());
        View view = mLayoutInflater.inflate(R.layout.item_headview, this);
        item_head_img_back = (ImageView) view.findViewById(R.id.item_head_img_back);
        item_head_right_img_action = (ImageView) view.findViewById(R.id.item_head_right_img_action);
        item_head_right_img_action2 = (ImageView) view.findViewById(R.id.item_head_right_img_action2);
        item_head_rl_bg = (RelativeLayout) view.findViewById(R.id.item_head_rl_bg);
        item_head_tv_title = (TextView) view.findViewById(R.id.item_head_tv_title);
        item_head_tv_action = (TextView) view.findViewById(R.id.item_head_tv_action);
    }

    public void setBackgroundColor(int color) {
        item_head_rl_bg.setBackgroundColor(color);

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        click(0, null, title, null);
    }

    /**
     * 返回
     *
     * @param listener
     */
    public void setBack(XCallbackListener listener) {
        click(-1, null, null, listener);
    }

    /**
     * 右边文字操作
     *
     * @param message
     * @param listener
     */
    public void setRightAction(String message, XCallbackListener listener) {
        click(1, null, message, listener);
    }

    public void setRightImgAction(int resourceId, XCallbackListener listener) {
        click(1, resourceId, null, listener);
    }

    public void setRightImgAction2(int resourceId, XCallbackListener listener) {
        click(2, resourceId, null, listener);
    }

    private void click(Integer type, Integer resourceId, String message, final XCallbackListener listener) {
        switch (type) {
            case -1:
                item_head_img_back.setVisibility(View.VISIBLE);
                item_head_img_back.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.call();
                    }
                });
                break;
            case 0:
                item_head_tv_title.setVisibility(View.VISIBLE);
                item_head_tv_title.setText("" + message);
                break;
            case 1:
                if (resourceId == null) {
                    item_head_tv_action.setVisibility(View.VISIBLE);
                    item_head_right_img_action.setVisibility(View.GONE);
                    item_head_tv_action.setText("" + message);
                    item_head_tv_action.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.call();
                        }
                    });
                } else if (resourceId != null) {
                    item_head_right_img_action.setVisibility(View.VISIBLE);
                    item_head_tv_action.setVisibility(View.GONE);
                    item_head_right_img_action.setBackgroundResource(resourceId);
                    item_head_right_img_action.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.call();
                        }
                    });
                }
                break;
            case 2:
                if (resourceId != null) {
                    item_head_right_img_action2.setVisibility(View.VISIBLE);
                    item_head_tv_action.setVisibility(View.GONE);
                    item_head_right_img_action2.setBackgroundResource(resourceId);
                    item_head_right_img_action2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.call();
                        }
                    });
                }
                break;

        }

    }
}
