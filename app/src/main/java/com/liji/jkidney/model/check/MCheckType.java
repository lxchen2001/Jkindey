package com.liji.jkidney.model.check;

import com.liji.jkidney.model.user.MyUser;

import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 尿蛋白检查指标
 * 作者：liji on 2016/7/6 13:33
 * 邮箱：lijiwork@sina.com
 */
public class MCheckType extends BmobObject {

    private MyUser author;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    private List<MCheckTypeDetail> list;

    public List<MCheckTypeDetail> getList() {
        return list;
    }

    public void setList(List<MCheckTypeDetail> list) {
        this.list = list;
    }
}
