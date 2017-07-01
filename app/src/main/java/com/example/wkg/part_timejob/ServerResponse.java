package com.example.wkg.part_timejob;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ServerResponse {
    private String result;
    private String message;
    private User user;
    public String getResult()
    {
        return result;
    }
    public String getMessage()
    {
        return message;
    }
    public User getUser()
    {
        return user;
    }
}
