package com.liji.jkidney.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.liji.dev.androidutils.utils.citypickerWheelView.widget.CanShow;
import com.liji.jkidney.utils.XCallbackListener;
import com.liji.jkidney.R;
import com.liji.jkidney.utils.JToastUtils;

/**
 * 评论
 * Created by liji on 16-7-18.
 */
public class PopPostComment implements CanShow {
    Context mContext;

    View contentview;

    PopupWindow popwindow;

    Button btn_comment;
    EditText et_comment;

    public PopPostComment(final Context context, final XCallbackListener listener) {
        mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        contentview = layoutInflater.inflate(R.layout.pop_post_comment, null);
        btn_comment = (Button) contentview.findViewById(R.id.btn_comment);
        et_comment = (EditText) contentview.findViewById(R.id.et_comment);
        popwindow = new PopupWindow(contentview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        popwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popwindow.setBackgroundDrawable(new ColorDrawable(0xB0000000));
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = et_comment.getText().toString().trim();

                if (TextUtils.isEmpty(comment)) {
                    JToastUtils.showToast(context, "请输入评论");
                    return;
                }
                listener.call(comment);
                hide();
            }
        });

        popwindow.update();
    }

    @Override
    public void setType(int i) {

    }

    @Override
    public void show() {
        if (null != popwindow && !popwindow.isShowing())
            popwindow.showAtLocation(contentview.getRootView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void hide() {
        popwindow.dismiss();
        popwindow = null;
    }

    @Override
    public boolean isShow() {
        return popwindow.isShowing();
    }

}
