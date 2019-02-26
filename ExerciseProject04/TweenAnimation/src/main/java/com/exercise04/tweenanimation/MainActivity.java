package com.exercise04.tweenanimation;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button alpha,rotate,scale,translate,animationSet;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        alpha = (Button)findViewById(R.id.alpha);
        rotate = (Button)findViewById(R.id.rotate);
        scale = (Button)findViewById(R.id.scale);
        translate = (Button)findViewById(R.id.translate);
        animationSet = (Button)findViewById(R.id.animationSet);

        //1.透明动画
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setRepeatCount(2);
                alphaAnimation.setRepeatMode(Animation.RESTART);

                //保持结束的样子
                alphaAnimation.setFillAfter(true);

                imageView.startAnimation(alphaAnimation);
            }
        });

        //2.旋转动画
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation rotateAnimation = new RotateAnimation(0,90);
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatCount(2);
                rotateAnimation.setRepeatMode(Animation.RESTART);

                //保持结束的样子
                rotateAnimation.setFillAfter(true);

                imageView.startAnimation(rotateAnimation);
            }
        });

        //3.缩放动画
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,50,0,50);
                scaleAnimation.setDuration(500);
                scaleAnimation.setRepeatCount(2);
                scaleAnimation.setRepeatMode(Animation.RESTART);

                //保持结束的样子
                scaleAnimation.setFillAfter(true);

                imageView.startAnimation(scaleAnimation);
            }
        });

        //4.平移动画
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0,50,0,50);
                translateAnimation.setDuration(500);
                translateAnimation.setRepeatCount(2);
                translateAnimation.setRepeatMode(Animation.RESTART);

                //保持结束的样子
                translateAnimation.setFillAfter(true);

                imageView.startAnimation(translateAnimation);
            }
        });

        //5.多种动画
        animationSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
                RotateAnimation rotateAnimation = new RotateAnimation(0,90);
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,50,0,50);
                TranslateAnimation translateAnimation = new TranslateAnimation(0,50,0,50);

                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(rotateAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.setDuration(500);
                animationSet.setRepeatCount(2);
                animationSet.setRepeatMode(Animation.RESTART);

                //保持结束的样子
                animationSet.setFillAfter(true);

                imageView.startAnimation(animationSet);



            }
        });

    }
}
