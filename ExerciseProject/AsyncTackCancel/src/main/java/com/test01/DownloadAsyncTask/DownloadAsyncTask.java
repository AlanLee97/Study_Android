package com.test01.DownloadAsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<String, Integer,byte[]> {
    private ProgressDialog pd;
    private Context context;
    private ImageView imageView;

    public DownloadAsyncTask(Context context,ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    /*
     *表示在AsyncTask执行之前运行在ui线程的准备方法  初始化操作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("tag",Thread.currentThread().getName() +  "-------onPreExecute() ");
        pd = new ProgressDialog(context);
        pd.setTitle("下载进度");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE,"取消下载",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancel(true);
            }
        });

        pd.show();
    }

    /*
     *该方法调用成功后，不会再调用onPostExecute()
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
        pd.dismiss();
        Log.i("tag", "------onCancelled: 异步任务被取消了");
    }

    /*
     *表示在onPreExecute()方法执行结束后立即执行
     * 该方法运行在工作线程中，主要执行耗时操作
     * 该方法的参数类型与类泛型第一个参数类型一致
     * 返回值类型与类泛型中第三个参数一致
     */
    @Override
    protected byte[] doInBackground(String... strings) {
        Log.i("tag",Thread.currentThread().getName() +  "-------doInBackground(String... strings)");
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
                //获取下载图片数据文件的总长度
                long totalLength = conn.getContentLength();
                int currentLength = 0;

                byte[] data = new byte[1024];
                int temp = 0;
                while ((temp = inputStream.read(data)) != -1 && !isCancelled()){
                    currentLength += temp;
                    //计算下载进度
                    int progress = (int) ((currentLength / (float)totalLength ) * 100);
                    //将进度发布到主线程中
                    publishProgress(progress);
                    outputStream.write(data,0,temp);
                    outputStream.flush();
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
            Toast.makeText(context,"图片下载失败",Toast.LENGTH_SHORT).show();

        }
        pd.dismiss();
    }

    /*
     *表示运行在主线程中用来更新进度的回调方法
     *
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i("tag",Thread.currentThread().getName() +  "-------onProgressUpdate(Integer... values)");
        pd.setProgress(values[0]);//设置更新进度
    }
}


