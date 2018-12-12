package com.example.liujunyang.view_pratice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.liujunyang.view_pratice.course07.CameraView;
import com.example.liujunyang.view_pratice.course08.FancyFlipView;

public class MainActivity extends AppCompatActivity {
    CameraView view;
//    FancyFlipView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        view = (CameraView) findViewById(R.id.view);
//        view = (FancyFlipView) findViewById(R.id.view);
        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view,"bottomFlip",45);
        bottomFlipAnimator.setDuration(2000);
//        bottomFlipAnimator.start();

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view,"flipRotation",270);
        flipRotationAnimator.setDuration(2000);
//        flipRotationAnimator.start();
        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", - 45);
        topFlipAnimator.setDuration(1500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator,topFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();
    }
}
