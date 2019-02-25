package com.test01.activityconvertvalues;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class MyActivity extends Activity {
    private  TextView textView;
    private  MyApplication application;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        textView = (TextView)findViewById(R.id.textView);

        /*
         *方法1：通过Intent接收值

        //1.激活组件的Intent对象
        Intent intent = getIntent();

        //2.根据key获得传递的数据
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",0);
        char sex = intent.getCharExtra("sex",'男');
        double score = intent.getDoubleExtra("score",0);

        //3.将数据展示到TextView中
        textView.setText("名字：" + name + "\n年龄：" + age
                + "\n性别：" + sex + "\n成绩：" + score);
        */


        /*
         *方法2：通过Bundle接收值

        //1.激活组件的Intent对象
        Intent intent = getIntent();
        //2.获取传递的bundle对象
        Bundle bundle = intent.getExtras();
        //3.根据bundle获得key传递的数据
        String name = bundle.getString("name");
        int age = bundle.getInt("age",0);
        char sex = bundle.getChar("sex",'男');

        //4.将数据展示到TextView中
        textView.setText("名字：" + name + "\n年龄：" + age
                + "\n性别：" + sex);
        */


        /*
         *方法3：通过Application接收值
         */
        application = (MyApplication)getApplication();
        String name = application.getName();
        int age = application.getAge();
        textView.setText("名字：" + name + "\n年龄：" + age);
    }
}
