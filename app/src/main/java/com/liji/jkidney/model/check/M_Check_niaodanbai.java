package com.liji.jkidney.model.check;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * 尿蛋白检测
 * 作者：liji on 2016/7/4 15:33
 * 邮箱：lijiwork@sina.com
 */
public class M_Check_niaodanbai extends BmobObject implements Parcelable {

    //000400（记录日期）
    //000401（24小时尿量）
    //000402（24小时尿蛋白定量）

    private String time;//检查时间
    private String type;//尿蛋白里面的种类
    private Double value;//尿蛋白里面种类的值

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeString(this.type);
        dest.writeValue(this.value);
    }

    public M_Check_niaodanbai() {
    }

    protected M_Check_niaodanbai(Parcel in) {
        this.time = in.readString();
        this.type = in.readString();
        this.value = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<M_Check_niaodanbai> CREATOR = new Parcelable.Creator<M_Check_niaodanbai>() {
        @Override
        public M_Check_niaodanbai createFromParcel(Parcel source) {
            return new M_Check_niaodanbai(source);
        }

        @Override
        public M_Check_niaodanbai[] newArray(int size) {
            return new M_Check_niaodanbai[size];
        }
    };
}
