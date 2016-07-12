package com.liji.jkidney.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobUser;

/**
 * 作者：liji on 2016/7/4 16:12
 * 邮箱：lijiwork@sina.com
 */
public class MyUser extends BmobUser {
    String nickname;
    Integer age;
    String sex;
    String career;
    String address;
    String info;
    String headimg;
    String comfirmPwd;

    public String getComfirmPwd() {
        return comfirmPwd;
    }

    public void setComfirmPwd(String comfirmPwd) {
        this.comfirmPwd = comfirmPwd;
    }

    public Integer getAge() {
        return age == null ? new Integer(0) : age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadimg() {
        return headimg == null ? "" : headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname == null ? "无名" : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex == null ? "男" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCareer() {
        return career == null ? "未知" : career;
    }

    public void setCareer(String career) {
        this.career = career;
    }


    public String getAddress() {
        return address == null ? "中国" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return (info == "" || info == null) ? "这个人很懒，什么也没有留下..." : info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public MyUser() {
    }


}
