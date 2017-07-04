package com.example.wkg.part_timejob;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wkg.part_timejob.Fragment_main.fmain;
import com.example.wkg.part_timejob.Fragment_main.fmainAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Fragment_new_market_information extends Fragment implements fmainAdapter.MyItemClickListener {
    RecyclerView rv_market;
    fmainAdapter adapter;
    ArrayList<Job>data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_market_one,container,false);
        rv_market= (RecyclerView) view.findViewById(R.id.rv_new_markget_information);
        adapter=new fmainAdapter(new ArrayList<fmain>());
        rv_market.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_market.setAdapter(adapter);
        adapter.setmItemClickListener(this);
        getdata();
        return view;
    }
    void getdata()
    {
        Retrofit retrofit =new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_collect requestInterface=retrofit.create(RequestInterface_collect.class);
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPEARTIONALLJIOB);
        Call<ServerResponse> responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null) {
                    data= resp.getAllJob();
                    if(data!=null) {
                        for (int i = 0; i < data.size(); i++)
                            adapter.addData(data.get(i));
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
    public void onItemClick(View view, int postion) {
        Uri uri=Uri.parse("http://www.baidu.com");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
