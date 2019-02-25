package com.test01.task;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;

public class MyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void clickMyActBtn(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:10010"));


        startActivity(intent);
    }
}
