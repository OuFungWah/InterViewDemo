package com.example.crazywah.interviewdemo.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.crazywah.interviewdemo.util.ToastUtil;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.d(TAG, "onReceive: executed");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showShort("收到广播信息："+intent);
            }
        });

    }
}
