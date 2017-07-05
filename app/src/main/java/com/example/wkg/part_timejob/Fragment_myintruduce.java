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
 * Created by Administrator on 2017/7/3.
 */

public class Fragment_myintruduce extends Fragment {
    EditText et_nickname;
    EditText et_birthday;
    EditText et_realname;
    EditText et_school;
    EditText et_major;
    EditText et_gotoschool;
    EditText et_signal;
    EditText et_swiith;
    Button btn_commit;
    String user_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_intruduce,container,false);
        et_nickname= (EditText) view.findViewById(R.id.et_mNickName);
        et_birthday= (EditText) view.findViewById(R.id.et_mBirthdayTextView);
        et_realname= (EditText) view.findViewById(R.id.et_mRealNameEditText);
        et_school= (EditText) view.findViewById(R.id.et_mSchoolName);
        et_major= (EditText) view.findViewById(R.id.et_mMajorEditText);
        et_gotoschool= (EditText) view.findViewById(R.id.et_mEnrolDateTextView);
        et_signal= (EditText) view.findViewById(R.id.et_mTagsTextView);
        et_swiith= (EditText) view.findViewById(R.id.et_mDeclaration);
        btn_commit= (Button) view.findViewById(R.id.btn_intruduce_commit);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id=((application)getActivity().getApplication()).getId();
                if(user_id!=null) {
                    String name=et_nickname.getText().toString();
                    String sex="男";
                    String birth=et_birthday.getText().toString();
                    String school=et_school.getText().toString();
                    String major=et_major.getText().toString();
                    String edustate=et_gotoschool.getText().toString();
                    String tag=et_signal.getText().toString();
                    String content=et_swiith.getText().toString();
                    String realname=et_realname.getText().toString();
                    updata(name,sex,birth,school,major,edustate,tag,content,realname);
                }
                else
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void updata(String name, String sex, String birth, String school, String major, String edustate, String tag, String content,String realname) {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_resume requestInterface_resume=retrofit.create(RequestInterface_resume.class);
        ServerRequest request=new ServerRequest();
        request.setUser_id(user_id);
        request.setOperation(Constants.OPERATIONUPDATERESUME);
        request.setName(name);
        request.setSex(sex);
        request.setBirthday(birth);
        request.setIsStudent("1");
        request.setRealname(realname);
        request.setSchool(school);
        request.setMajor(major);
        request.setEduStartDate("2017-7-4");
        request.setTag(tag);
        request.setContent(content);
        Call<ServerResponse>responseCall=requestInterface_resume.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    Toast.makeText(getContext(),"上传成功",Toast.LENGTH_SHORT).show();
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
