package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.wkg.part_timejob.Fragment_main.Fragment_main;

import cn.jpush.im.android.api.JMessageClient;


public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_contain;
    private SharedPreferences pref;
    Fragment f4;
    /*
    变量声明
     */
    private BottomNavigationBar bottom_navigation_bar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(f4!=null) {
            f4.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref=getPreferences(0);
        fl_contain= (FrameLayout) findViewById(R.id.fragment_containt);
        initBottomNavigationBar();
        getFragmentManager().beginTransaction().replace(R.id.fragment_containt,new Fragment_main()).commit();
        initJmessage();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void initJmessage()
    {
        JMessageClient.init(getApplicationContext(),true);
    }

    private void initBottomNavigationBar(){
        //找到对应的页面布局---BottomNavigationBar
        bottom_navigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
       /*
       add in 7.1 by wkg crweate
        */
        bottom_navigation_bar
                .setActiveColor(R.color.royalblue)
                .setInActiveColor("#FFFFFF")
                .setBarBackgroundColor(R.color.btnmain);
        /*
            设置消息数目
            badge=new BadgeItem()
             .setText("2")；//显示文本
         */
        /*
        添加底部导航栏分别有 “首页  招聘  消息  我的”四个按钮
        V 1.0
        Create by wkg
         */
            bottom_navigation_bar.addItem(new BottomNavigationItem(R.mipmap.home,"首页"))
                    .addItem(new BottomNavigationItem(R.mipmap.document, "招聘"))
                    .addItem(new BottomNavigationItem(R.mipmap.mail, "消息"))
                    .addItem(new BottomNavigationItem(R.mipmap.user, "我的"))
                    .initialise();//初始化
            bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){

            @Override
            public void onTabSelected(int position) {
                //未选中 -> 选中
                /*
                *
                */
                switch (position)
                {
                    case 0:
                        Fragment f1=new Fragment_main();
                        FragmentTransaction ft= getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_containt,f1).commit();
                        break;
                    case 1:
                        Fragment f2=new Fragment_new_scc();
                        FragmentTransaction ft2=getFragmentManager().beginTransaction();
                        ft2.replace(R.id.fragment_containt,f2).commit();
                       // getFragmentManager().beginTransaction().replace(R.id.fragment_containt,new Fragment_Market_Information()).commit();
                        break;
                    case 2:
                        Fragment f3=new Fragment_Conversation();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_containt,f3).commit();
                        break;
                    case 3:
                        f4=new Fragment_person_information();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_containt,f4).commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {
                //选中 -> 未选中
            }

            @Override
            public void onTabReselected(int position) {
                //选中 -> 选中
            }
        });
    }
}
