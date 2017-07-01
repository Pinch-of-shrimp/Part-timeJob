package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/26.
 */

public class Fragment_Market_Information extends Fragment {
    private Toolbar market_toolbar;
    private RadioGroup market_rg_tool;
    private RadioButton market_rb_All;
    private RadioButton market_rb_graph;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_market_information,container,false);
        market_rg_tool= (RadioGroup) view.findViewById(R.id.rg_title);
        market_toolbar= (Toolbar) view.findViewById(R.id.tb_scc);
        market_rb_All= (RadioButton) market_rg_tool.findViewById(R.id.rb_recommend);
        market_rb_graph= (RadioButton) market_rg_tool.findViewById(R.id.rb_classify);
        market_rg_tool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_recommend:
                        Toast.makeText(getContext(),"this is the information fragment",Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.Chirld_containt,new Fragment_Market_one()).commit();
                        break;
                    case R.id.rb_classify:
                        Toast.makeText(getContext(),"this is the graph fragment",Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.Chirld_containt,new Fragment_Web()).commit();
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
}
