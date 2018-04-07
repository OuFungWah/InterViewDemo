package com.example.crazywah.interviewdemo.application;

import android.app.Application;

import com.example.crazywah.interviewdemo.util.ToastUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
    }
}
