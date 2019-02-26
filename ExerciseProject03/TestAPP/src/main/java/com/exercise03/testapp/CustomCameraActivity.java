package com.exercise03.testapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CustomCameraActivity extends AppCompatActivity {
    //step1:设置各种所需要的变量
    private Camera camera;                  //定义相机对象
    private boolean isPreview = false;      //定义非预览状态
    private SurfaceView surfaceView;
    private Button takePhoto;
    String photoPath = "/DCIM/Camera/";     //照片文件存储路径
    //这个也是一个变量，只是很长而已
    private SurfaceHolder.Callback SurfaceHolder_Callback = new SurfaceHolder.Callback(){

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            startPreview();     //开始预览
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            stopPreview();      //停止预览
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        /*//这个好像没什么用了，因为现在大多数手机都不装SD卡了
        if (!Environment.getExternalStorageState().equals(  //判断手机是否安装SD卡
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "请安装SD卡！", Toast.LENGTH_SHORT).show(); // 提示安装SD卡
        }
        */


        //step2:
        //获取SurfaceView组件，用于显示相机预览
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        //获取SurfaceHolder对象
        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        //设置该SurfaceHolder自己不维护缓冲
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(SurfaceHolder_Callback);//刚刚那个很长的变量用在这里了

        //获取Button组件，用于拍照
        takePhoto = (Button)findViewById(R.id.takephoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {//设置按钮监听事件
            @Override
            public void onClick(View v) {
                if(camera != null){
                    camera.takePicture(null,null,Camera_PictureCallback);
                    Toast.makeText(CustomCameraActivity.this, "照片已保存在" + photoPath + "中", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //step3: 实现将照片保存到系统图库中
    final Camera.PictureCallback Camera_PictureCallback = new Camera.PictureCallback(){

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
            camera.stopPreview();       //停止预览
            isPreview = false;         //设置为非预览状态

            //获取sd卡根目录
            File dir = new File(Environment.getExternalStorageDirectory(),photoPath);
            if(!dir.exists()){      //如果该目录不存在
                dir.mkdirs();       //就创建该目录
            }

            //将获取当前系统时间设置为照片名称
            String fileName = System.currentTimeMillis() + ".jpg";
            //创建文件对象
            File capturedPhoto = new File(dir,fileName);

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(capturedPhoto);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };



    //开始预览，图像显示在SurfaceView中
    public void startPreview(){
        camera = Camera.open();
        isPreview = true;                                //设置为预览状态
        try {
            camera.setPreviewDisplay(surfaceView.getHolder());
            camera.setDisplayOrientation(90);   //让相机旋转90度
            camera.startPreview();
            camera.autoFocus(null);                        //设置自动对焦
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //停止预览
    public void stopPreview(){
        camera.stopPreview();
        camera.release();
        camera = null;
    }


    @Override
    protected void onPause() {
        super.onPause();

        if(camera != null){
            camera.stopPreview();
            camera.release();
        }
    }
}
