package com.exercise03.testapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlbumActivity extends Activity {
    private Button lastImage,nextImage;
    private ImageView imageView;
    private String[] imagePaths = new String[]{
            "http://pic.ffpic.com/files/desk/android2.jpg",
            "http://img.zcool.cn/community/015e29593e5f43a8012193a3c3e382.jpg@1280w_1l_2o_100sh.png",
            "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1102/07/c2/6696123_6696123_1297054484266.jpg",
            "http://attachments.gfan.com/forum/201412/09/1609541dvm7j55d16w6w4d.jpg",
            "http://old.bz55.com/uploads/allimg/160607/140-16060G10225.jpg",
            "http://pic1.win4000.com/wallpaper/2018-02-03/5a75631925bc8.jpg",
            "http://pic1.win4000.com/wallpaper/2018-02-03/5a756330346ac.jpg"
    } ;
    private int[] images1 = new int[]{
            R.drawable.icon1,
            R.drawable.icon2
    };
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        imageView = (ImageView)findViewById(R.id.iv_showImage);
        lastImage = (Button)findViewById(R.id.lastImage);
        nextImage = (Button)findViewById(R.id.nextImage);

        lastImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadImage().execute(imagePaths[index]);
                if(index == 0){
                    Toast.makeText(AlbumActivity.this, "已经是第一张图片了", Toast.LENGTH_SHORT).show();
                    index = 0;
                    return;
                }

                index--;

            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadImage().execute(imagePaths[index]);
                if(index == imagePaths.length - 1){
                    Toast.makeText(AlbumActivity.this, "已经是最后一张图片了", Toast.LENGTH_SHORT).show();
                    index = imagePaths.length - 1;
                    return;
                }
                index++;
            }
        });
    }

//===================================下载图片
    /*
     * 参数1： 操作时需要的类型
     * 参数2： 操作时的进度类型
     * 参数3： 操作结果的数据类型
     */
    class DownloadImage extends AsyncTask<String, Void,byte[]> {
    /*
     *表示在AsyncTask执行之前运行在ui线程的准备方法  初始化操作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("tag", Thread.currentThread().getName() + "-------onPreExecute() ");
    }

    /*
     *表示在onPreExecute()方法执行结束后立即执行
     * 该方法运行在工作线程中，主要执行耗时操作
     * 该方法的参数类型与类泛型第一个参数类型一致
     * 返回值类型与类泛型中第三个参数一致
     */
    @Override
    protected byte[] doInBackground(String... strings) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] images = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                byte[] data = new byte[1024];
                int temp = 0;
                while ((temp = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, temp);
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        images = outputStream.toByteArray();
        return images;
    }


    /*
     *doInBackground执行后调用该方法
     */
    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        Log.i("tag", Thread.currentThread().getName() + "-------onPostExecute() ");
        if (bytes != null && bytes.length != 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(AlbumActivity.this, "图片下载失败", Toast.LENGTH_SHORT).show();

        }
    }
}
}
