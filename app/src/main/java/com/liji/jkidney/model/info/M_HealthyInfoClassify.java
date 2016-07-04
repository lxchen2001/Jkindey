package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/29 16:15
 * 邮箱：lijiwork@sina.com
 */
public class M_HealthyInfoClassify implements Parcelable {

    String description;
    String keywords;
    String name;
    String title;
    Integer seq;
    Integer id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.keywords);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeValue(this.seq);
        dest.writeValue(this.id);
    }

    public M_HealthyInfoClassify() {
    }

    protected M_HealthyInfoClassify(Parcel in) {
        this.description = in.readString();
        this.keywords = in.readString();
        this.name = in.readString();
        this.title = in.readString();
        this.seq = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<M_HealthyInfoClassify> CREATOR = new Parcelable.Creator<M_HealthyInfoClassify>() {
        @Override
        public M_HealthyInfoClassify createFromParcel(Parcel source) {
            return new M_HealthyInfoClassify(source);
        }

        @Override
        public M_HealthyInfoClassify[] newArray(int size) {
            return new M_HealthyInfoClassify[size];
        }
    };
}
