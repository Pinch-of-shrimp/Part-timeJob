package com.example.wkg.part_timejob.Splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wkg.part_timejob.R;

import java.util.List;

/**
 * Created by WKG on 2017/7/2.
 */

public class PerinforAdapter extends ArrayAdapter<Perinfor> {
    private int resourceId;//判断是那一个item
    public PerinforAdapter(Context context, int resource, List<Perinfor> objects) {
        super(context, resource,objects);
        resourceId=resource;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Perinfor perinfor=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView iv_image=(ImageView)view.findViewById(R.id.iv_lv_image);
        TextView tv_name=(TextView) view.findViewById(R.id.tv_lv_name);
        //more 图标的显示
        ImageView iv_moreimage=(ImageView)view.findViewById(R.id.image_more);
        iv_moreimage.setImageResource(R.drawable.more);
        iv_image.setImageResource(perinfor.getImageId());
        tv_name.setText(perinfor.getText());
        return view;

    }
}
