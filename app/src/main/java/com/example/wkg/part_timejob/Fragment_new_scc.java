package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wkg.part_timejob.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Fragment_new_scc extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager myviewpager;
    //fragment的集合，对应每个子页面
    private ArrayList<android.support.v4.app.Fragment> fragments;
    private View view;
    //选项卡中的按钮
    private Button btn_first;
    private Button btn_second;
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的集合
    private int[] widthArgs;
    //所有按钮的集合
    private Button[] btnArgs;
    Fragment_new_market_information f1=new Fragment_new_market_information();
    Fragment_Web f2= new Fragment_Web();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view=inflater.inflate(R.layout.fragment_new_conversation,container,false);
        initView();
        return view;
    }

    private void initView() {
        myviewpager = (ViewPager)view.findViewById(R.id.myviewpager_scc);
        btn_first = (Button)view.findViewById(R.id.btn_first_scc);
        btn_second = (Button)view.findViewById(R.id.btn_second_scc);

        btnArgs = new Button[]{btn_first,btn_second};

        cursor = (ImageView)view.findViewById(R.id.cursor_btn_scc);
        cursor.setBackgroundColor(Color.RED);

        myviewpager.setOnPageChangeListener(this);
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        fragments = new ArrayList<android.support.v4.app.Fragment>();
        fragments.add(f1);
        fragments.add(f2);
        FragmentAdapter adapter = new FragmentAdapter(((MainActivity)getActivity()).getSupportFragmentManager(),fragments);
        myviewpager.setAdapter(adapter);
        resetButtonColor();
        btn_first.setTextColor(Color.RED);
    }

    private void resetButtonColor() {
        btn_first.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_second.setBackgroundColor(Color.parseColor("#DCDCDC"));
        btn_first.setTextColor(Color.BLACK);
        btn_second.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first_scc:
                myviewpager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.btn_second_scc:
                myviewpager.setCurrentItem(1);
                cursorAnim(1);
                break;
        }

    }

    private void cursorAnim(int curItem) {
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
        lp.width = widthArgs[curItem]-btnArgs[0].getPaddingLeft()*2;
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for(int i=0; i<curItem; i++){
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX+btnArgs[curItem].getPaddingLeft());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(widthArgs==null){
            widthArgs = new int[]{btn_first.getWidth(),
                    btn_second.getWidth()};

        }
        //每次滑动首先重置所有按钮的颜色
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为红色
        btnArgs[position].setTextColor(Color.RED);
        cursorAnim(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
