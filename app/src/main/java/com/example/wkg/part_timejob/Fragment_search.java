package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
 * Created by Administrator on 2017/7/4.
 */

public class Fragment_search extends Fragment {
    EditText et_search;
    RecyclerView rv_search;
    fmainAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_all,container,false);
        et_search= (EditText) view.findViewById(R.id.et_search);
        adapter=new fmainAdapter(new ArrayList<fmain>());
        rv_search= (RecyclerView) view.findViewById(R.id.rv_search);
        rv_search.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_search.setAdapter(adapter);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                gotoSearchProcess(et_search.getText().toString());

            }
        });
        return view;
    }
    void gotoSearchProcess(String str)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_infor requestInterface_infor=retrofit.create(RequestInterface_infor.class);
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPERATIONSEARCHJOB);
        request.setJobname(str);
        Call<ServerResponse>responseCall=requestInterface_infor.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    ArrayList<Job>data=resp.getSearchJob();
                    if(data!=null)
                    {
                        for (int i=0;i<data.size();i++)
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
