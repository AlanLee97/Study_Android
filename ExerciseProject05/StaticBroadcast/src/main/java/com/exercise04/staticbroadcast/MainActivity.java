package com.exercise04.staticbroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button broadcast,order_broadcast;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcast = (Button)findViewById(R.id.sendBroadcast);
        order_broadcast = (Button)findViewById(R.id.sendOrderBroadcast);

        /**************************普通广播**************************************/
        //在java代码中注册动态广播
        //意图过滤器
        IntentFilter intentFilter = new IntentFilter("receiver");
        IntentFilter intentFilter2 = new IntentFilter("receiver");     //测试有序广播时用的
        //接收者对象
        MyReceiver myReceiver = new MyReceiver();
        MyReceiver2 myReceiver2 = new MyReceiver2();        //测试有序广播时用的
        //注册广播接收者的对象
        registerReceiver(myReceiver,intentFilter);
        registerReceiver(myReceiver2,intentFilter2);        //测试有序广播时用的

        intent = new Intent("receiver");
    }

    public void sendBroadcast(View view) {
        switch (view.getId()){
            case R.id.sendBroadcast:

                sendBroadcast(intent);      //发送广播
                Log.i("TAG","Broadcast");
                break;

            case R.id.sendOrderBroadcast:
                sendOrderedBroadcast(intent,null);      //发送有序广播
                Log.i("TAG","OrderedBroadcast");
                break;
        }


    }




    /**************************有序广播**************************************/





}
