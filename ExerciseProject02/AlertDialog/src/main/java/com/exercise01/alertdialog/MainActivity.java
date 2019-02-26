package com.exercise01.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDialog();
    }

    public void initDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题").setIcon(R.drawable.smile)
                .setMessage("是否退出？").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }
        ).setNegativeButton("取消",null);
        alertDialog = builder.create();
    }

    public void onClick(View view){
        if(!alertDialog.isShowing()){
            alertDialog.show();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && (!alertDialog.isShowing())){
            alertDialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
