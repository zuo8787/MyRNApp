package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.myretrofit.HttpEngine;
import com.example.myretrofit.movieTopReq;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    public Button textView,bottom_button,bottom_button2;
    public float tv_x,tv_y,mv_x,mv_y;
    public MyLayout myLayout;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        context = this;
        myLayout = findViewById(R.id.mylayout);
        textView = findViewById(R.id.tv_secend);
        bottom_button = findViewById(R.id.bottom_button);

        bottom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MyRNActivity.class);
                context.startActivity(intent);
            }
        });

        bottom_button2 = findViewById(R.id.bottom_button2);

        bottom_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MyRNActivity2.class);
                context.startActivity(intent);
            }
        });

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tv_x = view.getX();
                        tv_y = view.getY();
                        mv_x = motionEvent.getRawX();
                        mv_y = motionEvent.getRawY();
                    case MotionEvent.ACTION_MOVE:
                        Log.e("tag","x="+motionEvent.getRawX()+"y="+motionEvent.getRawY());
                        view.layout((int)(tv_x-mv_x+motionEvent.getRawX()),
                                (int)(tv_y-mv_y+motionEvent.getRawY()),
                                (int)(tv_x-mv_x+motionEvent.getRawX()+view.getWidth()),
                                (int)(tv_y-mv_y+motionEvent.getRawY()+view.getHeight()));
                        //view.scrollTo((int)motionEvent.getRawX(),(int)motionEvent.getRawY());
                    case MotionEvent.ACTION_UP:
                        default:

                }
                return false;
            }
        });

        initData();
        initTestRxjava();
    }

    private void initTestRxjava() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(12);
                Log.e("tag","subscribe");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        //调用封装好的retrofit请求方法
        HttpEngine.getDuoBanTop("上海", "json", 1,"12fb7bb3196a7650e9552d392139e609",new Observer<movieTopReq>() {

            @Override
            public void onError(Throwable e) {
                //失败
                Log.i("retrofit==111=", "请求错误："+e.getMessage());
            }

            @Override
            public void onComplete() {
                //完成
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(movieTopReq movieTopReq) {
                //成功
                Log.i("retrofit==222=", movieTopReq.toString());
                //textView.setText(movieTopReq.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myLayout.init();
    }
}
