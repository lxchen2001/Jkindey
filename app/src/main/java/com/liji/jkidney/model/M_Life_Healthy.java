package com.liji.jkidney.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liji on 16-6-28.
 */
public class M_Life_Healthy implements Parcelable {

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ctime);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.picUrl);
        dest.writeString(this.url);
    }

    public M_Life_Healthy() {
    }

    protected M_Life_Healthy(Parcel in) {
        this.ctime = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.picUrl = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<M_Life_Healthy> CREATOR = new Parcelable.Creator<M_Life_Healthy>() {
        @Override
        public M_Life_Healthy createFromParcel(Parcel source) {
            return new M_Life_Healthy(source);
        }

        @Override
        public M_Life_Healthy[] newArray(int size) {
            return new M_Life_Healthy[size];
        }
    };
}
