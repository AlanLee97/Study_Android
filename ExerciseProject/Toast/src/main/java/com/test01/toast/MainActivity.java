package com.test01.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1  = (Button)findViewById(R.id.button1);
        btn2  = (Button)findViewById(R.id.button2);
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.button1:
                Toast.makeText(MainActivity.this,"Toast提示",Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                Toast toast = Toast.makeText(MainActivity.this,"改变了Toast提示的位置",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                break;
        }
    }

}
