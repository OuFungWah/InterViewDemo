package com.example.crazywah.interviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.crazywah.interviewdemo.R;
import com.example.crazywah.interviewdemo.util.ToastUtil;
import com.example.crazywah.interviewdemo.view.SwitchButtonV3;

public class SwitchButtonTestActivity extends AppCompatActivity implements SwitchButtonV3.OnStateChangeListener {

    private SwitchButtonV3 sbv3;
    private TextView v3Tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);

        sbv3 = findViewById(R.id.sbv3);
        v3Tv = findViewById(R.id.v3_tv);

        sbv3.setOnStateChangeListener(this);
        v3Tv.setText("SwitchButton.v3\n"+sbv3.getEnable());

        // TODO: 2018/4/14 留意一下，当多个View在同时重新绘制时出现卡顿

    }

    @Override
    public void onStateChange(boolean enable) {
        v3Tv.setText("SwitchButton.v3\n"+enable);
    }
}
