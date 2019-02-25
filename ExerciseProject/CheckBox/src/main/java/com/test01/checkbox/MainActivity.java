package com.test01.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox cb1,cb2,cb3,cb4;
    private MyOnCheckedChangeListener listener = new MyOnCheckedChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox)findViewById(R.id.checkBox1);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);
        cb4 = (CheckBox)findViewById(R.id.checkBox4);

        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        cb3.setOnCheckedChangeListener(listener);
        cb4.setOnCheckedChangeListener(listener);
    }

    public void clickBtn(View view){
        switch (view.getId()){
            case R.id.okBtn:
                String str = checkedString();
                Toast.makeText(MainActivity.this,"你的爱好是：" + str,Toast.LENGTH_LONG).show();
                break;
        }
    }

    public  String checkedString(){
        StringBuilder sb = new StringBuilder();
        if(cb1.isChecked()){
            sb.append(cb1.getText().toString());
        }
        if(cb2.isChecked()){
            sb.append(cb2.getText().toString());
        }
        if(cb3.isChecked()){
            sb.append(cb3.getText().toString());
        }
        if(cb4.isChecked()){
            sb.append(cb4.getText().toString());
        }

        return sb.toString();
    }

    class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public  void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
            CheckBox cb = (CheckBox)buttonView;
            String str = cb.getText().toString();
            Toast.makeText(MainActivity.this, "你选择了" + str,Toast.LENGTH_LONG).show();
        }
    }
}
