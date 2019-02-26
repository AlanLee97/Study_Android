package com.exercise03.testapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PanelActivity extends Activity {
    Button takephoto,xiangce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        takephoto = (Button)findViewById(R.id.takephoto);
        xiangce = (Button)findViewById(R.id.xiangce);

        Toast.makeText(this, "请在应用权限中给该应用允许使用相机权限\n否则相机功能将不可用，并且应用会崩溃", Toast.LENGTH_LONG).show();
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.takephoto:
                String[] camera = new String[]{"系统相机","自定义相机"};
                AlertDialog.Builder alertDialog_builder = new AlertDialog.Builder(PanelActivity.this);
                alertDialog_builder.setTitle("请选择相机类型");
                alertDialog_builder.setIcon(R.drawable.icon1);
                alertDialog_builder.setSingleChoiceItems(camera, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent1 = new Intent(PanelActivity.this,SystemCameraActivity.class);
                                startActivity(intent1);
                                break;

                            case 1:
                                Intent intent2 = new Intent(PanelActivity.this,CustomCameraActivity.class);
                                startActivity(intent2);
                                break;
                        }

                    }

                });
                alertDialog_builder.setNegativeButton("取消",null);
                alertDialog_builder.create().show();
                break;

            case R.id.xiangce:
                Intent intent2 = new Intent(PanelActivity.this,AlbumActivity.class);
                startActivity(intent2);
                break;
        }
    }


}
