package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wkg.part_timejob.Constants;
import com.example.wkg.part_timejob.Fragment_main.fmain;
import com.example.wkg.part_timejob.Fragment_main.fmainAdapter;
import com.example.wkg.part_timejob.Job;
import com.example.wkg.part_timejob.R;
import com.example.wkg.part_timejob.RequestInterface;
import com.example.wkg.part_timejob.RequestInterface_collect;
import com.example.wkg.part_timejob.ServerRequest;
import com.example.wkg.part_timejob.ServerResponse;
import com.example.wkg.part_timejob.application;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/4.
 */

public class Fragment_myfavorite extends Fragment {
    RecyclerView rv_myfavorite;
    fmainAdapter adapter;
    String user_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_myfavoite,container,false);
        rv_myfavorite= (RecyclerView) view.findViewById(R.id.rv_myfavorite);
        adapter=new fmainAdapter(new ArrayList<fmain>());
        rv_myfavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_myfavorite.setAdapter(adapter);
        user_id=((application)getActivity().getApplication()).getId();
        if (user_id!=null)
            gotomyfavorite();
        else
            Toast.makeText(getContext(),"请先登录",Toast.LENGTH_SHORT).show();
        return view;
    }

    private void gotomyfavorite() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_collect requestInterface=retrofit.create(RequestInterface_collect.class);
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPERATIONGETALLFAVORITE);
        request.setUser_id(user_id);
        Call<ServerResponse>respCall=requestInterface.operation(request);
        respCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    ArrayList<Job>data=resp.getSearchCollection();
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
