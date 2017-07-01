package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/27.
 */

public class Fragment_register extends Fragment implements View.OnClickListener {
    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_passwordVerity;
    private EditText et_emailVerity;
    private Button btn_register;
    private CountDownTimer countDownTimer;
    private Button btn_sendEamil;
    private Boolean canClick;
    private String email;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        init(view);
        btn_sendEamil.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        return view;
    }
    private void init(View view)
    {
        et_name= (EditText) view.findViewById(R.id.et_register_name);
        canClick=true;
        et_email= (EditText) view.findViewById(R.id.et_register_email);
        et_password= (EditText) view.findViewById(R.id.et_register_password);
        et_passwordVerity= (EditText) view.findViewById(R.id.et_register_passwordverity);
        et_emailVerity= (EditText) view.findViewById(R.id.et_register_emailVerity);
        btn_sendEamil= (Button) view.findViewById(R.id.btn_sendemailverity);
        btn_register= (Button) view.findViewById(R.id.btn_register_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_sendemailverity:
                if(canClick) {
                    email = et_email.getText().toString();
                    VerityEmailProcess(email);
                    startCountTimer();
                }
                break;
            case R.id.btn_register_register:
                String name =et_name.getText().toString();
                String password=et_password.getText().toString();
                String code=et_emailVerity.getText().toString();
                String verity=et_passwordVerity.getText().toString();
                if(verity.equals(password)) {
                    RegisterProcess(name, password, code, email);
                    RegisterinJmessage(email,password);
                }
                else
                    Toast.makeText(getContext(),"please input the same password",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    public void RegisterProcess(String name,String password,String code,String email )
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setCode(code);
        user.setEmail(email);;
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        retrofit2.Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    if(resp.getResult().equals(Constants.SUCCESS))
                    {
                        Toast.makeText(getContext(),resp.getMessage(),Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.fl_login_contain,new Fragment_login()).commit();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void VerityEmailProcess(String email)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        User user=new User();
        user.setEmail(email);
        ServerRequest request=new ServerRequest();
        request.setUser(user);
        request.setOperation(Constants.SENDEMAILVERITY_OPERATION);
        retrofit2.Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    if(resp.getResult().equals(Constants.SUCCESS))
                    {
                        Toast.makeText(getContext(),"please check your email",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getContext(),"please input the right email",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(retrofit2.Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startCountTimer() {
        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_sendEamil.setText(millisUntilFinished / 1000 + "s");
                canClick = false;

            }

            @Override
            public void onFinish() {
                canClick = true;
            }
        }.start();
    }
    public void RegisterinJmessage(String name,String password)
    {
        JMessageClient.register(name, password, new CreateGroupCallback() {
            @Override
            public void gotResult(int i, String s, long l) {
                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
