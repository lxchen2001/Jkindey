package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/29 16:48
 * 邮箱：lijiwork@sina.com
 */
public class M_J_HealthInfoClassicfy implements Parcelable {
    boolean status;
    String tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTngou() {
        return tngou;
    }

    public void setTngou(String tngou) {
        this.tngou = tngou;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeString(this.tngou);
    }

    public M_J_HealthInfoClassicfy() {
    }

    protected M_J_HealthInfoClassicfy(Parcel in) {
        this.status = in.readByte() != 0;
        this.tngou = in.readString();
    }

    public static final Parcelable.Creator<M_J_HealthInfoClassicfy> CREATOR = new Parcelable.Creator<M_J_HealthInfoClassicfy>() {
        @Override
        public M_J_HealthInfoClassicfy createFromParcel(Parcel source) {
            return new M_J_HealthInfoClassicfy(source);
        }

        @Override
        public M_J_HealthInfoClassicfy[] newArray(int size) {
            return new M_J_HealthInfoClassicfy[size];
        }
    };
}
