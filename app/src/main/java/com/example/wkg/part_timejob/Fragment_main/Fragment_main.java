package com.example.wkg.part_timejob.Fragment_main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyLocationStyleCreator;
import com.example.wkg.part_timejob.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

/**
 * Created by WKG on 2017/6/24.
 */

public class Fragment_main extends Fragment {
    private Banner banner;
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;
    private MapView mainMAP;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        banner = (Banner)view.findViewById(R.id.banner);
        mainMAP= (MapView) view.findViewById(R.id.main_maps);
        mainMAP.onCreate(savedInstanceState);
        if(myLocationStyle==null)
        {
            myLocationStyle=new MyLocationStyle();

        }
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        InitData();
        if(aMap==null)
        {
            aMap=mainMAP.getMap();
        }
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainMAP.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainMAP.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainMAP.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainMAP.onSaveInstanceState(outState);
    }

    private void InitData()
    {
        //banner=(Banner)getActivity().findViewById(R.id.banner);
        list_path=new ArrayList<>();
        list_path.add(R.drawable.graph1);
        list_path.add(R.drawable.graph2);
        list_path.add(R.drawable.graph3);
        list_path.add(R.drawable.graph4);
        list_title=new ArrayList<>();
        list_title.add("成功");
        list_title.add("兼职信息");
        list_title.add("就业分布");
        list_title.add("职业类别");
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(list_path);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setBannerTitles(list_title);
        banner.setDelayTime(1000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }
}
