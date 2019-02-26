package com.exercise04.dynamicfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化Fragment对象
        LeftFragmentActivity leftFragment = new LeftFragmentActivity();
        RightFragmentActivity rightFragment = new RightFragmentActivity();
        //开启一个事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rl_left, leftFragment);
        ft.add(R.id.rl_right, rightFragment);
        ft.commit();    //提交事务


    }
}
