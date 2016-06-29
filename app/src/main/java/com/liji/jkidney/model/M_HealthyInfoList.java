package com.liji.jkidney.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/29 17:04
 * 邮箱：lijiwork@sina.com
 */
public class M_HealthyInfoList implements Parcelable {
    Integer count;
    Integer fcount;
    Integer id;
    Integer infoclass;
    Integer rcount;
    Double time;

    String img;
    String title;
    String description;
    String keywords;


    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFcount() {
        return fcount;
    }

    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoclass() {
        return infoclass;
    }

    public void setInfoclass(Integer infoclass) {
        this.infoclass = infoclass;
    }

    public Integer getRcount() {
        return rcount;
    }

    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getImg() {
        return "http://tnfs.tngou.net/image"+img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.count);
        dest.writeValue(this.fcount);
        dest.writeValue(this.id);
        dest.writeValue(this.infoclass);
        dest.writeValue(this.rcount);
        dest.writeValue(this.time);
        dest.writeString(this.img);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.keywords);
    }

    public M_HealthyInfoList() {

    }

    protected M_HealthyInfoList(Parcel in) {
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fcount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.infoclass = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rcount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.time = (Double) in.readValue(Double.class.getClassLoader());
        this.img = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.keywords = in.readString();
    }

    public static final Parcelable.Creator<M_HealthyInfoList> CREATOR = new Parcelable.Creator<M_HealthyInfoList>() {
        @Override
        public M_HealthyInfoList createFromParcel(Parcel source) {
            return new M_HealthyInfoList(source);
        }

        @Override
        public M_HealthyInfoList[] newArray(int size) {
            return new M_HealthyInfoList[size];
        }
    };
}
