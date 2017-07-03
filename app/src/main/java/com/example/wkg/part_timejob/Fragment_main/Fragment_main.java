package com.example.wkg.part_timejob.Fragment_main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyLocationStyleCreator;
import com.example.wkg.part_timejob.Activity_MainMap;
import com.example.wkg.part_timejob.Constants;
import com.example.wkg.part_timejob.Job;
import com.example.wkg.part_timejob.R;
import com.example.wkg.part_timejob.RequestInterface;
import com.example.wkg.part_timejob.RequestInterface_infor;
import com.example.wkg.part_timejob.ServerRequest;
import com.example.wkg.part_timejob.ServerResponse;
import com.example.wkg.part_timejob.User;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WKG on 2017/6/24.
 */

public class Fragment_main extends Fragment {
    /**
     * Created by WKG on 2017/7/1.
     */

    private List<fmain> fmainList=new ArrayList<>();
    private Toolbar toolbar;
    private Banner banner;
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;
    fmainAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        banner = (Banner)view.findViewById(R.id.banner);
        toolbar= (Toolbar) view.findViewById(R.id.tb_mainfragment);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.document);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Activity_MainMap.class));
            }
        });
        getdataProcess();
        InitData();

        /*
        实现内容
        create by wkg 7.1
         */
        Initmian_item();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.fmain_recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new fmainAdapter(fmainList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        return view;
    }
    void getdataProcess()
    {
        Retrofit retrofit =new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_infor requestInterface=retrofit.create(RequestInterface_infor.class);
        User user=new User();
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPEARTIONALLJIOB);
        Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null) {
                    ArrayList<Job> data_job= resp.getCity();
                    if(data_job!=null) {
                        for (int i = 0; i < data_job.size(); i++)
                            adapter.addData(data_job.get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
    private void  Initmian_item(){
        for (int i=0;i<4;i++)
        {
            fmain item1=new fmain("麦当劳","沙坪坝","6:00-8:00","100元/天","日结");
            fmain item2=new fmain("麦当劳","沙坪坝","6:00-8:00","100元/天","日结");
            fmain item3=new fmain("麦当劳","沙坪坝","6:00-8:00","100元/天","日结");
            fmain item4=new fmain("麦当劳","沙坪坝","6:00-8:00","100元/天","日结");
            fmainList.add(item1);
            fmainList.add(item2);
            fmainList.add(item3);
            fmainList.add(item4);
        }
    }
}
