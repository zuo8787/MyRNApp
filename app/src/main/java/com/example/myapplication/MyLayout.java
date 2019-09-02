package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyLayout extends FrameLayout {

    public Button button1,button2,button3,button4,button5;
    public Context context;
    public View mView;
    public boolean flag = true;

    public MyLayout(@NonNull Context context) {
        super(context);
        this.context = context;
        //init();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.my_layout, this, true);
    }

    public MyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //init();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.my_layout, this, true);
    }

    public void init(){
        button1 = mView.findViewById(R.id.button1);
        button2 = mView.findViewById(R.id.button2);
        button3 = mView.findViewById(R.id.button3);
        button4 = mView.findViewById(R.id.button4);
        button5 = mView.findViewById(R.id.button5);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(1000);
                button1.setAnimation(rotateAnimation);
                if(flag){
                    flag = false;
                    MyAnimation1(button1,button2);
                    MyAnimation1(button1,button3);
                    MyAnimation1(button1,button4);
                    MyAnimation1(button1,button5);
                }else{
                    flag = true;
                    MyAnimation2(button1,button2);
                    MyAnimation2(button1,button3);
                    MyAnimation2(button1,button4);
                    MyAnimation2(button1,button5);
                }
            }
        });
    }

    public void MyAnimation1(Button button1,View view){
        Log.e("tag","button1.getX()="+button1.getX() +"button1.getY()="+ button1.getY()+"view.getX()="+view.getX()+"view.getY()="+view.getY());
        view.setVisibility(View.VISIBLE);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1000);

        TranslateAnimation translateAnimation = new TranslateAnimation(button1.getX()-view.getX(), 0,button1.getY()-view.getY(),0);
        translateAnimation.setDuration(1000);

        //RotateAnimation rotateAnimation  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //rotateAnimation.setDuration(1000);

        animationSet.addAnimation(translateAnimation);
        //animationSet.addAnimation(rotateAnimation);

        view.setAnimation(animationSet);

    }
    public void MyAnimation2(Button button1,View view){
        Log.e("tag","button1.getX()="+button1.getX() +"button1.getY()="+ button1.getY()+"view.getX()="+view.getX()+"view.getY()="+view.getY());
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1000);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, button1.getX()-view.getX(),0,button1.getY()-view.getY());
        translateAnimation.setDuration(1000);

        //RotateAnimation rotateAnimation  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ///rotateAnimation.setDuration(1000);

        animationSet.addAnimation(translateAnimation);
        //animationSet.addAnimation(rotateAnimation);

        view.setAnimation(animationSet);
        view.setVisibility(View.INVISIBLE);
    }
}
