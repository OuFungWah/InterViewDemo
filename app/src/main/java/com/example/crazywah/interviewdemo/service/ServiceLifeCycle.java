package com.example.crazywah.interviewdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.crazywah.interviewdemo.util.ToastUtil;

public class ServiceLifeCycle extends Service {

    private static final String TAG="ServiceLifeCycle";

    /**
     * 创建时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.showShort("onCreate: executed");
        Log.d(TAG, "onCreate: executed");
    }

    /**
     * startService() 时调用，每次start都调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ToastUtil.showShort("onStartCommand: executed");
        Log.d(TAG, "onStartCommand: executed");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * bindService() 绑定时调用，每一个Connection只能绑定一次
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ToastUtil.showShort("onBind: executed");
        Log.d(TAG, "onBind: executed");
        return new MyBinder(this);
    }

    /**
     * 解除绑定时调用
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        ToastUtil.showShort("onUnbind: executed");
        Log.d(TAG, "onUnbind: executed");
        return super.onUnbind(intent);
    }

    /**
     * 用于携带Service对象引用的Binder类，用于多进程或多线程传输
     */
    public class MyBinder extends Binder{

        private ServiceLifeCycle instance;

        MyBinder(ServiceLifeCycle serviceLifeCycle){
            instance = serviceLifeCycle;
        }

        public ServiceLifeCycle getInstance() {
            return instance;
        }
    }

    /**
     * Service 对象销毁时调用：当前Service对象执行完了StopService和每个链接都已经unbind()的时候就会
     * 销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastUtil.showShort("onDestroy: executed");
        Log.d(TAG, "onDestroy: executed");
    }
}
