package com.liji.jkidney.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a.a.V;
import com.liji.jkidney.R;
import com.liji.jkidney.utils.XCallbackListener;

/**
 * 作者：liji on 2016/6/27 10:04
 * 邮箱：lijiwork@sina.com
 */
public class CustomeHeadView extends RelativeLayout {

    private ImageView item_head_img_back;
    private TextView item_head_tv_title;
    private TextView item_head_tv_action;
    private final Integer LOC_TYPE_LEFT = 0;//左边
    private final Integer LOC_TYPE_CENTER = 1;//中间
    private final Integer LOC_TYPE_RIGHT = 2;//右边

    private LayoutInflater mLayoutInflater;

    public CustomeHeadView(Context context) {
        this(context, null);
    }

    public CustomeHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomeHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mLayoutInflater = LayoutInflater.from(getContext());
        View view = mLayoutInflater.inflate(R.layout.item_headview, this);
        item_head_img_back = (ImageView) view.findViewById(R.id.item_head_img_back);
        item_head_tv_title = (TextView) view.findViewById(R.id.item_head_tv_title);
        item_head_tv_action = (TextView) view.findViewById(R.id.item_head_tv_action);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        click(0, title, null);
    }

    /**
     * 返回
     *
     * @param listener
     */
    public void setBack(XCallbackListener listener) {
        click(-1, null, listener);
    }

    /**
     * 右边操作
     *
     * @param message
     * @param listener
     */
    public void setRightAction(String message, XCallbackListener listener) {
        click(1, message, listener);
    }

    private void click(Integer type, String message, final XCallbackListener listener) {
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
                item_head_tv_action.setVisibility(View.VISIBLE);
                item_head_tv_action.setText("" + message);
                item_head_tv_action.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.call();
                    }
                });
                break;
        }

    }
}
