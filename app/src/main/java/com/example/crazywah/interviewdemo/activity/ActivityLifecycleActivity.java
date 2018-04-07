package com.example.crazywah.interviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.crazywah.interviewdemo.R;

public class ActivityLifecycleActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";

    /**
     * Activity刚创建时调用
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: executed");
        setContentView(R.layout.activity_lifecycle);

    }

    /**
     * 重新进入App时调用：如，Home键返回桌面再重新进入App
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: executed");
    }

    /**
     * 绘制Activity内容并置于前台，但是还无法与用户进行交互
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: executed");
    }

    /**
     * 读取上一次App Stop前存储的Bundle携带的数据
     * 只有当Activity因非正规原因重启时才会调用：程序崩毁、横竖屏转换等
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: executed");
    }

    /**
     * Activity绘制完成并且置于前台可与用户交互
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: executed");
    }

    /**
     * 一个瞬态，紧接着就会进入onStop()停止，此时如果重新进入到当前Activity可立即进入onResume（极端）
     * 需要注意的是只有当前Activity的onPause执行完成了下一个Activity的onResume方法才能被执行
     * */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: executed");
    }

    /**
     * 保存当前Activity的信息，用于下一次Activity的重启恢复
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: executed");
    }

    /**
     * 当前Activity离开前台，进入后台，不可交互
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: executed");
    }

    /**
     * Activity销毁时调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
