package com.test01.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv);

        //1、准备数据源
        final String[] cities = getResources().getStringArray(R.array.cities);

        //2、将数据源加载到适配器中
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,cities);

        //3、将适配器中的数据加载到控件中
        listView.setAdapter(adapter);


        //表示当ListView控件中每项被点击的监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //表示当ListView中被点击的回调方法
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //1.从数据源中获取
                String s1 = cities[i];
                //2.从适配器中获取
                String s2 = adapter.getItem(i);
                //3.在parent中获取
                //String s3 = parent.getItemAtPosition(i).toString();
                //4.在ListView控件中获取
                String s4 = listView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, s1, Toast.LENGTH_SHORT).show();
            }
        });
        
        //ListView长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //如果返回false表示不处理事件，返回true,处理事件
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "触发了长按事件", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
