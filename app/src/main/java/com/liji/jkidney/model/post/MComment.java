package com.liji.jkidney.model.post;

import com.liji.jkidney.model.user.MyUser;

import cn.bmob.v3.BmobObject;

/**
 * 帖子评论
 * 作者：liji on 2016/7/18 16:42
 * 邮箱：lijiwork@sina.com
 */
public class MComment extends BmobObject {

    private String content;
    private String time;
    private MyUser author;
    private M_Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public M_Post getPost() {
        return post;
    }

    public void setPost(M_Post post) {
        this.post = post;
    }
}
