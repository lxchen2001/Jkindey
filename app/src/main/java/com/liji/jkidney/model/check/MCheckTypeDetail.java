package com.liji.jkidney.model.check;

import cn.bmob.v3.BmobObject;

/**
 * 尿蛋白检查指标
 * 作者：liji on 2016/7/6 13:33
 * 邮箱：lijiwork@sina.com
 */
public class MCheckTypeDetail extends BmobObject {

    private String time;
    private String type;
    private Double value;

    public Double getValue() {
        return value == null ? new Double(0) : value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
