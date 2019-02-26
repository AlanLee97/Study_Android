package com.exercise01.customdialog;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View customView = getLayoutInflater().inflate(R.layout.custom_title,null);
        TextView textView = (TextView)customView.findViewById(R.id.title_tv);
        ImageView imageView = (ImageView)customView.findViewById(R.id.title_iv);

        textView.setText("自定义的标题");

        builder.setCustomTitle(customView);

        View contentView = getLayoutInflater().inflate(R.layout.custom_content,null);
        builder.setView(contentView);
        builder.setPositiveButton("确定",null);
        builder.setNegativeButton("取消",null);
        builder.show();
    }
}
