package com.liji.jkidney.model;

import android.content.Context;

import com.liji.jkidney.model.user.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * 作者：liji on 2016/7/5 16:55
 * 邮箱：lijiwork@sina.com
 */
public class User {

    public static MyUser getCurrentUser(Context context) {
        return BmobUser.getCurrentUser(context, MyUser.class);
    }
}
