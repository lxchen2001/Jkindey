package com.liji.jkidney.utils;

/**
 * 作者：liji on 2016/7/12 13:28
 * 邮箱：lijiwork@sina.com
 */
public interface HttpCallback {

    void success(String result);

    void failure(String message);
}
