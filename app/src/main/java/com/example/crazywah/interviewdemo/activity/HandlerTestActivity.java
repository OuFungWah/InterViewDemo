package com.example.crazywah.interviewdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.crazywah.interviewdemo.R;
import com.example.crazywah.interviewdemo.util.ToastUtil;

public class HandlerTestActivity extends AppCompatActivity implements View.OnClickListener {

    private HandlerThread handlerThread;
    private Handler handler;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handlerThread = new HandlerThread("HandlerThreadTest");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                ToastUtil.showShort("这是" + (msg.what + 1) + msg.obj);
                return false;
            }
        });

        btn1 = findViewById(R.id.send_msg1_btn);
        btn2 = findViewById(R.id.send_msg2_btn);
        btn3 = findViewById(R.id.send_msg3_btn);
        btn4 = findViewById(R.id.send_msg4_btn);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Message message;
        switch (v.getId()) {
            case R.id.send_msg1_btn:
                message = Message.obtain(handler, 0);
                handler.sendMessage(message);
                break;
            case R.id.send_msg2_btn:
                handler.sendEmptyMessage(1);
                break;
            case R.id.send_msg3_btn:
                handler.sendEmptyMessageDelayed(2, 1000);
                break;
            case R.id.send_msg4_btn:
                message = Message.obtain(handler, 3, "haha");
                handler.sendMessageDelayed(message, 1000);
                break;
            default:
                break;
        }
    }
}
