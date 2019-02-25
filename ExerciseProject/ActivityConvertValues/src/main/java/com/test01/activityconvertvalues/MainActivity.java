package com.test01.activityconvertvalues;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {
    private MyApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view){

        /*
        *方法1：通过Intent传值

        Intent intent = new Intent(MainActivity.this,MyActivity.class);
        intent.putExtra("name","你不乖");
        intent.putExtra("age",20);
        intent.putExtra("sex",'男');
        intent.putExtra("score",99.5);

        startActivity(intent);
        */

        /*
         *方法2：通过Bundle对象传值

        //1.创建一个Intent对象
        Intent intent = new Intent(MainActivity.this,MyActivity.class);
        //2.创建一个Bundle对象，用来存储需要传递的数据
        Bundle bundle = new Bundle();
        //3.将需要传递的数据存储的到bundle对象中
        bundle.putString("name","你不乖");
        bundle.putInt("age",20);
        bundle.putChar("sex",'男');
        //4.将bundle对象存储到intent对象中
        intent.putExtras(bundle);
        //5.启动Activity
        startActivity(intent);

        */

        /*
         *方法3：通过Application传值
         */
        //1.获得application对象
        application = (MyApplication)getApplication();
        application.setName("你不乖");
        application.setAge(20);
        //启动Activity
        startActivity(new Intent(MainActivity.this,MyActivity.class));




    }
}
