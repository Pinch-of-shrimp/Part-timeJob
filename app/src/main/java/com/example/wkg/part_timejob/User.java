package com.example.wkg.part_timejob;

/**
 * Created by Administrator on 2017/6/27.
 */

public class User {
    private String name;
    private String email;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;
    private String new_password_verify;
    private String code;
    private String city;


    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword_new_veity(String n){new_password_verify=n;}
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getUnqiue_id()
    {
        return unique_id;
    }
    public void setName(String n)
    {
        name=n;
    }
    public void setEmail(String n)
    {
        email=n;
    }
    public void setCode(String n){code=n;}
    public void setPassword(String n)
    {
        password=n;
    }
    public  void  setOld_password(String n)
    {
        old_password=n;
    }
    public void setNew_password(String n)
    {
        new_password=n;
    }
}
