package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Activity_other_things extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohters_thing);
        Intent intent=getIntent();
        toolbar= (Toolbar) findViewById(R.id.tb_activity_other_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_graph);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        });
        switch (intent.getStringExtra("type"))
        {
            case "myintruduce":
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_myintruduce()).commit();
                break;
            case "feedback":
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_fedeback()).commit();
                break;
            case "hotJob":
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_hotjob()).commit();
                break;
            case "studentJob":
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_studentJob()).commit();
                break;
            case "weekendjob":
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_weekend()).commit();
                break;
            case "comunicate":
                getFragmentManager().beginTransaction().replace(R.id.activity_other_contain,new Fragment_new_comunicate()).commit();
            default:
                break;
        }

    }
}
