package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_feedback_submit_button:
                break;
            default:
                break;
        }
    }
}
