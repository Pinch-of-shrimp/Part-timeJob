package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/5.
 */

public class fragment_pushinformation extends Fragment {
    Button btn_commit;
    EditText et_1;
    EditText et_2;
    EditText et_3;
    EditText et_4;
    EditText et_5;
    EditText et_6;
    EditText et_7;
    EditText et_8;
    EditText et_9;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_pushinformation,container,false);
        btn_commit= (Button) view.findViewById(R.id.btn_save);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"上传成功",Toast.LENGTH_SHORT).show();
            }
        });
        init(view);
        return view;
    }

    private void init(View view) {
        et_1= (EditText) view.findViewById(R.id.et_mNickName);
        et_2= (EditText) view.findViewById(R.id.et_mName);
        et_3= (EditText) view.findViewById(R.id.et_ev_salary);
        et_4= (EditText) view.findViewById(R.id.et_mRealNameEditText);
        et_5= (EditText) view.findViewById(R.id.et_mSchoolName);
    }

}
