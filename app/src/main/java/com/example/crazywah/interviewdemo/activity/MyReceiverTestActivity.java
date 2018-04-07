package com.example.crazywah.interviewdemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.crazywah.interviewdemo.R;
import com.example.crazywah.interviewdemo.broadcast.MyBroadcastReceiver;
import com.example.crazywah.interviewdemo.util.ToastUtil;

public class MyReceiverTestActivity extends AppCompatActivity implements View.OnClickListener {

    private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("msg");
        ToastUtil.showShort("注册广播接收者");
        registerReceiver(receiver,intentFilter);

        btn = findViewById(R.id.send);

        btn.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        ToastUtil.showShort("注销广播接收者");
    }

    @Override
    public void onClick(View v) {
        sendBroadcast(new Intent("msg"));
    }
}
