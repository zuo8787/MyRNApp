package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.example.myretrofit.MyModle;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView = null;
    private RecyclerViewAdapter mRecyclerViewAdapter = null;
    public ArrayList<MyModle> myModleArrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        initData();
        findView();

    }

    public void findView(){
        mRecyclerView = findViewById(R.id.recycler_view);
        //使用瀑布流布局,第一个参数 spanCount 列数,第二个参数 orentation 排列方向
//        StaggeredGridLayoutManager recyclerViewLayoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //线性布局Manager
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerViewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //网络布局Manager
//        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, 3);
        //给recyclerView设置LayoutManager
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //mRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void initData(){
        myModleArrayList = new ArrayList<MyModle>();
        for (int i=0;i < 25 ; i++){
            myModleArrayList.add(new MyModle("第"+i,"第"+i));
        }
        Log.e("tag","列表尺寸："+myModleArrayList.size());
        mRecyclerViewAdapter = new RecyclerViewAdapter(this,myModleArrayList);

    }


    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SYSTEM_ALERT_WINDOW,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d("tag", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                            Log.d("tag", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，而且选中『不再询问』
                            Log.d("tag", permission.name + " is denied.");
                        }
                    }
                });
    }
}
