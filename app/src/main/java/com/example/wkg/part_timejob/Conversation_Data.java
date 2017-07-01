package com.example.wkg.part_timejob;

import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/6/26.
 */

public class Conversation_Data {
    String imagePath;
    String theName;
    String theConversation;
    String theData;
    UserInfo info;
    public void setImagePath(String n){imagePath=n;}
    public void setInfo(UserInfo info){this.info=info;}
    public void setTheName(String n){theName=n;}
    public void setTheConversation(String n){theConversation=n;}
    public void setTheData(String n){theData=n;}
    public String getImagePath(){return imagePath;}
    public String getTheName(){return theName;}
    public String getTheConversation(){return theConversation;}
    public String getTheData(){return theData;}
    public UserInfo getInfo(){return info;}
}
