package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.shell.MainReactPackage;

import java.io.File;
import java.util.List;

public abstract class BaseReactActivity extends AppCompatActivity
        implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {

    private static final String TAG = "BaseReactActivity";
    private static final String JS_BUNDLE_LOCAL_FILE = "index.android.bundle";
    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;
    @Nullable
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    @Nullable
    private Callback mPermissionsCallback;
    @Nullable
    private PermissionListener mPermissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactRootView = new ReactRootView(this);
        initReactRootView();
        setContentView(mReactRootView);
    }

    protected void initReactRootView() {

        ReactInstanceManagerBuilder builder = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setJSMainModulePath(getJSMainModulePath())
                //.addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED);
        String jsBundleFile = getJSBundleFile();
        File file = null;
        if (!TextUtils.isEmpty(jsBundleFile)){
            file = new File(jsBundleFile);
        }
        if (file!=null && file.exists()){
            builder.setJSBundleFile(getJSBundleFile());
            Log.i(TAG, "load bundle from local cache");
        } else {
            String bundleAssetName = getBundleAssetName();
            builder.setBundleAssetName(TextUtils.isEmpty(bundleAssetName) ? JS_BUNDLE_LOCAL_FILE : bundleAssetName);
            Log.i(TAG, "load bundle from asset");
        }
        if (getPackages() != null){
            builder.addPackages(getPackages());
        }
        mReactInstanceManager = builder.build();
        mReactRootView.startReactApplication(mReactInstanceManager,getJsModuleName(),null);
        mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();

    }

    abstract protected String getJSMainModulePath();

    /**
     *读取bundle文件的路径，返回null时，从assets下读取
     *
     * @return
     */
    abstract protected String getJSBundleFile();

    /**
     * assets 中自带的 bundle名称
     *
     * @return
     */
    abstract protected String getBundleAssetName();

    /**
     * 自定义模块集
     * @return
     */
    abstract protected List<ReactPackage> getPackages();

    /**
     * 入口文件注册名
     * @return
     */
    abstract protected String getJsModuleName();

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onHostResume(this,this);
        }
        if (mPermissionsCallback != null) {
            mPermissionsCallback.invoke();
            mPermissionsCallback = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onHostDestroy(this);
        }

        ReactNativePreLoader.deatchView(getJsModuleName());
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager!=null){
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager!=null){
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mReactInstanceManager!=null) {
            mReactInstanceManager.onActivityResult(this,requestCode,resultCode,data);
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener){
        mPermissionListener = listener;
        requestPermissions(permissions,requestCode);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        mPermissionsCallback = new Callback() {
            @Override
            public void invoke(Object... args) {
                if (mPermissionListener != null && mPermissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
                    mPermissionListener = null;
                }
            }
        };
    }
}
