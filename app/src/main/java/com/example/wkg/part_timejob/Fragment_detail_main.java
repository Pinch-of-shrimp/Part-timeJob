package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wkg.part_timejob.Fragment_main.fmain;
import com.example.wkg.part_timejob.Fragment_main.fmainAdapter;

import java.sql.Ref;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/4.
 */
public class Fragment_detail_main extends Fragment {
    ArrayList<Job>data;
    ImageView iv_callphone;
    int position;
    String user_id;
    String message_id;
    TextView tv_jobname;
    TextView tv_jobplace;
    TextView tv_jobtime;
    TextView tv_jobsalary;
    TextView tv_jobgettype;
    TextView tv_detial_descrip;
    TextView tv_detial_salary;
    TextView tv_tv_detial_jobtime;
    TextView tv_detial_jobpalce;
    TextView tv_detial_interview;
    Button btn_button_apply_job;
    TextView tv_comunicatetocompany;
    fmainAdapter adapter;
    RecyclerView rv_more;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail_main,container,false);
        data=((application)getActivity().getApplication()).getList();
        position=((application)getActivity().getApplication()).getPostion();
        tv_jobname= (TextView) view.findViewById(R.id.jobname);
        user_id=((application)getActivity().getApplication()).getId();
        tv_jobplace= (TextView) view.findViewById(R.id.jobplace);
        tv_jobtime= (TextView) view.findViewById(R.id.jobtime);
        tv_jobsalary= (TextView) view.findViewById(R.id.jobsalary);
        tv_jobgettype= (TextView) view.findViewById(R.id.jobgettype);
        tv_detial_descrip= (TextView) view.findViewById(R.id.tv_detial_descrip);
        tv_detial_salary= (TextView) view.findViewById(R.id.tv_detial_salary);
        tv_tv_detial_jobtime= (TextView) view.findViewById(R.id.tv_detial_jobtime);
        tv_detial_jobpalce= (TextView) view.findViewById(R.id.tv_detial_jobpalce);
        tv_detial_interview= (TextView) view.findViewById(R.id.tv_detial_interview);
        btn_button_apply_job= (Button) view.findViewById(R.id.btn_button_apply_job);
        btn_button_apply_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id!=null)
                    applyforjob();
                else
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
            }
        });
        tv_comunicatetocompany= (TextView) view.findViewById(R.id.tv_contact_company_tv);
        tv_comunicatetocompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
        iv_callphone= (ImageView) view.findViewById(R.id.iv_button_favorite_image);
        iv_callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_callphone.setImageResource(R.drawable.icon_favorite_added);
                if(user_id!=null)
                    addtoFavorite(user_id,message_id);
            }
        });
        rv_more= (RecyclerView) view.findViewById(R.id.rv_fmain_recycler);

        adapter=new fmainAdapter(new ArrayList<fmain>());
        rv_more.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_more.setAdapter(adapter);
        if(data!=null) {
            for (int i = 0; i < data.size(); i++) {
                if(i!=position)
                    adapter.addData(data.get(i));
                else
                {
                    Job job=data.get(i);
                    message_id=job.getJob_id();
                    tv_jobname.setText(job.getJob());
                    tv_jobplace.setText(job.getCity());
                    tv_jobtime.setText(job.getStartdate()+"-"+job.getEnddate());
                    tv_jobsalary.setText(job.getSalary());
                    tv_jobgettype.setText(job.getSalarytype()+"元");
                    tv_detial_descrip.setText(job.getDescription());
                    tv_detial_salary.setText(job.getSalary());
                    tv_tv_detial_jobtime.setText(job.getStartdate()+"-"+job.getEnddate());
                    tv_detial_jobpalce.setText(job.getProvince());
                    tv_detial_interview.setText(job.getWorktype());
                }
            }
        }

        return view;
    }

    private void applyforjob() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_apply requestInterfaceCollect=retrofit.create(RequestInterface_apply.class);
        final ServerRequest request=new ServerRequest();
        request.setUser_id(user_id);
        request.setJob_id(message_id);
        request.setOperation(Constants.OPERATIONAPPLY);
        Call<ServerResponse>responseCall=requestInterfaceCollect.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    void addtoFavorite(String id,String me_id)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_collect requestInterfaceCollect=retrofit.create(RequestInterface_collect.class);
        final ServerRequest request=new ServerRequest();
        request.setUser_id(id);
        request.setJob_id(me_id);
        request.setOperation(Constants.OPERATIONADDTOFAVORITE);
        Call<ServerResponse>responseCall=requestInterfaceCollect.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
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
