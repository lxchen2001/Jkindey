package com.liji.jkidney.model.check;

import android.os.Parcel;
import android.os.Parcelable;

import com.liji.jkidney.model.user.MyUser;

import cn.bmob.v3.BmobObject;

/**
 * 尿蛋白检测
 * 作者：liji on 2016/7/4 15:33
 * 邮箱：lijiwork@sina.com
 */
public class M_Check_niaodanbai extends BmobObject {

    //000400（记录日期）
    //000401（24小时尿量）
    //000402（24小时尿蛋白定量）

    private String time;//检查时间
    private String type1;//24小时尿量
    private String type2;//24小时尿蛋白定量
    private Double valueNiaoliang;//000401
    private Double valueDanbai;//000402

    public String getType1() {
        return type1 == null ? "24小时尿量" : type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2 == null ? "24小时尿蛋白定量" : type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    private MyUser author;

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getValueNiaoliang() {
        return valueNiaoliang;
    }

    public void setValueNiaoliang(Double valueNiaoliang) {
        this.valueNiaoliang = valueNiaoliang;
    }

    public Double getValueDanbai() {
        return valueDanbai;
    }

    public void setValueDanbai(Double valueDanbai) {
        this.valueDanbai = valueDanbai;
    }

    public M_Check_niaodanbai() {
    }


    protected M_Check_niaodanbai(Parcel in) {
        this.time = in.readString();
        this.valueNiaoliang = (Double) in.readValue(Double.class.getClassLoader());
        this.valueDanbai = (Double) in.readValue(Double.class.getClassLoader());
    }


}
