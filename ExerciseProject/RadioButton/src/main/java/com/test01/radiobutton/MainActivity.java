package com.test01.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.*;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn_ok);
        rg = (RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton)findViewById(checkedId);
                String str = rb.getText().toString();
                Toast.makeText(MainActivity.this,"你选中了" + str,Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void click(View view){
        int checkedId = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(checkedId);
        Toast.makeText(MainActivity.this,
                "你最终选择的是" + rb.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}
