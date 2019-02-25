package com.test01.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;

public class Activity01 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);
    }

    public void click(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.button1:
                intent = new Intent(Activity01.this,Activity01.class);
                break;
            case R.id.button2:
                intent = new Intent(Activity01.this,Activity02.class);
                break;
        }
        startActivity(intent);
    }
}
