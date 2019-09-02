package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myretrofit.MyModle;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHold> {

    public Context mContext;
    public ArrayList<MyModle> mArrayList;


    public RecyclerViewAdapter(Context mContext, ArrayList<MyModle> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @NonNull
    @Override
    public MyHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textlayout,parent,false);
        MyHold myHold = new MyHold(view);
        return myHold;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHold holder, int position) {
        MyModle myModle = mArrayList.get(position);
        holder.mTextView.setText(mContext.getString(R.string.testViewName)+myModle.getTestName()+" 内容："+myModle.getTestData());

        //Glide.with(mContext).load("https://avatar.csdn.net/1/2/5/3_t_tzz.jpg").into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, SecondActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"点击了"+holder.mTextView.getText(),Toast.LENGTH_LONG).show();
                Log.e("tag","点击了textView"+holder.mTextView.getText());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"点击了item"+position,Toast.LENGTH_LONG).show();
                Log.e("tag","点击了item"+position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyHold extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView imageView;

        public MyHold(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_recycle_item);
            imageView = itemView.findViewById(R.id.img_recycle_item);
        }
    }
}
