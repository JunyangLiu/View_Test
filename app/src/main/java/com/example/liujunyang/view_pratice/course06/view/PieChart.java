package com.example.liujunyang.view_pratice.course06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.liujunyang.view_pratice.Utils;

/**
 * Created by @author liujunyang
 * on 2018/11/28
 */
public class PieChart extends View {
    private static final int RADIUS = (int) Utils.dp2px(150);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF bounds = new RectF();
    int currentAngle = 0;
    int[] angles = {60, 100, 120, 80};
    int[] colors = {Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
            Color.parseColor("#009688"), Color.parseColor("#FF8F00")};


    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colors[i]);
            //离屏缓冲
            canvas.save();
            if(i == 2){
                float  translateX = (float) Math.cos((double) Math.toRadians((double) (currentAngle+(angles[i]/2)))) * Utils.dp2px(20);
                float  translateY = (float) Math.sin((double) Math.toRadians((double) (currentAngle+(angles[i]/2)))) * Utils.dp2px(20);
                canvas.translate(translateX,translateY);
            }
            canvas.drawArc(bounds,currentAngle,angles[i],true,paint);
            canvas.restore();
            currentAngle += angles[i];
        }

    }
}
