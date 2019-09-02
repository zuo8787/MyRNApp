package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

public class MyRNActivity2 extends ReactActivity {
//public class MyRNActivity2 extends BaseReactActivity {
    @Nullable
    @Override
    protected String getMainComponentName() {
        return "MyRNActivity2";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Nullable
            @Override
            protected Bundle getLaunchOptions() {
                MyApp.sBundle.putInt("entrance",2);
                return MyApp.sBundle;
            }
        };
    }

    /*@Override
    protected String getJSMainModulePath() {
        return "index.android";
    }

    @Override
    protected String getJSBundleFile() {
        return null;
    }

    @Override
    protected String getBundleAssetName() {
        return "index.android.bundle";
    }

    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage()
        );
    }

    @Override
    protected String getJsModuleName() {
        return "MyRNActivity2";
    }*/
}
