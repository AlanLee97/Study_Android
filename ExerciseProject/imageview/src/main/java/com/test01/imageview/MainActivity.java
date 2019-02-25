package com.test01.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int [] images = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8
    };
    private ImageView iv;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        iv = (ImageView) findViewById(R.id.iv);
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.btn_pre:
                index--;
                break;
            case R.id.btn_next:
                index++;
                break;
        }
        if(index < 0){
            index = 0;
        }

        if(index > images.length - 1){
            index = images.length - 1;
        }

        iv.setImageResource(images[index]);
    }

}


