package com.liji.jkidney.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liji.jkidney.R;

/**
 * 作者：liji on 2016/7/5 17:09
 * 邮箱：lijiwork@sina.com
 */
public class JViewsUtils {

    public static View getEmptyView(Context context, View parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.empty_view, (ViewGroup) parent.getParent(), false);
        return view;

    }
}
