package com.liji.jkidney.utils;

import android.content.Context;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 网络操作相关类，包含上传和下载
 * 作者：liji on 2016/7/12 13:26
 * 邮箱：lijiwork@sina.com
 */
public class JHttpUtils {

    /**
     * 上传类
     *
     * @param context
     * @param file     文件地址
     * @param callback 上传后回调
     */
    public static void requset(final Context context, String file, final HttpCallback callback) {

        final BmobFile bmobFile = new BmobFile(new File(file));
        bmobFile.upload(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                JLogUtils.D("success: " + bmobFile.getFileUrl(context));
                callback.success(bmobFile.getFileUrl(context));
            }

            @Override
            public void onFailure(int i, String s) {
                JLogUtils.D("error: " + s);
                callback.failure(s);
            }
        });
    }
}
