package com.example.crazywah.interviewdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.crazywah.interviewdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout menuLl;

    private String[] titles = new String[]{
            "Activity生命周期",
            "Service生命周期",
            "Fragment生命周期",
            "BroadCast测试",
            "自定义LoadingView",
            "HandlerThread测试"};

    private Class[] targetActivities = new Class[]{
            ActivityLifecycleActivity.class,
            ServiceLifeCycleActivity.class,
            FragmentTestActivity.class,
            MyReceiverTestActivity.class,
            LoadingViewActivity.class,
            HandlerTestActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuLl = findViewById(R.id.menu_ll);

        for (int i = 0;i< titles.length;i++){
            Button button = new Button(this);
            button.setTag(i);
            button.setText(titles[i]);
            button.setOnClickListener(this);
            menuLl.addView(button);
        }

    }

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        startActivity(new Intent(this,targetActivities[position]));
    }
}
