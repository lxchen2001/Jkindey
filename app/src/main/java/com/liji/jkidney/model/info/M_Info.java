package com.liji.jkidney.model.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：liji on 2016/6/28 16:25
 * 邮箱：lijiwork@sina.com
 */
public class M_Info implements Parcelable {
    private String mNewsType;
    private Integer mNewsTypePic;
    private String mNewsTypeContent;

    public String getmNewsType() {
        return mNewsType;
    }

    public void setmNewsType(String mNewsType) {
        this.mNewsType = mNewsType;
    }

    public Integer getmNewsTypePic() {
        return mNewsTypePic;
    }

    public void setmNewsTypePic(Integer mNewsTypePic) {
        this.mNewsTypePic = mNewsTypePic;
    }

    public String getmNewsTypeContent() {
        return mNewsTypeContent;
    }

    public void setmNewsTypeContent(String mNewsTypeContent) {
        this.mNewsTypeContent = mNewsTypeContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mNewsType);
        dest.writeInt(this.mNewsTypePic);
        dest.writeString(this.mNewsTypeContent);
    }

    public M_Info() {
    }

    protected M_Info(Parcel in) {
        this.mNewsType = in.readString();
        this.mNewsTypePic = in.readInt();
        this.mNewsTypeContent = in.readString();
    }

    public static final Parcelable.Creator<M_Info> CREATOR = new Parcelable.Creator<M_Info>() {
        @Override
        public M_Info createFromParcel(Parcel source) {
            return new M_Info(source);
        }

        @Override
        public M_Info[] newArray(int size) {
            return new M_Info[size];
        }
    };
}
