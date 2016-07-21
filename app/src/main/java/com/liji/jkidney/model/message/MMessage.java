package com.liji.jkidney.model.message;

import com.liji.jkidney.model.user.MyUser;

import cn.bmob.v3.BmobObject;

/**
 * 作者：liji on 2016/7/21 17:16
 * 邮箱：lijiwork@sina.com
 */
public class MMessage extends BmobObject{
    String time;
    String content;
    MyUser author;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }
}
