package com.example.crazywah.interviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.crazywah.interviewdemo.R;
import com.example.crazywah.interviewdemo.fragment.FragmentLifeCycyle;

public class FragmentTestActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        btn = findViewById(R.id.btn);

        FragmentLifeCycyle fragment = new FragmentLifeCycyle();

        //获取fragment管理器
        FragmentManager manager = getSupportFragmentManager();

        //开启事务
        FragmentTransaction transition =  manager.beginTransaction();

        transition.add(R.id.frame,fragment);
        transition.commit();

    }
}
