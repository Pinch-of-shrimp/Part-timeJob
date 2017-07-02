package com.example.wkg.part_timejob.Fragment_main;

import android.support.annotation.VisibleForTesting;

import javax.security.auth.PrivateCredentialPermission;

/**
 * Created by WKG on 2017/7/2.
 */
//永于主页的实现步骤
public class fmain {
    private String Jobname;
    private String Jobplace;
    private String Jobtime;
    private String Jobsalry;
    private String Jobgettye;
    public fmain(String jobname, String jobplace, String jobtime, String jobsalry, String jobgettye)
    {
        this.Jobname=jobname;
        this.Jobplace=jobplace;
        this.Jobtime=jobtime;
        this.Jobsalry=jobsalry;
        this.Jobgettye=jobgettye;
    }
    public String GetJobname()
    {
        return Jobname;
    }
    public String GetJobplace()
    {
        return Jobplace;
    }
    public String GetJobtime()
    {
        return Jobtime;
    }
    public String GetJobsalry()
    {
        return Jobsalry;
    }
    public String GetJobgettye()
    {
        return Jobgettye;
    }
}
