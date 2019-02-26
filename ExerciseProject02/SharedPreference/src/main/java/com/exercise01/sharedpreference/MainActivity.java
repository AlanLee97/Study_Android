package com.exercise01.sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText1,editText2;
    private Button button1,button2;
    private String name;
    private String age;

    //1.获取对象
    private  SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.et1);
        editText2 = (EditText)findViewById(R.id.et2);
        button1 = (Button)findViewById(R.id.btn1);
        button2 = (Button)findViewById(R.id.btn2);

        sp = getSharedPreferences("info",MODE_PRIVATE);
        editor = sp.edit();


    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                name = editText1.getText().toString().trim();
                age = editText2.getText().toString().trim();
                //2.添加数据
                editor.putString("name",name);
                editor.putString("age",age);

                //3.提交
                boolean commit = editor.commit();
                if(commit){
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    editText1.setText("");
                    editText2.setText("");
                }
                break;
            case R.id.btn2:
                String nameValue = sp.getString("name","");
                String ageValue = sp.getString("age","");
                editText1.setText(nameValue);
                editText2.setText(ageValue);
                break;
                default:
                    break;
        }
    }
}
