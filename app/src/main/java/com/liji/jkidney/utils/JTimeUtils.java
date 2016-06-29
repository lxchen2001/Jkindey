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
//        Date date=format.parse(d);
//        System.out.println("Format To String(Date):"+d);
//        System.out.println("Format To Date:"+date);
    }

}
