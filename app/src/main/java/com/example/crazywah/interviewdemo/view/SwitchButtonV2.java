package com.example.crazywah.interviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class SwitchButtonV2 extends View implements View.OnClickListener{
    private static final String TAG = "SwitchButtonV2";

    private int proportion = 24;

    private long runningTime = 100;

    private Paint paint;

    /**
     * 默认为否认
     */
    private boolean enable = false;

    /**
     * 线条半径
     */
    private float lineRadiu = 0.0f;
    /**
     * 圆圈半径
     */
    private float cycleRadiu = 0.0f;

    /**
     * 修正后的高
     */
    private float height = 0.0f;

    /**
     * 修正后的宽
     */
    private float width = 0.0f;

    /**
     * X坐标系偏移值
     */
    private float xOffset = 0.0f;

    /**
     * Y坐标系偏移值
     */
    private float yOffset = 0.0f;

    /**
     * 否定默认灰色
     */
    private int[] firstColor = new int[]{255, 50, 50, 50};
    /**
     * 肯定默认红色
     */
    private int[] secondColor = new int[]{255, 255, 0, 0};
    /**
     * 当前颜色
     */
    private int[] currentColor = Arrays.copyOf(firstColor, firstColor.length);

    /**
     * 进度
     * Disable = 0
     * Enable = 100
     */
    private float progress = 0;

    public SwitchButtonV2(Context context) {
        super(context);
        init();
    }

    public SwitchButtonV2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchButtonV2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.argb(firstColor[0],firstColor[1],firstColor[2],firstColor[3]));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        setOnClickListener(this);
    }

    /**
     * 在View创建之初调用
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: executed");
        float measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        float measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (measureHeight < (measureWidth / 2)) {
            height = measureHeight;
            width = measureHeight * 2;
        } else {
            height = measureWidth / 2;
            width = measureWidth;
        }

        //计算圈圈半径
        cycleRadiu = width / 4;
        //计算横线半径
        lineRadiu = height / 20;
        //计算坐标轴的横向偏移值
        xOffset = measureWidth / 2 - 2 * cycleRadiu;
        //计算坐标轴的纵向偏移值
        yOffset = measureHeight / 2;

    }

    /**
     * 在View创建之初调用
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: executed");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //修正坐标系
        canvas.translate(xOffset,yOffset);

        //计算当前颜色的RGB
        currentColor[1] = firstColor[1]-(int)((progress/proportion)*(firstColor[1]-secondColor[1]));
        currentColor[2] = firstColor[2]-(int)((progress/proportion)*(firstColor[2]-secondColor[2]));
        currentColor[3] = firstColor[3]-(int)((progress/proportion)*(firstColor[3]-secondColor[3]));

        paint.setColor(Color.argb(currentColor[0],currentColor[1],currentColor[2],currentColor[3]));

        //绘制左右两个圆
        paint.setStrokeWidth(1);
        canvas.drawCircle(lineRadiu,0,lineRadiu,paint);
        canvas.drawCircle(width-lineRadiu,0,lineRadiu,paint);

        //绘制横线
        paint.setStrokeWidth(lineRadiu*2);
        canvas.drawLine(lineRadiu,0,width-lineRadiu,0,paint);

        //绘制圆
        paint.setStrokeWidth(1);
        canvas.drawCircle(cycleRadiu+((progress/proportion)*(2*cycleRadiu)),0,cycleRadiu,paint);

        //计算当前进度
        if(!enable&&progress>0){
            if(progress-1<0){
                progress = 0;
            } else {
                progress--;
            }
            postInvalidateDelayed(runningTime/proportion);
        }

        if (enable&&progress<proportion){
            if(progress+1>proportion){
                progress = proportion;
            }else{
                progress++;
            }
            postInvalidateDelayed(runningTime/proportion);
        }

    }

    @Override
    public void onClick(View v) {
        enable = !enable;
        postInvalidate();
    }

    /**
     * 设置否定颜色
     * @param a
     * @param r
     * @param g
     * @param b
     */
    public void setFirstColor(int a,int r,int g,int b){
        firstColor[0] = a;
        firstColor[1] = r;
        firstColor[2] = g;
        firstColor[3] = b;
        postInvalidate();
    }

    /**
     * 设置肯定颜色的ARGB
     * @param a
     * @param r
     * @param g
     * @param b
     */
    public void setSecondColor(int a,int r,int g,int b){
        secondColor[0] = a;
        secondColor[1] = r;
        secondColor[2] = g;
        secondColor[3] = b;
        postInvalidate();
    }

    public void setEnable(boolean enable){
        this.enable = enable;
    }

    public boolean getEnable(){
        return this.enable;
    }
}
