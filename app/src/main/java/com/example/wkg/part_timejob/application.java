package com.example.wkg.part_timejob;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.ArrayList;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/6/29.
 */

public class application extends Application {
    private UserInfo info;
    private ArrayList<Job>list;
    private int postion;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setList(ArrayList<Job> list) {
        this.list = list;
    }

    public ArrayList<Job> getList() {
        return list;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public UserInfo getInfo() {
        return info;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //preferences=getSharedPreferences("")
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
