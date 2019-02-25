package com.test01.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        Log.i("tag",Thread.currentThread().getName() +  "-------MyAsyncTask().execute(imagePath) ");
        new MyAsyncTask().execute(imagePath);
    }

    /*
     * 参数1： 操作时需要的类型
     * 参数2： 操作时的进度类型
     * 参数3： 操作结果的数据类型
     */
    class MyAsyncTask extends AsyncTask<String, Void,byte[]>{
        /*
         *表示在AsyncTask执行之前运行在ui线程的准备方法  初始化操作
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("tag",Thread.currentThread().getName() +  "-------onPreExecute() ");
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
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode = conn.getResponseCode();
                if(responseCode == 200){
                    InputStream inputStream = conn.getInputStream();
                    byte[] data = new byte[1024];
                    int temp = 0;
                    while ((temp = inputStream.read(data)) != -1){
                        outputStream.write(data,0,temp);
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
            Log.i("tag",Thread.currentThread().getName() +  "-------onPostExecute() ");
            if(bytes != null && bytes.length != 0){
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imageView.setImageBitmap(bitmap);
            }else{
                Toast.makeText(MainActivity.this,"图片下载失败",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
