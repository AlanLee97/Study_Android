package com.test01.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    String[] type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayAdapter<String> adapter;

        spinner = (Spinner)findViewById(R.id.spinner);
        type = getResources().getStringArray(R.array.mySpinner);
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,type);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String s1 = type[position];//根据下标在数据源中获取
                String s2 = adapter.getItem(position);//根据下标在适配器中获取
                String s3 = spinner.getItemAtPosition(position).toString();//根据下标在spinner控件中获取

                Toast.makeText(MainActivity.this, "你选择了" + s1 + "\n" + s2 + "\n" + s3,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
