package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @2Do:
 * @Author M2_
 * @Version v 1.0
 * @Date [2015/10/20]
 */
public class M_Common implements Parcelable {



    private int code;
    
    private String msg;
    
    private String newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNewslist() {
        return newslist;
    }

    public void setNewslist(String newslist) {
        this.newslist = newslist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeString(this.newslist);
    }

    public M_Common() {
    }

    protected M_Common(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.newslist = in.readString();
    }

    public static final Parcelable.Creator<M_Common> CREATOR = new Parcelable.Creator<M_Common>() {
        @Override
        public M_Common createFromParcel(Parcel source) {
            return new M_Common(source);
        }

        @Override
        public M_Common[] newArray(int size) {
            return new M_Common[size];
        }
    };
}
