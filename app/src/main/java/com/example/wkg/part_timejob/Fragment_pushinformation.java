package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Fragment_pushinformation extends Fragment {
    Button btn_commit;
    EditText et_1;
    EditText et_2;
    //结算方式
    EditText et_3;
    EditText et_4;
    EditText et_5;
    EditText et_6;
    EditText et_7;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_pushinformation,container,false);
        btn_commit= (Button) view.findViewById(R.id.btn_employ_commit);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoapply(et_1.getText().toString(),et_2.getText().toString(),et_3.getText().toString(),et_4.getText().toString(),et_5.getText().toString(),et_6.getText().toString(),et_7.getText().toString(),"日结","临时工","4","室内，风雨无阻","年龄16-20","工作亲送");

                Toast.makeText(getContext(),"上传成功",Toast.LENGTH_SHORT).show();
            }
        });
        init(view);
        return view;
    }
    void gotoapply(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10,String s11,String s12,String s13)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_apply requestInterface_apply=retrofit.create(RequestInterface_apply.class);
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPERATIONSCOMMIT);
        request.setJobtitle(s1);
        request.setProvince(s2);
        request.setCity(s3);
        request.setStartdate(s4);
        request.setEnddate(s5);
        request.setWorktime(s6);
        request.setSalary(s7);
        request.setSalarytyep(s8);
        request.setWorktype(s9);
        request.setPeoplenumb(s10);
        request.setDescription(s11);
        request.setRequire(s12);
        request.setWorkcontent(s13);
        Call<ServerResponse>responseCall=requestInterface_apply.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                    Toast.makeText(getContext(),"发布成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void init(View view) {
        et_1= (EditText) view.findViewById(R.id.ev_con_name);
        et_2= (EditText) view.findViewById(R.id.et_e12v_salary);
        et_3= (EditText) view.findViewById(R.id.et_com_job_type);
        et_4= (EditText) view.findViewById(R.id.et_need_person);
        et_5= (EditText) view.findViewById(R.id.ed_job_needs);
        et_6= (EditText) view.findViewById(R.id.et_com_descrip);
        et_7= (EditText) view.findViewById(R.id.et_jobcontent);
    }

}
