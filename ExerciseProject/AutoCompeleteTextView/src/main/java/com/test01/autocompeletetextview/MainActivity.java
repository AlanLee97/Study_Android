package com.test01.autocompeletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    String[] words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.actv);
        //准备数据源
        words = getResources().getStringArray(R.array.words);
        //将数据源加载到适配器中
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_spinner_item,words);
        //将适配器加载到控件中
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String str = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "你当前选中中了" + str,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
