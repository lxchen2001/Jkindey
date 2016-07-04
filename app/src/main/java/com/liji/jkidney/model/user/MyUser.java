package com.liji.jkidney.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobUser;

/**
 * 作者：liji on 2016/7/4 16:12
 * 邮箱：lijiwork@sina.com
 */
public class MyUser extends BmobUser implements Parcelable {
    private Boolean sex;
    private String nick;
    private Integer age;


    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.sex);
        dest.writeString(this.nick);
        dest.writeValue(this.age);
    }

    public MyUser() {
    }

    protected MyUser(Parcel in) {
        this.sex = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.nick = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
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
