package com.example.myapplication;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MyApp extends Application implements ReactApplication {
//public class MyApp extends Application {
    public static Bundle sBundle = new Bundle();
    public static ReactInstanceManager mReactInstanceManager;
    private ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {

        @Override
        public boolean getUseDeveloperSupport() {
            return true;
        }

        @Override
        protected String getJSMainModuleName() {
            return "index.android";
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }};

        @Override
        public ReactNativeHost getReactNativeHost() {
            return mReactNativeHost;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            SoLoader.init(this,false);
            mReactInstanceManager = ReactInstanceManager.builder()

                    .setApplication(this)

                    //.setCurrentActivity(this)//可以不需要此代码

                    .setBundleAssetName("index.android.bundle")

                    .setJSMainModulePath("index.android")//xxx.js
                    .addPackage(new MainReactPackage())

                    .setUseDeveloperSupport(true)

                    .setInitialLifecycleState(LifecycleState.RESUMED)

                    .build();
        }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d("tag", "onTerminate");
        super.onTerminate();
    }

}
