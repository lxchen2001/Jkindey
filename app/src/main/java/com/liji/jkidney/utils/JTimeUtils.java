package com.liji.jkidney.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：liji on 2016/6/29 17:21
 * 邮箱：lijiwork@sina.com
 */
public class JTimeUtils {


    public static String getTime(Double time) throws ParseException {
        //时间戳转化为Sting或Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        return d;

    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(date.getTime());
        return d;
    }


    /**
     * 将2016-07-09转化为07/09
     *
     * @param date
     * @return
     */
    public static String getDate(String date) {
        String[] data = date.split("-");
        return data[1] + "/" + data[2];
    }

}
