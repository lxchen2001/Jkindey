package com.liji.jkidney.model.tool;

import com.liji.jkidney.model.user.MyUser;

import cn.bmob.v3.BmobObject;

/**
 * 记事本功能
 * Created by liji on 16-7-7.
 */
public class MNote extends BmobObject {
    private String time;
    private String title;
    private String content;
    private MyUser author;

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
