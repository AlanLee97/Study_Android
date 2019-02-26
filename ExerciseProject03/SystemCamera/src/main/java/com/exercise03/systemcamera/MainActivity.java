package com.exercise03.systemcamera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 1;
    private ImageView img_show;
    private Button btn_start;
    private File currentImageFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //严格模式
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        bindViews();
    }

    private void bindViews() {
        img_show = (ImageView) findViewById(R.id.img_show);
        btn_start = (Button) findViewById(R.id.btn_start);

        //设置按钮监听事件
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得SD卡目录
                File dir = new File(Environment.getExternalStorageDirectory(),"pictures");
                if(dir.exists()){
                    dir.mkdirs();
                }

                //获得文件
                currentImageFile = new File(dir,System.currentTimeMillis() + ".jpg");
                if(!currentImageFile.exists()){
                    try {
                        currentImageFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //步骤1.通知系统相机
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri fileUri = Uri.fromFile(currentImageFile);//为MediaStore.EXTRA_OUTPUT准备的Uri

                //MediaStore.EXTRA_OUTPUT需要一个Uri
                it.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                //步骤2.执行Camera intent
                startActivityForResult(it, REQUEST_CODE);
            }
        });
    }


    //步骤3.重写系统相机后的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //将拍照得到的照片添加到ImageView中
        if (requestCode == REQUEST_CODE) {
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//            img_show.setImageBitmap(bitmap);

            //使用Uri的方式
            Uri fileUri = Uri.fromFile(currentImageFile);
            img_show.setImageURI(fileUri);
        }


//        switch (requestCode){
//            case REQUEST_CODE_TAKE_PICTURE:
//                img_show.setImageURI(Uri.fromFile(currentImageFile));
//                break;
//        }
    }
}
