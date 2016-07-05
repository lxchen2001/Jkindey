package com.liji.jkidney.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobUser;

/**
 * 作者：liji on 2016/7/4 16:12
 * 邮箱：lijiwork@sina.com
 */
public class MyUser extends BmobUser implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.nickname);
        dest.writeString(this.sex);
        dest.writeString(this.career);

        dest.writeString(this.address);
        dest.writeString(this.info);
    }

    public MyUser() {
    }

    protected MyUser(Parcel in) {

        this.nickname = in.readString();
        this.sex = in.readString();
        this.career = in.readString();

        this.address = in.readString();
        this.info = in.readString();
    }

    public static final Parcelable.Creator<MyUser> CREATOR = new Parcelable.Creator<MyUser>() {
        @Override
        public MyUser createFromParcel(Parcel source) {
            return new MyUser(source);
        }

        @Override
        public MyUser[] newArray(int size) {
            return new MyUser[size];
        }
    };
}
