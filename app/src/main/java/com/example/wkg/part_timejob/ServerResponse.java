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
    private ArrayList<Job>weekendJob;
    private ArrayList<Job>recommendJob;
    private ArrayList<Job>searchJob;
    private ArrayList<Job>searchCollection;
    private ArrayList<Job>getJobState;
    private ArrayList<Job>getAnalysis;

    public ArrayList<Job> getGetAnalysis() {
        return getAnalysis;
    }

    public ArrayList<Job> getGetJobState() {
        return getJobState;
    }

    // private String hotJob;

    public ArrayList<Job> getSearchCollection() {
        return searchCollection;
    }

    public ArrayList<Job> getSearchJob() {
        return searchJob;
    }

    public ArrayList<Job> getRecommendJob() {
        return recommendJob;
    }

    public ArrayList<Job> getWeekendJob() {
        return weekendJob;
    }

    public ArrayList<Job>getAllJob(){return allJob;}
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
