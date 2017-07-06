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
    private String name;
    private String sex;
    private String birthday;
    private String isStudent;
    private String realname;
    private String school;
    private String major;
    private String eduStartDate;
    private String tag;
    private String state;
    private String statement;
    private String jobtitle,startdate,enddate,worktime,salary,salarytyep,worktype,peoplenumb,description,require,workcontent;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public void setPeoplenumb(String peoplenumb) {
        this.peoplenumb = peoplenumb;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSalarytyep(String salarytyep) {
        this.salarytyep = salarytyep;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setWorkcontent(String workcontent) {
        this.workcontent = workcontent;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEduStartDate(String eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    public void setIsStudent(String isStudent) {
        this.isStudent = isStudent;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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
