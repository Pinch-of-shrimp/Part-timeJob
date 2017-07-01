package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * Created by Administrator on 2017/6/30.
 */

public class Fragment_Market_one extends Fragment implements OnWheelChangedListener, View.OnClickListener {
    private String[] mProvinceDatas;
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
    private Map<String,String> mProvinceName_Id = new HashMap<>();
    private Map<String,String> mCityName_Id = new HashMap<>();
    private Map<String,String> mAreaName_Id = new HashMap<>();
    private String mCurrentProviceName;
    private String mCurrentCityName;
    private String mCurrentAreaName ="";
    private String mJson;
    private WheelView mMProvince;
    private WheelView mCity;
    private WheelView mMArea;
    private Button mMButton;
    private Button btn_start;
    private TextView mAddressText;
    private TextView mAddressIdText;
    private LinearLayout mMLine;
    private PopupWindow mMPopupWindow;
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    private Map<String,Object> map;
    private int[] images={R.drawable.login_photo};
    private String[] names={"帅哥"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_market_one,container,false);
        mAddressText = (TextView) view.findViewById(R.id.address);
        mAddressIdText = (TextView)view.findViewById(R.id.address_id);
        btn_start= (Button) view.findViewById(R.id.btn_market_one);
        btn_start.setOnClickListener(this);
        lv=(ListView) view.findViewById(R.id.lv);
        list=new ArrayList<Map<String, Object>>();
        for(int i=0;i<names.length;i++){
            map=new HashMap<String, Object>();
            map.put("img",images[i]);
            map.put("name",names[i]);
            map.put("desc","大叔");
            list.add(map);
        }
        String[] from={"img","name","desc"};
        int[] to={R.id.pic_image,R.id.pic_name,R.id.pic_desc};
        adapter=new SimpleAdapter(getContext(),list,R.layout.list_market_one_layout,from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"点击的是索引"+position+",id"+id,Toast.LENGTH_SHORT).show();
            }
        });
        init();
        return view;
    }
    /*
    public void btnClick(View view){
        getSelect();
        showData();
    }*/
    private void showData() {
        //获取父布局
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main,null);
        //相对父布局的位置在底部弹出
        mMPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
    private void init(){
        View v = LayoutInflater.from(getContext()).inflate(R.layout.popwindow_layout,null);
        //设置弹窗的宽和高
        mMPopupWindow = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击外部弹窗消失
        mMPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mMPopupWindow.setOutsideTouchable(true);
        mMLine = (LinearLayout)v. findViewById(R.id.line);
        //省的控件
        mMProvince = (WheelView)v. findViewById(R.id.province);
        //市的控件
        mCity = (WheelView)v. findViewById(R.id.city);
        //区的控件
        mMArea = (WheelView)v. findViewById(R.id.area);

        mMButton = (Button)v. findViewById(R.id.btn);
        //点击确定按钮，把选择的地址返回，同事弹窗消失
        mMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose(v);
                getValue();
                //把当前所选地赋值给mAddressText
                mAddressText.setText(mCurrentProviceName+"--"+mCurrentCityName+"--"+mCurrentAreaName);
                mMPopupWindow.dismiss();
            }
        });


    }
    public void getSelect(){
        String json = initJsonData();
        parseJson(json);

        mMProvince.setViewAdapter(new ArrayWheelAdapter<String>(getContext(),mProvinceDatas));
        //添加change事件
        mMProvince.addChangingListener(this);
        //添加change事件
        mCity.addChangingListener(this);
        //添加change事件
        mMArea.addChangingListener(this);
        //同一时间可见的item个数
        mMProvince.setVisibleItems(6);
        mCity.setVisibleItems(6);
        mMArea.setVisibleItems(6);

        updateCities();

        updateAreas();

    }
    private void updateAreas(){
        int pCurrent = mCity.getCurrentItem();
        //当前市的名称
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mAreaDatasMap.get(mCurrentCityName);
        if(areas == null){
            areas = new String[]{""};
        }
        mMArea.setViewAdapter(new ArrayWheelAdapter<String>(getContext(),areas));
        mMArea.setCurrentItem(0);
        //得到区名字的初始值为第0个位置的值
        mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[0];
    }
    private void updateCities()
    {
        int pCurrent = mMProvince.getCurrentItem();
        //当前省的名称
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null)
        {
            cities = new String[] { "" };
        }
        mCity.setViewAdapter(new ArrayWheelAdapter<String>(getContext(), cities));
        mCity.setCurrentItem(0);
        updateAreas();

    }
    private String initJsonData(){
        try {
           InputStream is = getResources().getAssets().open("地区json.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            mJson = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mJson;
    }
    private void parseJson(String str){
        try {
            JSONArray jsonArray = new JSONArray(str);
            mProvinceDatas = new String[jsonArray.length()];
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonp = jsonArray.getJSONObject(i);
                String provinceName = jsonp.getString("region_name");
                String provinceId = jsonp.getString("region_id");
                mProvinceName_Id.put(provinceName,provinceId);
                mProvinceDatas[i] = provinceName;
                JSONArray jsonc = jsonp.getJSONArray("sub");
                String [] citiesData = new String[jsonc.length()];
                for(int j = 0;j<jsonc.length();j++){
                    JSONObject jsoncity = jsonc.getJSONObject(j);
                    String cityName = jsoncity.getString("region_name");
                    String cityId = jsoncity.getString("region_id");
                    mCityName_Id.put(cityName,cityId);
                    citiesData[j] = cityName;
                    JSONArray jsona = jsoncity.getJSONArray("sub");
                    String [] areaData = new String[jsona.length()];
                    for(int k = 0;k<jsona.length();k++){
                        String areaName = jsona.getJSONObject(k).getString("region_name");
                        String areaId = jsona.getJSONObject(k).getString("region_id");
                        mAreaName_Id.put(areaName,areaId);
                        areaData[k] = areaName;
                    }
                    mAreaDatasMap.put(cityName,areaData);
                }
                mCitisDatasMap.put(provinceName,citiesData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if(wheel == mMProvince){
            updateCities();
        }else if(wheel == mCity){
            updateAreas();
        }else if(wheel == mMArea){
            mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
        }

    }
    private void getValue(){
        //获取省的ID
        Object provinceId = new Object();
        provinceId = mProvinceName_Id.get(mCurrentProviceName);
        //获取市的ID
        Object cityId = new Object();
        cityId = mCityName_Id.get(mCurrentCityName);
        //获取区的ID
        Object areaId = new Object();
        areaId = mAreaName_Id.get(mCurrentAreaName);
        //将ID赋值给TextView（传服务器）
        mAddressIdText.setText(provinceId+"----"+cityId+"----"+areaId);
    }
    public void showChoose(View view)
    {
        Toast.makeText(getContext(), mCurrentProviceName + mCurrentCityName + mCurrentAreaName, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_market_one:
                getSelect();
                showData();
                break;
            default:
                break;
        }
    }
}
