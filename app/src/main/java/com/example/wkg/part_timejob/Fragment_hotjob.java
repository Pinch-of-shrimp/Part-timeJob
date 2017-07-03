package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
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

public class Fragment_hotjob extends Fragment {
    RecyclerView rv_hotjob;
    fmainAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hotjob,container,false);
        rv_hotjob= (RecyclerView) view.findViewById(R.id.rv_hotjob);
        rv_hotjob.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new fmainAdapter(new ArrayList<fmain>());
        rv_hotjob.setAdapter(adapter);
        rv_hotjob.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        getdataProcess("沙坪坝区");
        return view;
    }

    void getdataProcess(String city)
    {
        Retrofit retrofit =new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_infor requestInterface=retrofit.create(RequestInterface_infor.class);
        User user=new User();
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPERATIONHOTJOB);
        request.setCity(city);
        request.setUser(user);
        Call<ServerResponse> responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=new ServerResponse();
                resp=response.body();
                if(resp!=null)
                {
                    ArrayList<Job>data=resp.getCity();
                    if(data!=null)
                    {
                        for(int i=0;i<data.size();i++)
                        {
                            adapter.addData(data.get(i));
                        }
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

}
