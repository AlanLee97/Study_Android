package com.exercise01.contextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tv);
        //注册上下文控件对象
        registerForContextMenu(textView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Toast.makeText(MainActivity.this, "获取控件宽度", Toast.LENGTH_SHORT).show();
                setTitle("文本宽度 = " + textView.getWidth());
                break;
            case R.id.menu2:
                Toast.makeText(MainActivity.this, "获取控件高度", Toast.LENGTH_SHORT).show();
                setTitle("文本宽度 = " + textView.getHeight());
                break;

            default:
                break;
        }


        return super.onContextItemSelected(item);
    }
}
