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
     * 获取当前时间年月日
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
     * 获取当前时间年月日
     *
     * @return
     */
    public static String getCurrentTime(int type) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

    /**
     * 将数字月份转为大写的月份
     *
     * @param month
     * @return
     */
    public static String getMonth(String month) {
        String result = "";
        switch (month) {
            case "01":
                result = "一月";
                break;
            case "02":
                result = "二月";
                break;
            case "03":
                result = "三月";
                break;
            case "04":
                result = "四月";
                break;
            case "05":
                result = "五月";
                break;
            case "06":
                result = "六月";
                break;
            case "07":
                result = "七月";
                break;
            case "08":
                result = "八月";
                break;
            case "09":
                result = "九月";
                break;
            case "10":
                result = "十月";
                break;
            case "11":
                result = "十一月";
                break;
            case "12":
                result = "十二月";
                break;

        }
        return result;

    }

}
