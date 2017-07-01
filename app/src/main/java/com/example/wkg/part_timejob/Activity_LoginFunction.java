package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/27.
 */

public class Activity_LoginFunction extends AppCompatActivity {
    private Toolbar tb_login;
    private RadioGroup radioGroup;
    private RadioButton rb_login;
    private RadioButton rb_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);
        tb_login= (Toolbar) findViewById(R.id.tb_login);
        tb_login.setNavigationIcon(getResources().getDrawable(R.drawable.back_graph));
        setSupportActionBar(tb_login);
        tb_login.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_LoginFunction.this,MainActivity.class));
            }
        });
        radioGroup= (RadioGroup) findViewById(R.id.rg_login);
        rb_login= (RadioButton) radioGroup.findViewById(R.id.rb_login);
        rb_register= (RadioButton) radioGroup.findViewById(R.id.rb_register);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_login:
                        Fragment f1=new Fragment_login();
                        getFragmentManager().beginTransaction().replace(R.id.fl_login_contain,f1).commit();
                        break;
                    case R.id.rb_register:
                        Fragment f2=new Fragment_register();
                        getFragmentManager().beginTransaction().replace(R.id.fl_login_contain,f2).commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
