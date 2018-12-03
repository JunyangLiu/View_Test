package com.example.liujunyang.view_pratice.course07;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.liujunyang.view_pratice.Utils;

/**
 * Created by @author liujunyang
 * on 2018/12/3
 */
public class CameraView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();
    float flipRotation = 0;
    float bottomFlip = 45;
    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera.rotateX(30);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制上半部分
        canvas.save();
        canvas.rotate(-20,100 + 600 / 2, 100 + 600 / 2);
        canvas.clipRect(0, 0, getWidth(), 100 + 600 / 2);
        canvas.rotate(20,100 + 600 / 2, 100 + 600 / 2);
        canvas.drawBitmap(Utils.getAvatar(getResources(), 600), 100, 100, paint);
        canvas.restore();


        //绘制下半部分
        canvas.save();
        canvas.rotate(-20,100 + 600 / 2, 100 + 600 / 2);
        canvas.clipRect(-100, 100+600/2, getWidth(), getHeight());
        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-(100 + 600 / 2), -(100 + 600 / 2));
        canvas.rotate(20,100 + 600 / 2, 100 + 600 / 2);
        canvas.drawBitmap(Utils.getAvatar(getResources(), 600), 100, 100, paint);
        canvas.restore();



    }


    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }
}
