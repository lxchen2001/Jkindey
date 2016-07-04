package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/30 08:56
 * 邮箱：lijiwork@sina.com
 */
public class M_HealthyKnowledgeClassicfy implements Parcelable {
    String description;
    String title;
    String name;
    String keywords;

    Integer id;
    Integer seq;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.keywords);
        dest.writeValue(this.id);
        dest.writeValue(this.seq);
    }

    public M_HealthyKnowledgeClassicfy() {
    }

    protected M_HealthyKnowledgeClassicfy(Parcel in) {
        this.description = in.readString();
        this.title = in.readString();
        this.name = in.readString();
        this.keywords = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.seq = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<M_HealthyKnowledgeClassicfy> CREATOR = new Parcelable.Creator<M_HealthyKnowledgeClassicfy>() {
        @Override
        public M_HealthyKnowledgeClassicfy createFromParcel(Parcel source) {
            return new M_HealthyKnowledgeClassicfy(source);
        }

        @Override
        public M_HealthyKnowledgeClassicfy[] newArray(int size) {
            return new M_HealthyKnowledgeClassicfy[size];
        }
    };
}
