package com.example.wkg.part_timejob;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/2.
 */

public class Activity_MainMap extends AppCompatActivity implements LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener {
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    boolean flag=true;
    private MapView mainMAP;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        mainMAP= (MapView)findViewById(R.id.main_maps);
        mainMAP.onCreate(savedInstanceState);
        if(myLocationStyle==null)
        {
            myLocationStyle=new MyLocationStyle();

        }

        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        if(aMap==null)
        {
            aMap=mainMAP.getMap();
        }
        aMap.setLocationSource(this);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
    }
    public void getLating(String local,String city)
    {
        PoiSearch.Query query=new PoiSearch.Query(local,"",city);
        query.setPageNum(1);
        query.setPageSize(10);
        PoiSearch poiSearch=new PoiSearch(this,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainMAP.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainMAP.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainMAP.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainMAP.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener=onLocationChangedListener;
        if(mlocationClient==null)
        {
            mlocationClient=new AMapLocationClient(this);
            mLocationOption=new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null&&aMapLocation != null) {
            if (aMapLocation != null
                    &&aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                if(flag) {
                    loaddatafromdataset(aMapLocation.getDistrict(),aMapLocation.getCity());
                    //loaddatafromdataset("重庆", "沙坪坝");
                    flag=false;
                }
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    private void loaddatafromdataset(String city ,String province) {
        Retrofit retorfit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface_infor requestInterface=retorfit.create(RequestInterface_infor.class);
        final ServerRequest request=new ServerRequest();
        request.setCity(city);
        request.setProvince(province);
        request.setOperation(Constants.OPERATIONNEARJOB);
        Call<ServerResponse>responseCall=requestInterface.operation(request);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp=response.body();
                if(resp!=null)
                {
                    ArrayList<Job>arrayList=resp.getNearbyJob();
                    if(arrayList!=null) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            getLating(arrayList.get(i).getCity(), arrayList.get(i).getProvince());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getBaseContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        List<PoiItem>list=poiResult.getPois();
        setmark(list.get(0).getLatLonPoint().getLatitude(),list.get(0).getLatLonPoint().getLongitude(),list.get(0).getCityName());
    }

    private void setmark(double latitude, double longitude,String city) {
        LatLng latLng=new LatLng(latitude,longitude);
        Marker marker=aMap.addMarker(new MarkerOptions().position(latLng).title(city).snippet("DefaultMarker"));
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
