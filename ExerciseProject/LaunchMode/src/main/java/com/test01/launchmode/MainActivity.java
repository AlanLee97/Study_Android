package com.test01.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button actBtn1,actBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actBtn1 = (Button)findViewById(R.id.button1);
        actBtn2 = (Button)findViewById(R.id.button2);
    }

    public void click(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.button1:
                intent = new Intent(MainActivity.this,Activity01.class);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this,Activity02.class);
                break;
        }
        startActivity(intent);

    }
}
