package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liji on 16-6-29.
 */
public class M_HealthyInfoShow implements Parcelable {

    Integer count;
    Integer fcount;
    Integer id;
    Integer infoclass;
    Integer rcount;
    boolean status;
    Double time;

    String title;
    String url;
    String message;
    String keywords;
    String description;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeValue(this.time);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.message);
        dest.writeString(this.keywords);
        dest.writeString(this.description);
    }

    public M_HealthyInfoShow() {
    }

    protected M_HealthyInfoShow(Parcel in) {
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fcount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.infoclass = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rcount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = in.readByte() != 0;
        this.time = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.url = in.readString();
        this.message = in.readString();
        this.keywords = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<M_HealthyInfoShow> CREATOR = new Parcelable.Creator<M_HealthyInfoShow>() {
        @Override
        public M_HealthyInfoShow createFromParcel(Parcel source) {
            return new M_HealthyInfoShow(source);
        }

        @Override
        public M_HealthyInfoShow[] newArray(int size) {
            return new M_HealthyInfoShow[size];
        }
    };
}
