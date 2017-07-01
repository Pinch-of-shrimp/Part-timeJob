package com.example.wkg.part_timejob;

/**
 * Created by Administrator on 2017/6/27.
 */

public  class ServerRequest {
    private String operation;
    private User user;
    public void setOperation(String operation)
    {
        this.operation=operation;
    }
    public void setUser(User user)
    {
        this.user=user;
    }
}
