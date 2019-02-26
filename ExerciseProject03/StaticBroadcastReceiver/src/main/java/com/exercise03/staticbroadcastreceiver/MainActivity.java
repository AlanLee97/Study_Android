package com.exercise03.staticbroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //过滤器
        IntentFilter intentFilter = new IntentFilter("receiver");
        //创建广播接收者的对象
        MyReceiver myReceiver = new MyReceiver();
        //注册广播接收者的对象
        registerReceiver(myReceiver,intentFilter);

    }

    public void send(View view) {
        Intent intent = new Intent("receiver");
        sendBroadcast(intent);
    }
}
