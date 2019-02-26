package com.exercise04.handlemessage;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    int count = 0;
    private Button btn_sendMsg;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sendMsg = (Button)findViewById(R.id.sendMsg);
        textView = (TextView)findViewById(R.id.text);
    }

    //1. 定义一个UI线程
    public void sendMsg(View view) {

        //2. 定义一个工作线程
        new Thread(){
            @Override
            public void run() {
                while (count < 20){
                    count++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //textView.setText("当前的值 = " + count);      //不能在工作线程中更新UI线程
                    //4. 处理方法：使用Handle发送消息来更新UI线程
                    Message msg = Message.obtain();//获取消息对象
                    msg.what = 1;   //消息标识
                    msg.arg1 = count;   //传递count值
                    handler.sendMessage(msg);

                }


            }
        }.start();
    }

    //3. 创建Handler对象
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //5. 更新UI线程
            switch (msg.what){
                case 1:
                    textView.setText("当前的值 = " + msg.arg1);
                    break;
            }
        }
    };
}
