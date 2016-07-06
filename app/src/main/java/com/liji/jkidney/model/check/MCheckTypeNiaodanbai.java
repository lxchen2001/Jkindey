package com.liji.jkidney.model.check;

import com.liji.jkidney.model.user.MyUser;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 尿蛋白检查指标
 * 作者：liji on 2016/7/6 13:33
 * 邮箱：lijiwork@sina.com
 */
public class MCheckTypeNiaodanbai extends BmobObject {

    private MyUser author;

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    private List<MCheckTypeNiaodanbaiDetail> list;

    public List<MCheckTypeNiaodanbaiDetail> getList() {
        return list;
    }

    public void setList(List<MCheckTypeNiaodanbaiDetail> list) {
        this.list = list;
    }
}
