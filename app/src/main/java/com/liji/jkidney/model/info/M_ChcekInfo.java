package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/27 17:11
 * 邮箱：lijiwork@sina.com
 */
public class M_ChcekInfo implements Parcelable {
    private String checkName;
    private String checkContent;
    private int checkResourceId;

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public int getCheckResourceId() {
        return checkResourceId;
    }

    public void setCheckResourceId(int checkResourceId) {
        this.checkResourceId = checkResourceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.checkName);
        dest.writeString(this.checkContent);
        dest.writeInt(this.checkResourceId);
    }

    public M_ChcekInfo() {
    }

    protected M_ChcekInfo(Parcel in) {
        this.checkName = in.readString();
        this.checkContent = in.readString();
        this.checkResourceId = in.readInt();
    }

    public static final Parcelable.Creator<M_ChcekInfo> CREATOR = new Parcelable.Creator<M_ChcekInfo>() {
        @Override
        public M_ChcekInfo createFromParcel(Parcel source) {
            return new M_ChcekInfo(source);
        }

        @Override
        public M_ChcekInfo[] newArray(int size) {
            return new M_ChcekInfo[size];
        }
    };
}
