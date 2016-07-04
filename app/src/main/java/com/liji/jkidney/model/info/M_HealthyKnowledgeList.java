package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/30 09:02
 * 邮箱：lijiwork@sina.com
 */
public class M_HealthyKnowledgeList implements Parcelable {
    String description;
    String img;
    String keywords;
    String title;
    Integer id;
    Integer count;
    Integer fcount;
    Integer loreclass;
    Integer rcount;

    Double time;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return "http://tnfs.tngou.net/image"+img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLoreclass() {
        return loreclass;
    }

    public void setLoreclass(Integer loreclass) {
        this.loreclass = loreclass;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.img);
        dest.writeString(this.keywords);
        dest.writeString(this.title);
        dest.writeValue(this.id);
        dest.writeValue(this.count);
        dest.writeValue(this.fcount);
        dest.writeValue(this.loreclass);
        dest.writeValue(this.rcount);
        dest.writeValue(this.time);
    }

    public M_HealthyKnowledgeList() {
    }

    protected M_HealthyKnowledgeList(Parcel in) {
        this.description = in.readString();
        this.img = in.readString();
        this.keywords = in.readString();
        this.title = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fcount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.loreclass = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rcount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.time = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<M_HealthyKnowledgeList> CREATOR = new Parcelable.Creator<M_HealthyKnowledgeList>() {
        @Override
        public M_HealthyKnowledgeList createFromParcel(Parcel source) {
            return new M_HealthyKnowledgeList(source);
        }

        @Override
        public M_HealthyKnowledgeList[] newArray(int size) {
            return new M_HealthyKnowledgeList[size];
        }
    };
}
