package com.example.wkg.part_timejob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ListAdapter extends BaseAdapter {
    ArrayList<String>data;
    TextView tv_name;
    ImageView iv_image;
    LayoutInflater inflater;
    public ListAdapter(ArrayList<String>buff, Context context)
    {
        data=buff;
        inflater=LayoutInflater.from(context);
    }
    public void addData(String n)
    {
        data.add(n);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.lv_personal_layout,parent,false);
        tv_name= (TextView) view.findViewById(R.id.tv_lv_name);
        iv_image= (ImageView) view.findViewById(R.id.iv_lv_image);
        tv_name.setText(data.get(position));
        return view;
    }
}
