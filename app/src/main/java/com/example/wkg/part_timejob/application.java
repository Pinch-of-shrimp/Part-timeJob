package com.example.wkg.part_timejob;

import android.app.Application;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2017/6/29.
 */

public class application extends Application {
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
