package com.example.wkg.part_timejob;

import java.io.Serializable;
import java.util.ServiceLoader;

import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Pack implements Serializable {
    private UserInfo userInfo;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
