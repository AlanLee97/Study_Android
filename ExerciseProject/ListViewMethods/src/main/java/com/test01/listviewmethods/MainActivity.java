package com.test01.listviewmethods;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv);
        int[] imageID = new int[]{
                R.mipmap.img01,
                R.mipmap.img02,
                R.mipmap.img03,
                R.mipmap.img04,
                R.mipmap.img05,
                R.mipmap.img06,
                R.mipmap.img07,
                R.mipmap.img08,
                R.mipmap.img09
        };

        String[] name = new String[]{
                "名字1",
                "名字2",
                "名字3",
                "名字4",
                "名字5",
                "名字6",
                "名字7",
                "名字8",
                "名字9"
        };

        //1.准备数据源
        List<Map<String,Object>> listitems = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < imageID.length; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",imageID[i]);
            map.put("名字",name[i]);
            listitems.add(map);
        }

        //2.将数据源加载到适配器中
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listitems,
                R.layout.activity_other,
                new String[]{"名字","image"},
                new int[]{R.id.name,R.id.image}
                );

        //3.将适配器加载到控件中
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Map<String,Object> map = (Map<String,Object>) adapterView.getItemAtPosition(i);
                        Toast.makeText(MainActivity.this, map.get("名字").toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }



}
