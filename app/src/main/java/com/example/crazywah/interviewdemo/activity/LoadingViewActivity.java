package com.example.crazywah.interviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.crazywah.interviewdemo.util.LoadingView;

public class LoadingViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadingView loadingView = new LoadingView(this);
        setContentView(loadingView);
    }
}
