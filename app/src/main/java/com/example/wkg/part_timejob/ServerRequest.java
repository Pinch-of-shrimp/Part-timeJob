package com.example.wkg.part_timejob;

/**
 * Created by Administrator on 2017/6/27.
 */

public  class ServerRequest {
    private String operation;
    private User user;
    private String city;
    private String jobname;
    private Job job;
    private String province;
    private String author;
    private String content;
    private String user_id;
    private String job_id;

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
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
