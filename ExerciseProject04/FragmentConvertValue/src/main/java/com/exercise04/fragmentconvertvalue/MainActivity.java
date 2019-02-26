package com.exercise04.fragmentconvertvalue;

import android.content.ContentResolver;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_convert;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_convert = (Button)findViewById(R.id.btn_convert);
        fragmentManager = getSupportFragmentManager();

        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ContentFragment contentFragment = new ContentFragment();

                //使用Bundle存放值
                Bundle args = new Bundle();
                args.putString("key","Activity传递的信息");

                contentFragment.setArguments(args);
                fragmentTransaction.add(R.id.rl_content,contentFragment);
                fragmentTransaction.commit();

            }
        });
    }


}
