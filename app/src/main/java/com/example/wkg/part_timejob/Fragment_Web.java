package com.example.wkg.part_timejob;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/6/30.
 */

public class Fragment_Web extends Fragment {
    private WebView wv;
    private String url="https://www.baidu.com/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_web,container,false);
        wv=(WebView) view.findViewById(R.id.wv);
        showPage(url);
        return view;
    }
    protected void showPage(String info){
        //显示页面内容
        wv.loadUrl(info);
        //设置使用本地的客户端
        wv.setWebViewClient(new WebViewClient());
    }
    //点击back按钮实现返回上一级页面，并且如果没有上一级页面的时候，才进行退出
    //重写点击按钮
    public void onBackPressed(){
        //   super.onBackPressed();
        if(wv.canGoBack()){
            wv.goBack();
        }
        else{
            onBackPressed();
        }
    }
}
