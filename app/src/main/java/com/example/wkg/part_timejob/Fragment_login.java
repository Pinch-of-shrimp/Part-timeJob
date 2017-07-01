package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.prefs.Preferences;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/27.
 */

public class Fragment_login extends Fragment implements View.OnClickListener {
    private EditText et_email;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_forgetPassword;
    private TextView tv_changePassword;
    private SharedPreferences pref;
    private AlertDialog dialog_FindPassword;
    private AlertDialog dialog_ChangePassword;
    private EditText et_findpas_email;
    private EditText et_findpas_code;
    private EditText et_findpas_password;
    private Button btn_findpas_sendEmail;
    private ImageView iv_findpas_password;
    private ImageView iv_findpas_code;
    private EditText et_changepas_email;
    private EditText et_changepas_oldpassword;
    private EditText et_changepas_newpassword;
    private EditText et_changepas_newpassword_verity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login_layout,container,false);
        init(view);
        pref=getActivity().getPreferences(0);
        btn_login.setOnClickListener(this);
        tv_forgetPassword.setOnClickListener(this);
        tv_changePassword.setOnClickListener(this);
        return view;
    }
    private void showFindPassDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_findpassword,null);
        et_findpas_email= (EditText) view.findViewById(R.id.et_findpas_email);
        et_findpas_code= (EditText) view.findViewById(R.id.et_findpas_code);
        et_findpas_password= (EditText) view.findViewById(R.id.et_findpas_password);
        iv_findpas_code= (ImageView) view.findViewById(R.id.iv_finpas_bindtocode);
        iv_findpas_password= (ImageView) view.findViewById(R.id.iv_finpas_bindtopassword);
        btn_findpas_sendEmail= (Button) view.findViewById(R.id.btn_findpas_sendemail);
        btn_findpas_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=et_findpas_email.getText().toString();
                VerityEmailProcess(email);
                iv_findpas_password.setVisibility(View.VISIBLE);
                iv_findpas_code.setVisibility(View.VISIBLE);
                et_findpas_password.setVisibility(View.VISIBLE);
                et_findpas_code.setVisibility(View.VISIBLE);
                btn_findpas_sendEmail.setVisibility(View.GONE);
            }
        });
        builder.setView(view);
        builder.setTitle("找回密码");
        builder.setPositiveButton("找回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog_FindPassword=builder.create();
        dialog_FindPassword.show();
        dialog_FindPassword.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=et_findpas_email.getText().toString();
                String code=et_findpas_code.getText().toString();
                String password=et_findpas_password.getText().toString();
                if(email!=null&&code!=null&&password!=null)
                {
                    FindPasProcess(email,code,password);
                }
                else
                    Toast.makeText(getContext(),"输入有效数据",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void init(View view)
    {
        et_email= (EditText) view.findViewById(R.id.et_email);
        et_password= (EditText) view.findViewById(R.id.et_password);
        btn_login= (Button) view.findViewById(R.id.btn_login);
        tv_changePassword= (TextView) view.findViewById(R.id.tv_changePassword);
        tv_forgetPassword= (TextView) view.findViewById(R.id.tv_findPassword);
    }
    private void showChangePassDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_changepassword,null);
        et_changepas_email= (EditText) view.findViewById(R.id.et_changepassword_email);
        et_changepas_oldpassword= (EditText) view.findViewById(R.id.et_changepassword_password);
        et_changepas_newpassword= (EditText) view.findViewById(R.id.et_changepassword_newpas);
        et_changepas_newpassword_verity= (EditText) view.findViewById(R.id.et_changepassword_verity);
        builder.setView(view);
        builder.setTitle("更改密码");
        builder.setPositiveButton("更改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog_ChangePassword=builder.create();
        dialog_ChangePassword.show();
        dialog_ChangePassword.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpas=et_changepas_oldpassword.getText().toString();
                String newpas=et_changepas_newpassword.getText().toString();
                String verity=et_changepas_newpassword_verity.getText().toString();
                if(oldpas!=null&&newpas!=null&&verity!=null)
                    changePasProcess(pref.getString(Constants.EMAIL,""),oldpas,newpas,verity);
                else
                    Toast.makeText(getContext(),"输入有效数据",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void changePasProcess(String email,String oldpas,String newpas,String verity)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        User user=new User();
        user.setEmail(email);
        user.setOld_password(oldpas);
        user.setName(newpas);
        user.setPassword_new_veity(verity);
        final ServerRequest request=new ServerRequest();
        request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
        request.setUser(user);
        Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    if(resp.getResult().equals(Constants.SUCCESS))
                    {
                        dialog_ChangePassword.dismiss();
                        Toast.makeText(getContext(),resp.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(),resp.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getContext(),"resp is null",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login:
                String email=et_email.getText().toString();
                String password=et_password.getText().toString();
                if(email!=null&&password!=null) {
                    loginProcess(email, password);
                    LoginInJmessgae(email,password);
                }
                else
                    Toast.makeText(getContext(),"输入有效的密码或邮箱",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_changePassword:
                showChangePassDialog();
                break;
            case R.id.tv_findPassword:
                showFindPassDialog();
                break;
        }
    }
    public void FindPasProcess(String email,String code,String password)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        User user=new User();
        user.setEmail(email);
        user.setCode(code);
        user.setPassword(password);
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.RESET_PASSWORD_FINISH);
        request.setUser(user);
        Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null) {
                    if (resp.getResult().equals(Constants.SUCCESS))
                        dialog_FindPassword.dismiss();
                        Toast.makeText(getContext(), resp.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(),"no resp",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loginProcess(String email,String password)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        final ServerRequest request=new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                    Toast.makeText(getContext(),resp.getMessage(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"the body is null",Toast.LENGTH_SHORT).show();
                if(resp.getResult().equals(Constants.SUCCESS))
                {
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
                    editor.putString(Constants.NAME,resp.getUser().getName());
                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnqiue_id());
                    editor.apply();
                    startActivity(new Intent(getContext(),MainActivity.class));
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
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
        request.setOperation(Constants.RESET_PASSWORD_INITIATE);
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
    public void LoginInJmessgae(String name,String password)
    {
        JMessageClient.login(name, password, new CreateGroupCallback() {
            @Override
            public void gotResult(int i, String s, long l) {
                Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
