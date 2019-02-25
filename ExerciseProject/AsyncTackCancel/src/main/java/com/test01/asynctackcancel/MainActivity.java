package com.test01.asynctackcancel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.test01.DownloadAsyncTask.DownloadAsyncTask;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private String imagePath = "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1508/01/c4/10533938_10533938_1438414723499.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.iv);
    }

    public void click(View view){
        new DownloadAsyncTask(MainActivity.this,imageView).execute(imagePath);
    }
}
