package com.test01.intentfilter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);
    }


    public void click(View view){
        switch (view.getId()){
            case R.id.button:
                Intent intent = new Intent();
                intent.setAction("com.test01.action");
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent();
                intent1.setAction("com.test01.intentattribute");
                startActivity(intent1);
                break;
        }
    }
}
