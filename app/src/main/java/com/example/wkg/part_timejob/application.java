package com.example.wkg.part_timejob;

import android.app.Application;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/6/29.
 */

public class application extends Application {
    private UserInfo info;

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public UserInfo getInfo() {
        return info;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
