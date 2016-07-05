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
    String sex;
    String career;
    String address;
    String info;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public MyUser() {
    }


}
