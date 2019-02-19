package com.example.a12_scalable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by @author liujunyang
 * on 2019/2/19
 */
public class ScalableImageView extends View {
    private static final float IMAGE_WIDTH = Utils.dpToPixel(300);
    float offsetX;
    float offsetY;
    //bitmap 绘制位置的其实偏移
    float originalOffsetX;
    float originalOffsetY;
    //是否处于放大
    boolean big;
    float smallScale;
    float bigScale;
    float currentScale;
    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }


    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    ObjectAnimator objectAnimator;
    GestureDetectorCompat gestureDector;
    MyGestureListener gestureListener = new MyGestureListener();
    public ScalableImageView(Context context) {
        super(context);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        gestureDector = new GestureDetectorCompat(context,gestureListener);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = ((float) getWidth() - bitmap.getWidth()) / 2;
        originalOffsetY = ((float) getHeight() - bitmap.getHeight()) / 2;
        currentScale = getWidth()/IMAGE_WIDTH;
        if((float)getWidth()/bitmap.getWidth() > (float)getHeight()/bitmap.getHeight()){
            bigScale = (float)getWidth()/bitmap.getWidth();
            smallScale = (float)getHeight()/bitmap.getHeight();
        }else{
            smallScale = (float)getWidth()/bitmap.getWidth();
            bigScale = (float)getHeight()/bitmap.getHeight();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        Log.d("junyang","originalOffsetX = "+originalOffsetX+" originalOffsetY = "+originalOffsetY);
        canvas.drawBitmap(bitmap,originalOffsetX,originalOffsetY,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("jun", "onTouchEvent");
        boolean result = gestureDector.onTouchEvent(event);
        return result;
    }

    private ObjectAnimator getScaleAnimator(){
        if (objectAnimator == null){
            objectAnimator = ObjectAnimator.ofFloat(this,"currentScale",smallScale,bigScale);
            Log.d("jun","smallScale = "+smallScale + " bigScale "+bigScale);
        }
        return objectAnimator;
    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("jun","onDown");
            //这里要返回true 当前事件序列才会被消费
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("jun","onDoubleTap");
            big = !big;
            if(big){
                getScaleAnimator().start();
            }else{
                getScaleAnimator().reverse();
            }
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }
}
