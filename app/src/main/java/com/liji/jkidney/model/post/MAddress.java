package com.liji.jkidney.model.post;

import cn.bmob.v3.BmobObject;

/**
 * 作者：liji on 2016/7/26 13:01
 * 邮箱：lijiwork@sina.com
 */
public class MAddress extends BmobObject {

    String title;
    String address;
    String id;
    String category;
    Double _distance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double get_distance() {
        return _distance == null ? new Double(0.0) : _distance;
    }

    public void set_distance(Double _distance) {
        this._distance = _distance;
    }
}
