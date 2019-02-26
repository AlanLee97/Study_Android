package com.exercise01.sharepreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String count;
    private String password;

    private String c_name = "nibuguai";
    private String c_pwd = "nibuguai";

    EditText editText_count;
    EditText editText_pwd;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_count = (EditText)findViewById(R.id.et_count);
        editText_pwd = (EditText)findViewById(R.id.et_pwd);
        button = (Button) findViewById(R.id.btn);

        func();
    }

    public void func(){
        //1.获得SharedPreferences.Editor对象，调用edit()，写入内容
        final SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        if(sp.getString("et_count",count) != null &&
                sp.getString("et_pwd",password) != null){
            if(sp.getString("et_count",count).equals(c_name) &&
                    sp.getString("et_pwd",password).equals(c_pwd)){
                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);
            }
        }else{
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count = editText_count.getText().toString();
                    password = editText_pwd.getText().toString();

                    if(count.equals(c_name) && password.equals(c_pwd)){
                        Toast.makeText(MainActivity.this, "账号密码正确", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                        startActivity(intent);
                        //2.向SharedPreferences文件存储数据
                        editor.putString("count",count);
                        editor.putString("password",password);
                        //3.提交数据
                        editor.commit();
                    }else {
                        Toast.makeText(MainActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
