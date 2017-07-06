package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Activity_other_things extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_ohters_thing);
        Intent intent=getIntent();
        toolbar= (Toolbar) findViewById(R.id.tb_activity_other_toolbar);
        setSupportActionBar(toolbar);
        tv_title= (TextView) findViewById(R.id.tv_activity_other_thing);
        toolbar.setNavigationIcon(R.drawable.back_to_graph);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        });
        switch (intent.getStringExtra("type"))
        {
            case "myintruduce":
                toolbar.setTitle("我的简介");
                tv_title.setText("我的简介");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_myintruduce()).commit();
                break;
            case "feedback":
                toolbar.setTitle("反馈");
                tv_title.setText("意见反馈");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_fedeback()).commit();
                break;
            case "hotJob":
                toolbar.setTitle("热门兼职");
                tv_title.setText("热门兼职");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_hotjob()).commit();
                break;
            case "studentJob":
                toolbar.setTitle("学生兼职");
                tv_title.setText("学生兼职");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_studentJob()).commit();
                break;
            case "weekendjob":
                toolbar.setTitle("周末兼职");
                tv_title.setText("周末兼职");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_weekend()).commit();
                break;
            case "comunicate":
                toolbar.setTitle("会话");
                tv_title.setText("消息会话");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_new_comunicate()).commit();
                break;
            case "search":
                toolbar.setTitle("职位搜索");
                tv_title.setText("职位搜索");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_search()).commit();
                break;
            case "detail_main":
                toolbar.setTitle("详细介绍");
                tv_title.setText("详细介绍");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_detail_main()).commit();
                break;
            case "myfavorite":
                toolbar.setTitle("我的收藏");
                tv_title.setText("我的简介");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_myfavorite()).commit();
                break;
            case "myjob":
                toolbar.setTitle("我的兼职");
                tv_title.setText("我的兼职");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_myjob()).commit();
            case "push_information":
                tv_title.setText("招聘发布");
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_pushinformation()).commit();
                break;
            default:
                break;
        }

    }
}
