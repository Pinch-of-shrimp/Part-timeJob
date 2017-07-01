package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by WKG on 2017/6/24.
 */

public class Fragment_person_information extends Fragment implements OnClickListener{
    private TextView tv_register;
    private ListView lv_group_one;
    private SharedPreferences pref;
    private ListView lv_group_two;
    @Nullable
    /*
    给listview添加内容first
     */
    private String[] group_one={"我的兼职","我的简历","我的收藏","我的偏好"};
    private String[] group_two={"我要找人","联系客服","投诉和反馈"};
    private ArrayList<String>data1;
    private ArrayList<String>data2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.person_infor,container,false);
        tv_register= (TextView) view.findViewById(R.id.tv_register);
        lv_group_one= (ListView) view.findViewById(R.id.lv_group_one);
        lv_group_two= (ListView) view.findViewById(R.id.lv_group_two);
        pref=getActivity().getPreferences(0);
        tv_register.setOnClickListener(this);
        if(pref.getBoolean(Constants.IS_LOGGED_IN,true)) {
            tv_register.setText("welcome:" + pref.getString(Constants.EMAIL, ""));
            
        }
        data1=new ArrayList<>();
        data2=new ArrayList<>();
        for(int i=0;i<group_one.length;i++)
            data1.add(group_one[i]);
        for (int i=0;i<group_two.length;i++)
            data2.add(group_two[i]);
        lv_group_one.setAdapter(new ListAdapter(data1,getContext()));
        lv_group_two.setAdapter(new ListAdapter(data2,getContext()));
        lv_group_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"the position is :"+position,Toast.LENGTH_SHORT).show();
            }
        });
        lv_group_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"the position is:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_register:
              //  Toast.makeText(getContext(),"this is register",Toast.LENGTH_SHORT).show();
                //need to juege he state if not login jump to the register otherwise stay
                Boolean buff=pref.getBoolean(Constants.IS_LOGGED_IN,false);
                if(pref.getBoolean(Constants.IS_LOGGED_IN,false)==false)
                    startActivity(new Intent(getActivity(),Activity_LoginFunction.class));
                if (pref.getBoolean(Constants.IS_LOGGED_IN,true))
                {
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,false);
                    editor.putString(Constants.EMAIL,"");
                    editor.putString(Constants.NAME,"");
                    editor.putString(Constants.UNIQUE_ID,"");
                    editor.apply();
                    tv_register.setText("登录/注册");
                }
                break;
            default:
                break;
        }

    }
}
