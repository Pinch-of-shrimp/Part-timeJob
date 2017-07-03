package com.example.wkg.part_timejob;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ServerResponse {
    private String result;
    private String message;
    private User user;
    private Job job;
    private ArrayList<Job> hotJob;
    private ArrayList<Job>nearbyJob;
    private ArrayList<Job>allJob;
   // private String hotJob;
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
    public Job getJob(){return job;}
    public ArrayList<Job>getCity(){return hotJob;}

    public ArrayList<Job> getNearbyJob() {
        return nearbyJob;
    }
    //public  String getHotJob(){return hotJob;}
}
