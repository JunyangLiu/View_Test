package com.example.liujunyang.view_pratice.course06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.liujunyang.view_pratice.Utils;

/**
 * Created by @author liujunyang
 * on 2018/11/8
 */
public class Dashboard extends View {

    private static final int START_ANGLE = 120;
    private static final int SWEEP_ANGLE = 300;
    private static final int MARK_COUNTS = 20;
    private static final float LINE_LENGTH = 100;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path arc = new Path();
    private Path dash = new Path();
    private PathDashPathEffect mPathEffect;
    private float left;
    private float top;
    private float right;
    private float bottom;




    public Dashboard(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(Utils.dp2px(2));
        left = getWidth()/2-Utils.dp2px(150);
        top = getHeight()/2-Utils.dp2px(150);
        right = getWidth()/2+Utils.dp2px(150);
        bottom = getHeight()/2+Utils.dp2px(150);
        arc.addArc(left,top,right,bottom,START_ANGLE,SWEEP_ANGLE);
        dash.addRect(0,0,Utils.dp2px(2),Utils.dp2px(10),Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure(arc,false);
        mPathEffect = new PathDashPathEffect(dash,((pathMeasure.getLength()-Utils.dp2px(2))/MARK_COUNTS),0,PathDashPathEffect.Style.ROTATE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线
        float left = getWidth()/2-Utils.dp2px(150);
        float top = getHeight()/2-Utils.dp2px(150);
        float right = getWidth()/2+Utils.dp2px(150);
        float bottom = getHeight()/2+Utils.dp2px(150);
        Log.d("jun", "getWidth()"+getWidth()+" getHeight()"+getHeight());
        Log.d("jun","left"+left+" top"+top+"  right"+right+"  bottom"+bottom);

        //画弧
        canvas.drawArc(left,top,right,bottom,START_ANGLE,SWEEP_ANGLE,false,paint);

        //画刻度
        paint.setPathEffect(mPathEffect);
        canvas.drawArc(left,top,right,bottom,START_ANGLE,SWEEP_ANGLE,false,paint);



        //画指针线
        Log.d("jun",getAngleFromMark(5)+"");
        float stopX = (float) (Math.cos(Math.toRadians(getAngleFromMark(5)))*Utils.dp2px(LINE_LENGTH)+getWidth()/2);
        float stopY = (float) (Math.sin(Math.toRadians(getAngleFromMark(5)))*Utils.dp2px(LINE_LENGTH)+(getHeight()/2));
        canvas.drawLine(getWidth()/2,getHeight()/2,stopX,stopY,paint);



    }

    /**
     * 根据第几刻度获取对应的角度
     * @param mark
     * @return
     */
      private float getAngleFromMark(int mark) {
          return  START_ANGLE+(SWEEP_ANGLE)/MARK_COUNTS*mark;
      }
}
