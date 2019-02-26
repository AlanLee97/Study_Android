package com.exercise03.testapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

//调用系统相机

public class SystemCameraActivity extends AppCompatActivity{
    private Button takePhoto;
    private ImageView preView;
    private File capturedPhoto = null;
    private Uri fileUri = null;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_camera);

        //严格模式
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        //绑定控件
        preView = (ImageView)findViewById(R.id.preView);
        takePhoto = (Button)findViewById(R.id.takephoto);

        //设置takePhoto按钮监听事件
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将文件保存在SD卡中
                File dir = new File(Environment.getExternalStorageDirectory(),"SystemCamera");
                if(!dir.exists()){
                    dir.mkdirs();
                }

                //照片文件名
                String capturePhotoName = System.currentTimeMillis() + ".jpg";
                capturedPhoto = new File(dir,capturePhotoName);
                //判断该文件是否存在
                if(!capturedPhoto.exists()){
                    //创建这个文件
                    try {
                        capturedPhoto.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //step1:调用系统相机隐式 Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = Uri.fromFile(capturedPhoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);

                //step2: 启用intent
                startActivityForResult(intent,REQUEST_CODE);

            }
        });

    }

    //step3: 重写方法

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //将拍照得到的照片添加到ImageView中
        if(requestCode == REQUEST_CODE){
            fileUri = Uri.fromFile(capturedPhoto);
            preView.setImageURI(fileUri);
            Toast.makeText(this, "图片已加载到ImageView中", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
        }
    }
}


