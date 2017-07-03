package com.example.wkg.part_timejob;

/**
 * Created by Administrator on 2017/6/27.
 */

public  class ServerRequest {
    private String operation;
    private User user;
    private String city;
    private Job job;
    private String province;

    public void setProvince(String province) {
        this.province = province;
    }

    public void setOperation(String operation)
    {
        this.operation=operation;
    }
    public void setUser(User user)
    {
        this.user=user;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    public void setCity(String city)
    {
        this.city=city;
    }
}
