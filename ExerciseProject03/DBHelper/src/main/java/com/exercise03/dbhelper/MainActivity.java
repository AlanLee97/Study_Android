package com.exercise03.dbhelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    public Button insert,delete,update,query;
    public ListView listView;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        //SQLiteDatabase readableDataBase = dbHelper.getReadableDatabase();
        sqLiteDatabase = dbHelper.getWritableDatabase();

        insert = (Button)findViewById(R.id.insert);
        delete = (Button)findViewById(R.id.drop);
        update = (Button)findViewById(R.id.update);
        query = (Button)findViewById(R.id.query);
        listView = (ListView)findViewById(R.id.lv);

        String sql = "select id as _id,name from person_tb";

        //准备SimpleCursorAdapter构造方法的参数
        Cursor c = sqLiteDatabase.query("person_tb",null,
                null,null,null,null,null);
        int layout_lv = R.layout.activity_listview;
        String from[] = new String[]{"name"};
        int to[] = new int[]{R.id.tv_name};
        int flag = SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;

        //simpleCursorAdapter的使用
        simpleCursorAdapter = new SimpleCursorAdapter(this,layout_lv,c,from,to,flag);

        listView.setAdapter(simpleCursorAdapter);

    }

    //插入数据
    public void insert(View view){
        //方法1 但是无返回值  所以不知道是否成功插入数据 不推荐使用
        //String sql = "insert into person values(1,'nibuguai')";
        //sqLiteDatabase.execSQL(sql);

        //方法2
        long check = 0;
        for(int i = 1; i <= 20; i++){
            ContentValues values = new ContentValues();
            values.put("id",i);
            values.put("name","nibuguai");
            check = sqLiteDatabase.insert("person_tb",null,values);
        }

        if(check > 0){
            Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "插入数据失败", Toast.LENGTH_SHORT).show();
        }
    }

    //删除数据
    public void delete(View view){
        ContentValues contentValues = new ContentValues();

        int result = sqLiteDatabase.delete("person_tb",
                "name = ?", new String[] {"nibuguai"});
        if(result > 0){
            Toast.makeText(this, "数据删除成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "数据删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    //更新数据
    public void update(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","guaiguai");
        int result = sqLiteDatabase.update("person_tb",
                contentValues,"id = 21",null);
        if(result > 0){
            Toast.makeText(this, "数据更新成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "数据更新失败", Toast.LENGTH_SHORT).show();
        }
    }

    //查询数据
    public void query(View view){
        //方法1：
        //String sql = "SELECT * FROM persons WHERE name = ?";
        //sqLiteDatabase.rawQuery(sql,selctionArgs);

        Cursor cursor = sqLiteDatabase.query("person_tb",null,"name = ?",
                new String[] {"nibuguai"},null,null,null);

        /*使用游标读取数据，只要下一行还有数据就继续读取
        while(cursor.moveToNext()){
            int cursorIndex = cursor.getColumnIndex("name");
            String nameValue = cursor.getString(cursorIndex);
        }
        */

        //将新的cursor与旧的cursor交换
        simpleCursorAdapter.swapCursor(cursor);
        simpleCursorAdapter.notifyDataSetChanged();

    }
}
