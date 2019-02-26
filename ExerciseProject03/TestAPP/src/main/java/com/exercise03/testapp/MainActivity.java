package com.exercise03.testapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    TextView forgetMima,reg;
    EditText et_count, et_pwd;
    public String user = "123456";
    public String password = "123456";
    private String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button)findViewById(R.id.login);
        et_count = (EditText)findViewById(R.id.et_count);
        et_pwd = (EditText)findViewById(R.id.et_pwd);
        forgetMima = (TextView)findViewById(R.id.forgetMima);
        reg = (TextView)findViewById(R.id.reg);

        Toast.makeText(MainActivity.this, "帐号密码都是 123456", Toast.LENGTH_SHORT).show();
        //权限
        checkSelfPermission(permissions[0]);
        checkSelfPermission(permissions[1]);
        //请求权限
        requestPermissions(permissions,1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean login(View view){
        String input_user = et_count.getText().toString();
        String input_pwd = et_pwd.getText().toString();

        if(user.equals(input_user) && password.equals(input_pwd)){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,PanelActivity.class);
            startActivity(intent);
            return true;
        }

        Toast.makeText(this, "输入的信息有误，请重新输入", Toast.LENGTH_SHORT).show();
        return false;
    }

    private static class ContextCompat {
    }
}
