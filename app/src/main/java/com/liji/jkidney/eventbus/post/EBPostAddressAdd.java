package com.liji.jkidney.eventbus.post;

import com.liji.jkidney.model.post.MAddress;

/**
 * 帖子首页刷新类
 * 发帖成功后通知首页刷新
 * 作者：liji on 2016/7/25 09:49
 * 邮箱：lijiwork@sina.com
 */
public class EBPostAddressAdd {

    private MAddress address;

    public EBPostAddressAdd(MAddress address) {
        this.address = address;
    }

    public MAddress getAddress() {
        return address;
    }
}
