package com.exercise04.fragmentconvertvalue2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_convert;
    private FragmentManager fragmentManager;

    ContentFragment contentFragment = new ContentFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_convert = (Button)findViewById(R.id.btn_convert);
        fragmentManager = getSupportFragmentManager();


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //使用事务
        // add(@IdRes int var1, @NonNull Fragment var2, @Nullable String var3)
        //的方法传值
        fragmentTransaction.add(R.id.rl_content,contentFragment,"tag_value");
        fragmentTransaction.commit();

        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentFragment = (ContentFragment) fragmentManager.findFragmentByTag("tag_value");
                contentFragment.setMSg("这是传过来的值");
            }
        });
    }
}
