package com.example.liujunyang.view_pratice.course06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.liujunyang.view_pratice.course06.Utils;

/**
 * Created by @author liujunyang
 * on 2018/11/28
 */
public class SportsView extends View {
    private static final float RING_WIDTH = Utils.dp2px(20);
    private static final float RADIUS = Utils.dp2px(150);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect = new Rect();
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();


    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }
    {
        paint.setTextSize(Utils.dp2px(100));
//        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Quicksand-Regular.ttf"));
        paint.getFontMetrics(fontMetrics);
//        paint.setTextAlign(Paint.Align.CENTER);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        // 绘制环
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(CIRCLE_COLOR);
//        paint.setStrokeWidth(RING_WIDTH);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
//
//        // 绘制进度条
//        paint.setColor(HIGHLIGHT_COLOR);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, -90, 225, false, paint);

        // 绘制文字
        paint.setTextSize(Utils.dp2px(80));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.getTextBounds("abgj安卓", 0, "abgj安卓".length(), rect);
        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2;
        canvas.drawText("abgj安卓", getWidth()/2, getHeight() / 2, paint);

        Log.d("jun","centerY:"+getHeight()/2);
        Log.d("jun","rect.top:"+rect.top+"rect.bottom:"+rect.bottom+"   fontMetrics.ascent:"+fontMetrics.ascent+"   fontMetrics.descent:"+fontMetrics.descent);
        paint.setStrokeWidth(Utils.dp2px(1));
        paint.setColor(Color.BLUE);
//        canvas.drawLine(rect.left+getWidth() /10,getHeight() / 2 + rect.top,rect.right+getWidth() /10,getHeight() / 2 + rect.top,paint);
//        canvas.drawLine(rect.left+getWidth() /10,getHeight() / 2 + rect.bottom,rect.right+getWidth() /10,getHeight() / 2 + rect.bottom,paint);
//        canvas.drawLine(rect.left+getWidth() /10,getHeight() / 2 + rect.top,rect.left+getWidth() /10,getHeight() / 2 + rect.bottom , paint);
//        canvas.drawLine(rect.right+getWidth() /10,getHeight() / 2 + rect.top,rect.right+getWidth() /10,getHeight() / 2 + rect.bottom , paint);
        paint.setColor(Color.RED);
        canvas.drawLine(0,getHeight() / 2 + fontMetrics.ascent,getWidth(),getHeight() / 2 + fontMetrics.ascent , paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawLine(0,getHeight() / 2 + fontMetrics.descent,getWidth(),getHeight() / 2 + fontMetrics.descent , paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,getHeight() / 2 + fontMetrics.top,getWidth(),getHeight() / 2 + fontMetrics.top , paint);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0,getHeight() / 2 + fontMetrics.bottom,getWidth(),getHeight() / 2 + fontMetrics.bottom , paint);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0,getHeight() / 2 ,getWidth(),getHeight() / 2  , paint);

        Log.d("jun","fontMetrics.top = "+fontMetrics.top);
        Log.d("jun","fontMetrics.ascent = "+fontMetrics.ascent);
        Log.d("jun","fontMetrics.descent = "+fontMetrics.descent);
        Log.d("jun","fontMetrics.bottom = "+fontMetrics.bottom);


    }
}
