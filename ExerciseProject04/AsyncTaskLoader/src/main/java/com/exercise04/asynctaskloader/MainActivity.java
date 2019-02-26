package com.exercise04.asynctaskloader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

//1.实现LoaderManager.LoaderCallbacks<Cursor>接口
public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor> {
    private ListView listView;
    SimpleCursorAdapter simpleCursorAdapter;
    String[] from = new String[] {"_id","display_name"};
    int[] to = new int[]{R.id.tv_id,R.id.tv_name};
    int flag = SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;
    private static ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);
        simpleCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.listview, null, from,to,flag
        );
        listView.setAdapter(simpleCursorAdapter);

        contentResolver = getContentResolver();

        //2.初始化Loader
        getLoaderManager().initLoader(1,null,  this);

    }


    //3.创建一个Loader对象
    @NonNull
    @Override
    public android.content.Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new android.content.Loader<Cursor>(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);
    }

    static class MyLoader extends AsyncTaskLoader<Cursor>{

        public MyLoader(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();

            //强制加载
            forceLoad();
        }

        @Nullable
        @Override
        public Cursor loadInBackground() {//在后台线程中进行数据的加载   耗时操作
            String[] lie = new String[]{"_id","display_name"};
            contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,lie,null,null,null);


            return null;
        }
    }
}
