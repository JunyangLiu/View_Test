package com.example.liujunyang.view_pratice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.liujunyang.view_pratice.course07.CameraView;

public class MainActivity extends AppCompatActivity {
    CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = (CameraView) findViewById(R.id.view);
        ObjectAnimator animator = ObjectAnimator.ofFloat(cameraView,"bottomFlip",0,100);
        animator.setDuration(3000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();

    }
}
