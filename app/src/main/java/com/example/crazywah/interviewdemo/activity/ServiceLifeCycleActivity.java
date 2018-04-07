package com.example.crazywah.interviewdemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.crazywah.interviewdemo.R;
import com.example.crazywah.interviewdemo.service.ServiceLifeCycle;

public class ServiceLifeCycleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startBtn;
    private Button bindBtn;
    private Button unBindBtn;
    private Button stopBtn;

    private ServiceLifeCycle serviceLifeCycle;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取Service的对象
            serviceLifeCycle = ((ServiceLifeCycle.MyBinder)service).getInstance();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //解除链接以后
            serviceLifeCycle = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_lifecycle);

        startBtn = findViewById(R.id.start_btn);
        bindBtn = findViewById(R.id.bind_btn);
        unBindBtn = findViewById(R.id.unbind_btn);
        stopBtn = findViewById(R.id.stop_btn);

        startBtn.setOnClickListener(this);
        bindBtn.setOnClickListener(this);
        unBindBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.start_btn:
                intent = new Intent(this,ServiceLifeCycle.class);
                startService(intent);
                break;
            case R.id.bind_btn:
                intent = new Intent(this,ServiceLifeCycle.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_btn:
                unbindService(connection);
                break;
            case R.id.stop_btn:
                intent = new Intent(this,ServiceLifeCycle.class);
                stopService(intent);
                break;
            default:
                break;
        }
    }
}
