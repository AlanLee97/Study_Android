package com.test01.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Map<String,Object>> list;
    private int[] images = new int[]{
            R.mipmap.a1,
            R.mipmap.a2,
            R.mipmap.a3,
            R.mipmap.a4,
            R.mipmap.a5,
            R.mipmap.a6,
            R.mipmap.a7,
            R.mipmap.a8,
            R.mipmap.a9,
            R.mipmap.a10
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv);
        //1.准备数据源
        list = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < images.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("img",images[i]);
            map.put("name","名字" + i);
            list.add(map);
        }

        //2.将数据加载到适配器中
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                MainActivity.this,  //参数1(context)：上下文对象
                list,   //参数2(data)：表示加载到适配器的数据源对象
                R.layout.list_item,     //参数3(resource)：表示simpleAdapter控件中每项布局资源id
                new String[]{"img","name"},     //参数4(from)：表示数据源map中key的数组  表示key指定的数值
                new int[]{R.id.imageView,R.id.textView} //参数5(to)：表示需要展示对应数据的控件资源id
                //通过from和to的对应 将from中的key对应的数据指定的值显示到to指定资源id的控件中
        );

        //3.将适配器加载到控件中
        listView.setAdapter(simpleAdapter);


    }
}
