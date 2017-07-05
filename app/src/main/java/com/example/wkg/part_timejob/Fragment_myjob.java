package com.example.wkg.part_timejob;

import android.app.Fragment;
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
 * Created by Administrator on 2017/7/5.
 */

public class Fragment_myjob extends Fragment {
    RecyclerView rv_myjob;
    fmainAdapter adapter;
    ArrayList<Job>data;
    String user_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_myjob,container,false);
        rv_myjob= (RecyclerView) view.findViewById(R.id.rv_myjob);
        adapter=new fmainAdapter(new ArrayList<fmain>());
        rv_myjob.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_myjob.setAdapter(adapter);
        user_id=((application)getActivity().getApplication()).getId();
        if(user_id!=null)
            getData();
        else
            Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
        return view;
    }

    private void getData() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_apply requestinterface=retrofit.create(RequestInterface_apply.class);
        ServerRequest request=new ServerRequest();
        request.setUser_id(user_id);
        request.setOperation(Constants.OPERATIONGETJOBSTATE);
        request.setState("2");
        Call<ServerResponse>responseCall=requestinterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null) {
                    data=resp.getGetJobState();
                    if(data!=null)
                    {
                        for (int i=0;i<data.size();i++)
                            adapter.addData(data.get(i));
                    }
                }
                else
                    Toast.makeText(getContext(),"查看失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
