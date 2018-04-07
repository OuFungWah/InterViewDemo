package com.example.crazywah.interviewdemo.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 彩虹色的LoadingView
 * <p>
 * Created by 区枫华 on 2017/3/18.
 */

public class LoadingView extends View {

    /**
     * 刷新时间
     */
    private final int REFRASHTIME = 10;
    /**
     * 加速度
     */
    private final double ACELERATION = 0.3;
    /**
     * 初始间隔
     */
    private double OFFSET = 0.8;
    /**
     * 是否是第一次开始的
     */
    boolean start = true;
    int radius;

    /**
     * 画笔组
     */
    private static Paint paintGroup[] = {new Paint(), new Paint(), new Paint(), new Paint(), new Paint(), new Paint()};
    /**
     * 画笔颜色组
     */
    private static int paintARGB[][] = {{255, 255, 168, 1}, {255, 255, 244, 1}, {255, 203, 255, 1}, {255, 89, 222, 157}, {255, 97, 157, 255}, {255, 209, 128, 254}};
    /**
     * 角度组
     */
    private double angleGroup[] = {0, 0, 0, 0, 0, 0};
    /**
     * 是否回到原点标记组
     */
    private boolean zeroGroup[] = {true, true, true, true, true, true};
    /**
     * 画笔对应坐标xy组
     */
    private int xyGroup[][] = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    /**
     * 各画笔原点前后两次位置角度差距组
     */
    private double offsetGroup[] = {OFFSET, OFFSET, OFFSET, OFFSET, OFFSET, OFFSET};
    /**
     * 运动轨迹的圆心x
     */
    int x;
    /**
     * 运动轨迹的圆心y
     */
    int y;

    //在类加载的时候进行画笔初始化
    static {
        for (int i = 0; i < paintGroup.length; i++) {
            //设置画笔颜色
            paintGroup[i].setARGB(paintARGB[i][0], paintARGB[i][1], paintARGB[i][2], paintARGB[i][3]);
            //抗锯齿化
            paintGroup[i].setAntiAlias(true);
            //设置画笔形状
            paintGroup[i].setStrokeCap(Paint.Cap.ROUND);
        }
    }

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //计算中心位置
        x = getWidth() / 2;
        y = getHeight() / 2;

        //取最小的一个半径作为运动轨迹的半径
        if (x >= y)
            radius = y / 2;
        else
            radius = x / 2;

        //第一次运行时的初始化
        if (start) {
            for (int i = 0; i < xyGroup.length; i++) {
                xyGroup[i][0] = x - radius;
                xyGroup[i][1] = y;
                paintGroup[i].setStrokeWidth(radius / 3);
            }
            start = false;
        }

        //画出相应的点
        for (int i = paintGroup.length - 1; i >= 0; i--) {
            canvas.drawPoint(xyGroup[i][0], xyGroup[i][1], paintGroup[i]);
        }

        //计算下一次点的位置
        for (int i = 0; i < paintGroup.length; i++) {
            if (zeroGroup[i]) {
                if (i == 0 || angleGroup[i - 1] >= 15) {
                    xyGroup[i][0] = x - (int) (radius * Math.cos(Math.toRadians(angleGroup[i])));
                    xyGroup[i][1] = y - (int) (radius * Math.sin(Math.toRadians(angleGroup[i])));

                    angleGroup[i] += offsetGroup[i];
                    if (angleGroup[i] <= 180)
                        offsetGroup[i] += ACELERATION;
                    else
                        offsetGroup[i] -= ACELERATION;
                    if (angleGroup[i] >= 359 && angleGroup[angleGroup.length - 1] <= 359) {
                        angleGroup[i] = 360;
                        offsetGroup[i] = 360;
                        zeroGroup[i] = false;
                    }
                    if (angleGroup[i] >= 359 && angleGroup[angleGroup.length - 1] > 359) {
                        angleGroup = new double[]{0, 0, 0, 0, 0, 0};
                        offsetGroup = new double[]{OFFSET, OFFSET, OFFSET, OFFSET, OFFSET, OFFSET};
                        zeroGroup = new boolean[]{true, true, true, true, true, true};
                    }
                }
            }

        }

        //在延迟时间后重画
        postInvalidateDelayed(REFRASHTIME);

    }
}