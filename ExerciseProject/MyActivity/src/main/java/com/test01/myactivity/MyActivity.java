package com.test01.myactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

    }
}
