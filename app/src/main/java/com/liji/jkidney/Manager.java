package com.liji.jkidney;

import android.content.Context;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 作者：liji on 2016/7/21 15:06
 * 邮箱：lijiwork@sina.com
 */
public class Manager {

    /**
     * 用户别名
     */
    public static String USERID = "";

    /**
     * 开启推送
     *
     * @param context
     */
    public static void startJpush(Context context) {
        Context mContext = context.getApplicationContext();
        JPushInterface.resumePush(mContext);
        JPushInterface.setAlias(mContext, USERID, new TagAliasCallbackImpl(context));
    }

    /**
     * 关闭推送
     *
     * @param context
     */
    public static void stopJpush(Context context) {
        Context mContext = context.getApplicationContext();
        JPushInterface.stopPush(mContext);
    }

    public static class TagAliasCallbackImpl implements TagAliasCallback {
        Context context;

        public TagAliasCallbackImpl(Context context) {
            this.context = context;
        }

        @Override
        public void gotResult(int responseCode, String alias, Set<String> tags) {
            if (responseCode != 0)
                startJpush(context);
        }
    }


}
