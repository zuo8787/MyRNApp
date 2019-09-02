package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;


/*public class MyRNActivity extends ReactActivity{
    @Nullable
    @Override
    protected String getMainComponentName() {
        return "MyRNActivity";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Nullable
            @Override
            protected Bundle getLaunchOptions() {
                MyApp.sBundle.putInt("entrance",1);
                return MyApp.sBundle;
            }
        };
    }
}*/


public class MyRNActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView,mReactRootView2;

    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rn);

        //mReactRootView = new ReactRootView(this);
        mReactRootView = findViewById(R.id.test_js);
        mReactInstanceManager = ReactInstanceManager.builder()

                .setApplication(getApplication())

                .setCurrentActivity(this)//可以不需要此代码

                .setBundleAssetName("index.android.bundle")

                .setJSMainModulePath("index.android")//xxx.js
                .addPackage(new MainReactPackage())

                .setUseDeveloperSupport(true)

                .setInitialLifecycleState(LifecycleState.RESUMED)

                .build();

        // 注意这里的MyReactNativeApp必须对应“index.android.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "MyRNActivity", null);

        mReactRootView2= findViewById(R.id.test_js2);
        mReactRootView2.startReactApplication(MyApp.mReactInstanceManager, "MyRNActivity", null);
        //setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        if(mReactInstanceManager!=null){
            mReactInstanceManager.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(mReactInstanceManager!=null){
            mReactInstanceManager.onHostResume(this,this);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();

        if(mReactInstanceManager!=null){
            mReactInstanceManager.onHostPause(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mReactInstanceManager!=null){
            mReactInstanceManager.onHostDestroy(this);
        }
    }

}
