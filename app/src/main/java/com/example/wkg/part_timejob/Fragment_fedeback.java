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

public class Fragment_fedeback extends Fragment implements View.OnClickListener {
    private EditText et_feedback_content_edit;
    private EditText et_feedback_number;
    private Button btn_sumbit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_feedback,container,false);
        et_feedback_content_edit= (EditText) view.findViewById(R.id.et_feedback_content_edit);
        et_feedback_number= (EditText) view.findViewById(R.id.et_feedback_contact_editnumber);
        btn_sumbit= (Button) view.findViewById(R.id.btn_feedback_submit_button);
        btn_sumbit.setOnClickListener(this);
        return view;
    }
    public void Gotoprocess(String au,String message)
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        ServerRequest request=new ServerRequest();
        request.setOperation(Constants.OPERATIONFEEDBACK);
        request.setAuthor(au);
        request.setContent(message);
        Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    Toast.makeText(getContext(),"成功反馈",Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_feedback_submit_button:
                String au=et_feedback_number.getText().toString();
                String mes=et_feedback_content_edit.getText().toString();
                Gotoprocess(au,mes);
                break;
            default:
                break;
        }
    }
}
