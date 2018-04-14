package com.example.crazywah.interviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class SwitchButtonV3 extends View implements View.OnClickListener {

    private static final String TAG = "SwitchButtonV2";

    private int proportion = 10;

    private long runningTime = 100;

    private Paint paint;

    /**
     * 左弧正方形
     */
    private RectF leftArcRectf;

    /**
     * 右弧正方形
     */
    private RectF rightArcRectf;

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
     * 边框线粗细
     */
    private float borderWidth = 1;

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
     * 否定默认颜色
     */
    private int[] firstColor = new int[]{255, 255, 255, 255};
    /**
     * 肯定默认颜色
     */
    private int[] secondColor = new int[]{255, 84, 181, 239};

    /**
     * 边框线颜色
     */
    private int[] borderColor = new int[]{255, 200, 200, 200};

    /**
     * 当前边框线颜色
     */
    private int[] currentBorderColor = Arrays.copyOf(borderColor, borderColor.length);

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

    private OnStateChangeListener onStateChangeListener = null;

    public SwitchButtonV3(Context context) {
        super(context);
        init();
    }

    public SwitchButtonV3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchButtonV3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        setOnClickListener(this);
    }

    /**
     * 在View创建之初调用
     *
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
        lineRadiu = height / 10;
        //计算坐标轴的横向偏移值
        xOffset = measureWidth / 2 - 2 * cycleRadiu;
        //计算坐标轴的纵向偏移值
        yOffset = measureHeight / 2;

        //边框线宽度计算
        borderWidth = lineRadiu/5;

//        leftArcRectf = new RectF(borderWidth,lineRadiu-borderWidth,lineRadiu*2-borderWidth,-(lineRadiu-borderWidth));
//        rightArcRectf = new RectF(width-lineRadiu*2+borderWidth,lineRadiu-borderWidth,width-borderWidth,-(lineRadiu-borderWidth));

        leftArcRectf = new RectF(borderWidth,lineRadiu-borderWidth,lineRadiu*2-borderWidth,-(lineRadiu-borderWidth));
        rightArcRectf = new RectF(width-lineRadiu*2+borderWidth,lineRadiu-borderWidth,width-borderWidth,-(lineRadiu-borderWidth));

    }

    /**
     * 在View创建之初调用
     *
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
        canvas.translate(xOffset, yOffset);

        //计算当前颜色的RGB
        currentColor[1] = firstColor[1] - (int) ((progress / proportion) * (firstColor[1] - secondColor[1]));
        currentColor[2] = firstColor[2] - (int) ((progress / proportion) * (firstColor[2] - secondColor[2]));
        currentColor[3] = firstColor[3] - (int) ((progress / proportion) * (firstColor[3] - secondColor[3]));

        //计算当前边框线颜色
        currentBorderColor[1] = borderColor[1] - (int) ((progress / proportion) * (borderColor[1] - secondColor[1]));
        currentBorderColor[2] = borderColor[2] - (int) ((progress / proportion) * (borderColor[2] - secondColor[2]));
        currentBorderColor[3] = borderColor[3] - (int) ((progress / proportion) * (borderColor[3] - secondColor[3]));

        //绘制两个圆和横线
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.argb(currentColor[0], currentColor[1], currentColor[2], currentColor[3]));
        //绘制左右两个圆
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(lineRadiu, 0, lineRadiu-borderWidth, paint);
        canvas.drawCircle(width - lineRadiu, 0, lineRadiu-borderWidth, paint);

        //绘制横线边框线
        paint.setColor(Color.argb(currentBorderColor[0],currentBorderColor[1],currentBorderColor[2],currentBorderColor[3]));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        canvas.drawLine(lineRadiu,lineRadiu-borderWidth,width-lineRadiu,lineRadiu-borderWidth,paint);
        canvas.drawLine(lineRadiu,-(lineRadiu-borderWidth),width-lineRadiu,-(lineRadiu-borderWidth),paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(leftArcRectf,90,360,false,paint);
        canvas.drawArc(rightArcRectf,-90,360,false,paint);

        //绘制横线
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.argb(currentColor[0], currentColor[1], currentColor[2], currentColor[3]));
        //绘制横线
        paint.setStrokeWidth(lineRadiu * 2-borderWidth*3);
        canvas.drawLine(lineRadiu, 0, width - lineRadiu, 0, paint);

        //绘制大圆
        paint.setColor(Color.argb(currentColor[0], currentColor[1], currentColor[2], currentColor[3]));
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(cycleRadiu + ((progress / proportion) * (2 * cycleRadiu)), 0, cycleRadiu, paint);

        //绘制大圆的边框线
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        paint.setColor(Color.argb(currentBorderColor[0],currentBorderColor[1],currentBorderColor[2],currentBorderColor[3]));
        // TODO: 2018/4/14 注意边框线的绘制是按照边框线宽度的一半，在半径的内外分别绘制
        canvas.drawCircle(cycleRadiu + ((progress / proportion) * (2 * cycleRadiu)), 0, cycleRadiu-borderWidth/2, paint);

        //计算当前进度
        if (!enable && progress > 0) {
            if (progress - 1 < 0) {
                progress = 0;
            } else {
                progress--;
            }
            postInvalidateDelayed(runningTime / proportion);
        }

        if (enable && progress < proportion) {
            if (progress + 1 > proportion) {
                progress = proportion;
            } else {
                progress++;
            }
            postInvalidateDelayed(runningTime / proportion);
        }

    }

    @Override
    public void onClick(View v) {
        enable = !enable;
        if(onStateChangeListener!=null){
            onStateChangeListener.onStateChange(enable);
        }
        postInvalidate();
    }

    /**
     * 设置否定颜色
     *
     * @param a
     * @param r
     * @param g
     * @param b
     */
    public void setFirstColor(int a, int r, int g, int b) {
        firstColor[0] = a;
        firstColor[1] = r;
        firstColor[2] = g;
        firstColor[3] = b;
        postInvalidate();
    }

    /**
     * 设置肯定颜色的ARGB
     *
     * @param a
     * @param r
     * @param g
     * @param b
     */
    public void setSecondColor(int a, int r, int g, int b) {
        secondColor[0] = a;
        secondColor[1] = r;
        secondColor[2] = g;
        secondColor[3] = b;
        postInvalidate();
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public interface OnStateChangeListener{
        void onStateChange(boolean enable);
    }

}
