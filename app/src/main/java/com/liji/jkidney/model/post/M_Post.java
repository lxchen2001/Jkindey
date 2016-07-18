package com.liji.jkidney.model.post;

import com.liji.jkidney.model.user.MyUser;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 发表的主题
 * Created by liji on 16-7-16.
 */
public class M_Post extends BmobObject {

    private String title;
    private String content;
    private String time;
    private String address;
    private MyUser author;

    //浏览次数
    private Integer seeNum;
    //评论次数
    private Integer commentNum;

    public Integer getCommentNum() {
        return commentNum == null ? new Integer(0) : commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getSeeNum() {
        return seeNum == null ? new Integer(0) : seeNum;
    }

    public void setSeeNum(Integer seeNum) {
        this.seeNum = seeNum;
    }

    private List<String> postImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public List<String> getPostImg() {
        return postImg;
    }

    public void setPostImg(List<String> postImg) {
        this.postImg = postImg;
    }
}
