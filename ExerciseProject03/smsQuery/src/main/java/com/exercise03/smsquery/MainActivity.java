package com.exercise03.smsquery;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ContentResolver contentResolver;
    private String SMS_URI = "content://sms";
    private ListView listView;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //内容解析者
        contentResolver = getContentResolver();

        listView = (ListView)findViewById(R.id.listView);
    }

    public void onClick(View view) {
        cursor = contentResolver.query(Uri.parse(SMS_URI),null,null,null,null);
        if(cursor == null){
            Log.i("tag_cursor","-->");
        }
        listView.setAdapter(new MyAdapter(this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null){
            cursor.close();
        }

    }

    class MyAdapter extends CursorAdapter{

        public MyAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        //创建一个视图，引入一个listView子视图
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return getLayoutInflater().inflate(R.layout.listview,null);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView number = (TextView)findViewById(R.id.number);
            TextView content = (TextView)findViewById(R.id.content);
            TextView type = (TextView)findViewById(R.id.type);

            String strNumber = cursor.getString(cursor.getColumnIndex("address"));
            String strContent = cursor.getString(cursor.getColumnIndex("body"));
            number.setText(strNumber);
            content.setText(strContent);
            int intType = cursor.getInt(cursor.getColumnIndex("type"));
            if(intType == 1){
                type.setText("收到的信息");
            }else {
                type.setText("发送的信息");
            }


        }
    }
}
