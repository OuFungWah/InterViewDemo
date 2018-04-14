package com.example.crazywah.interviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SwitchButtonV1 extends View implements View.OnClickListener{

    private static final String TAG = "SwitchButton";

    private Paint paint;

    private OnClickListener mOnClickListener;

    private int absWidth;
    private int absHeight;

    private float width;
    private float height;

    private float centerX;
    private float centerY;

    private float currentX;

    private int fr = 255;
    private int fg = 0;
    private int fb = 0;

    private int sr = 50;
    private int sg = 50;
    private int sb = 50;

    private int currentR = 0;
    private int currentG = 0;
    private int currentB = 0;

    private int rOffset = fr-sr;
    private int gOffset = fg-sg;
    private int bOffset = fb-sb;

    private boolean flag = false;

    private long runningTime = 100;

    private long proporation = 25;

    private float progress = 0;

    private float offset = 0;


    public SwitchButtonV1(Context context) {
        super(context);
        init();
    }

    public SwitchButtonV1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchButtonV1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        mOnClickListener = this;
        setOnClickListener(mOnClickListener);
    }

    /**
     * 测量控件长宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        absWidth = MeasureSpec.getSize(widthMeasureSpec);
        absHeight = MeasureSpec.getSize(heightMeasureSpec);
        adjustSize();
//        确定当前圆的位置
        currentX = flag?-width/4:width/4;

        offset = (width/2)/proporation;

    }

    /**
     * 调整宽高至合适
     */
    private void adjustSize() {
        if(absHeight<absWidth/2){
            height = absHeight;
            width = 2*height;
        }else{
            width = absWidth;
            height = width/2;
        }
        centerX = absWidth/2;
        centerY = absHeight/2;

        currentR = fr;
        currentG = fg;
        currentB = fb;

    }

    /**
     * 开始布局
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(centerX,centerY);
        if(flag&&currentX==-width/4){

            progress = 1;
            drawPointAndLine(canvas,currentX);

        }else if(!flag&&currentX==width/4){

            progress = 1;
            drawPointAndLine(canvas,currentX);

        }else if(flag&&currentX>-width/4){

            progress = -(currentX-(width/4))/(width/2);
            currentX = currentX-offset;
            if(Math.abs(currentX-(-width/4))<offset){
                currentX = -width/4;
            }
            drawPointAndLine(canvas,currentX);
            postInvalidateDelayed(runningTime/proporation);

        }else if(!flag&&currentX<width/4){

            progress = (currentX-(width/4))/(width/2);
            currentX = currentX+offset;
            if(Math.abs((width/4)-currentX)<offset){
                currentX = width/4;
            }
            drawPointAndLine(canvas,currentX);
            postInvalidateDelayed(runningTime/proporation);

        }

        Log.d(TAG, "onDraw: progress = "+progress);

    }

    private void drawPointAndLine(Canvas canvas,float xPosition){
        paint.setColor(getCurrentColor());
        paint.setStrokeWidth(height/10);
        canvas.drawLine(width/2,0,-width/2,0,paint);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(xPosition,0,width/4,paint);
    }

    private int getCurrentColor(){
        progress = Math.abs(progress);
        if(!flag){
            return Color.argb(255,(int)(sr+(progress*rOffset)),(int)(sg+(progress*gOffset)),(int)(sb+(progress*bOffset)));
        }else{
            return Color.argb(255,(int)(fr-(progress*rOffset)),(int)(fg-(progress*gOffset)),(int)(fb-(progress*bOffset)));
        }
    }

    @Override
    public void onClick(View v) {
        flag = !flag;
        postInvalidate();
    }

}
