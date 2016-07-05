package com.liji.jkidney.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：liji on 2016/7/5 09:25
 * 邮箱：lijiwork@sina.com
 */
public class JToastUtils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
