package com.liji.jkidney.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

/**
 * 图片工具类
 * 作者：liji on 2016/7/20 16:24
 * 邮箱：lijiwork@sina.com
 */
public class JBitmapUtils {

    /**
     * 获取原始图片的宽和高
     *
     * @param picPath
     * @return
     */
    public static int[] getBitmapWidthAndHeight(String picPath) {
        if (TextUtils.isEmpty(picPath))
            return null;
        int[] size = new int[2];
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);
            size[0] = options.outWidth;
            size[1] = options.outHeight;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return size;
    }

}
