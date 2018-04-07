package com.example.crazywah.interviewdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.crazywah.interviewdemo.util.ToastUtil;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: executed");
        ToastUtil.showShort("收到广播信息："+intent);
    }
}
