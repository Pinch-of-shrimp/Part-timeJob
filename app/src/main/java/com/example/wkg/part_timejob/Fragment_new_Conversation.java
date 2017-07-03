package com.example.wkg.part_timejob;

import android.support.v4.app.Fragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wkg.part_timejob.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class Fragment_new_Conversation extends Fragment {
    private ViewPager viewPager;// 声明一个viewpager对象
    private TextView tv1;
    private TextView tv2;
    private ImageView tabline;
    private List<Fragment> list;// 声明一个list集合存放Fragment（数据源）

    private int tabLineLength;// 1/2屏幕宽
    private int currentPage = 0;// 初始化当前页为0（第一页）

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_conversation,container,false);
        initTabLine(view);
        initView(view);
        return view;

    }
    private void initView(View view) {
        // 实例化对象
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        list = new ArrayList<Fragment>();

        // 设置数据源
        Fragment fragment1 = new Fragment_new_showFriend();
        //Fragment fragment2 = new Fragment_new_comunicate();

        list.add(fragment1);
        //list.add(fragment2);

        // 设置适配器
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(
                getFragmentManager()) {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return list.get(arg0);
            }
        };

        // 绑定适配器
        viewPager.setAdapter(adapter);

        // 设置滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 当页面被选择时，先讲2个textview的字体颜色初始化成黑
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);

                // 再改变当前选择页（position）对应的textview颜色
                switch (position) {
                    case 0:
                        tv1.setTextColor(Color.rgb(51, 153, 0));
                        break;
                    case 1:
                        tv2.setTextColor(Color.rgb(51, 153, 0));
                        break;
                }

                currentPage = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                //Log.i("tuzi", arg0 + "," + arg1 + "," + arg2);

                // 取得该控件的实例
                LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) tabline
                        .getLayoutParams();

                if (currentPage == 0 && arg0 == 0) { // 0->1移动(第一页到第二页)
                    ll.leftMargin = (int) (currentPage * tabLineLength + arg1
                            * tabLineLength);
                }
                else if (currentPage == 1 && arg0 == 0) { // 1->0移动（第二页到第一页）
                    ll.leftMargin = (int) (currentPage * tabLineLength - ((1 - arg1) * tabLineLength));
                }

                tabline.setLayoutParams(ll);

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }
    private void initTabLine(View view) {
        // 获取显示屏信息
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        // 得到显示屏宽度
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // 1/3屏幕宽度
        tabLineLength = metrics.widthPixels / 2;
        // 获取控件实例
        tabline = (ImageView) view.findViewById(R.id.tabline);
        // 控件参数
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        lp.width = tabLineLength;
        tabline.setLayoutParams(lp);
    }


}
