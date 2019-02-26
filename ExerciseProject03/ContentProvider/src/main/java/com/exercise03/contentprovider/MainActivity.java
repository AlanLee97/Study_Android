package com.exercise03.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    Button button;
    ListView listView;
    private ContentResolver contentResolver;
    private String call_log_uri = "content://call_log/calls";
    private String[] columns = new String[]{
            CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.DATE,
            CallLog.Calls.TYPE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.btn);
        listView = (ListView)findViewById(R.id.lv);

        contentResolver = getContentResolver();
    }

    public void onClick(View view) {
        Cursor cursor = contentResolver.query(Uri.parse(call_log_uri),
                                            columns,
                                            null,
                                            null,
                                            null
                                            );
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_listview,
                cursor,
                new String[]{CallLog.Calls.NUMBER,CallLog.Calls.DATE},
                new int[]{R.id.lv_number,R.id.lv_date},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        listView.setAdapter(simpleCursorAdapter);
    }
}
