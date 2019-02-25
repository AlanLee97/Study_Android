package com.test01.intentattribute;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.content.Intent.*;

public class MainActivity extends AppCompatActivity {
    private Button btnCall, btnSms, btnWeb, btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button) findViewById(R.id.btnDial);
        btnSms = (Button) findViewById(R.id.btnSms);
        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnPlay = (Button) findViewById(R.id.btnPlay);

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnDial:
                Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel://10010"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent1);
                break;
            case R.id.btnSms:
                Intent intent2 = new Intent(ACTION_SENDTO,Uri.parse("sms://10010"));
                startActivity(intent2);
                break;
            case R.id.btnWeb:
                Intent intent3 = new Intent(ACTION_VIEW,Uri.parse("http://www.baidu.com"));
                startActivity(intent3);
                break;
            case R.id.btnPlay:
                Intent intent4 = new Intent(ACTION_VIEW);
                intent4.setDataAndType(Uri.parse("file:///" +
                        Environment.getExternalStorageDirectory().getAbsolutePath()
                 + "近我者甜呀.mp3"),"audio/*");
                startActivity(intent4);
                break;

        }
    }
}
