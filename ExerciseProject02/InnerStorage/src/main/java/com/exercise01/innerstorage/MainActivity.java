package com.exercise01.innerstorage;

import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText editText_name,editText_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){
        editText_name = (EditText)findViewById(R.id.et_name);
        editText_content = (EditText)findViewById(R.id.et_content);
    }

    public void saveFile(View view){
        String fileName = editText_name.getText().toString().trim();
        String content = editText_content.getText().toString().trim();
        if(TextUtils.isEmpty(fileName) || TextUtils.isEmpty(content)){
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName,MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            editText_content.setText("");
            editText_name.setText("");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFile(View view){
        String fileName = editText_name.getText().toString().trim();
        if(TextUtils.isEmpty(fileName)){
            return;
        }
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            editText_content.setText(new String(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(View view){
        String fileName = editText_name.getText().toString().trim();
        if(TextUtils.isEmpty(fileName)){
            return;
        }
        boolean deleteFile = deleteFile(fileName);
        if(deleteFile){
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            editText_content.setText("");
            editText_name.setText("");
        }

    }
}
