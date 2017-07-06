package com.example.wkg.part_timejob;

import android.*;
import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wkg.part_timejob.Splash.CircleImageView;
import com.example.wkg.part_timejob.Splash.Perinfor;
import com.example.wkg.part_timejob.Splash.PerinforAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.wkg.part_timejob.R.id.btn_open_camera;

/**
 * Created by WKG on 2017/6/24.
 */

public class Fragment_person_information extends Fragment implements OnClickListener{
    private TextView tv_register;
    private ListView lv_group_one;
    private SharedPreferences pref;
    private ListView lv_group_two;
    private CircleImageView civ_photo;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    @Nullable
    /*
    给listview添加内容first
     */
    private String[] group_one={"我的兼职","我的简历","我的收藏"};
    private String[] group_two={"招聘发布","联系客服","投诉和反馈"};
    //private ArrayList<String>data1;
    //private ArrayList<String>data2;
    //个人信息页
    private List<Perinfor> perinfors_one=new ArrayList<>();
    private List<Perinfor> perinfors_two=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.person_infor,container,false);
        tv_register= (TextView) view.findViewById(R.id.tv_register);
        lv_group_one= (ListView) view.findViewById(R.id.lv_group_one);
        lv_group_two= (ListView) view.findViewById(R.id.lv_group_two);
        civ_photo= (CircleImageView) view.findViewById(R.id.civ_chosephoto);
        civ_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow(v);
            }
        });
        pref=getActivity().getPreferences(0);
        tv_register.setOnClickListener(this);
        if(pref.getBoolean(Constants.IS_LOGGED_IN,true)) {
            tv_register.setText("welcome:" + pref.getString(Constants.EMAIL, ""));
            
        }
        initperinfor();//初始化个人信息数据
        //数据实现适配 第一个listview
        PerinforAdapter adapter_one=new PerinforAdapter(this.getActivity(),R.layout.lv_personal_layout,perinfors_one);
        //第二个listview
        PerinforAdapter adapter_two=new PerinforAdapter(this.getActivity(),R.layout.lv_personal_layout,perinfors_two);
        lv_group_one.setAdapter(adapter_one);
        lv_group_two.setAdapter(adapter_two);
        return view;
    }
    public void initperinfor()
    {
        /*
        第一条数据
         */
        Perinfor lvone_1=new Perinfor(group_one[0].toString(),R.drawable.myjob2);
        perinfors_one.add(lvone_1);
        Perinfor lvone_2=new Perinfor(group_one[1].toString(),R.drawable.myintruduce2);
        perinfors_one.add(lvone_2);
        Perinfor lvone_3=new Perinfor(group_one[2].toString(),R.drawable.mycollect2);
        perinfors_one.add(lvone_3);
        /*
        第二条数据
         */

        Perinfor lvtwo_1=new Perinfor(group_two[0].toString(),R.drawable.pushjob2);
        perinfors_two.add(lvtwo_1);
        Perinfor lvtwo_2=new Perinfor(group_two[1].toString(),R.drawable.connecttokefu2);
        perinfors_two.add(lvtwo_2);
        Perinfor lvtwo_3=new Perinfor(group_two[2].toString(),R.drawable.feedback2);
        perinfors_two.add(lvtwo_3);
//        group1=new
//        data1=new ArrayList<>();
//        data2=new ArrayList<>();
//        for(int i=0;i<group_one.length;i++)
//            data1.add(group_one[i]);
//        for (int i=0;i<group_two.length;i++)
//            data2.add(group_two[i]);
//        lv_group_one.setAdapter(new ListAdapter(data1,getContext()));
//        lv_group_two.setAdapter(new ListAdapter(data2,getContext()));
        lv_group_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent intent3=new Intent(getActivity(),Activity_other_things.class);
                        intent3.putExtra("type","myjob");
                        startActivity(intent3);
                        break;
                    case 1:
                        Intent intent=new Intent(getActivity(),Activity_other_things.class);
                        intent.putExtra("type","myintruduce");
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1=new Intent(getActivity(),Activity_other_things.class);
                        intent1.putExtra("type","myfavorite");
                        startActivity(intent1);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });
       lv_group_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent intent3=new Intent(getActivity(),Activity_other_things.class);
                        intent3.putExtra("type","push_information");
                        startActivity(intent3);
                        break;
                    case 1:
                        Intent intent2 = new Intent(Intent.ACTION_CALL,Uri.parse("tel:10000"));
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent=new Intent(getActivity(),Activity_other_things.class);
                        intent.putExtra("type","feedback");
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
           }
        });

    }
    PopupWindow popupWindow;
    private void showPopWindow(View view)
    {
        View contentview=LayoutInflater.from(getContext()).inflate(R.layout.popwindow_chosephoto,null);
        Button btn_opencream= (Button) contentview.findViewById(R.id.btn_open_camera);
        Button btn_openblum= (Button) contentview.findViewById(R.id.btn_chose_cancel);
        Button btn_cancel= (Button) contentview.findViewById(R.id.btn_choose_img);
        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        btn_opencream.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                autoObtainCameraPermission();
            }
        });
        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                autoObtainStoragePermission();
            }
        });
        popupWindow=new PopupWindow(contentview, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.showAsDropDown(view);
    }
    public void autoObtainCameraPermission()
    {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.CAMERA)) {
                ToastUtils.showShort(getContext(), "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    imageUri = FileProvider.getUriForFile(getContext(), "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                PhotoUtils.takePicture(getActivity(), imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.showShort(getContext(), "设备没有SD卡！");
            }
        }
    }

    private boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE: {//调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(getContext(), "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(getActivity(), imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.showShort(getContext(), "设备没有SD卡！");
                    }
                } else {

                    ToastUtils.showShort(getContext(), "请允许打开相机！！");
                }
                break;


            }
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(getActivity(), CODE_GALLERY_REQUEST);
                } else {

                    ToastUtils.showShort(getContext(), "请允许打操作SDCard！！");
                }
                break;
        }
    }
    private static final int output_X = 480;
    private static final int output_Y = 480;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(getActivity(), imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(getContext(), data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(getContext(), "com.zz.fileprovider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(getActivity(), newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.showShort(getContext(), "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getContext());
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
            }
        }
    }

    private void showImages(Bitmap bitmap) {
        civ_photo.setImageBitmap(bitmap);
    }
    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(getActivity(), CODE_GALLERY_REQUEST);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_register:
              //  Toast.makeText(getContext(),"this is register",Toast.LENGTH_SHORT).show();
                //need to juege he state if not login jump to the register otherwise stay
                Boolean buff=pref.getBoolean(Constants.IS_LOGGED_IN,false);
                if(pref.getBoolean(Constants.IS_LOGGED_IN,false)==false)
                    startActivity(new Intent(getActivity(),Activity_LoginFunction.class));
                if (pref.getBoolean(Constants.IS_LOGGED_IN,true))
                {
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN,false);
                    editor.putString(Constants.EMAIL,"");
                    editor.putString(Constants.NAME,"");
                    editor.putString(Constants.UNIQUE_ID,"");
                    editor.apply();
                    tv_register.setText("登录/注册");
                }
                break;
            default:
                break;
        }

    }
}
