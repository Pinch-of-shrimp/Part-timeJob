package com.example.wkg.part_timejob.Splash;

/**
 * Created by WKG on 2017/7/2.
 */

public class Perinfor {
    private String text;
    private int imageId;
    public Perinfor(String text,int imageId)
    {
        this.text=text;
        this.imageId=imageId;
    }
    public String getText()
    {
        return text;
    }
    public int getImageId()
    {
        return imageId;
    }
}
