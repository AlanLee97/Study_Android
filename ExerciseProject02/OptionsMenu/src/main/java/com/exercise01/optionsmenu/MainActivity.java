package com.exercise01.optionsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //菜单填充器
        MenuInflater menuInflater = getMenuInflater();
        menu.add(Menu.NONE,Menu.NONE,Menu.NONE,"新的menu项");
        //将xml文件转换成menu对象
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Toast.makeText(MainActivity.this, "菜单1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(MainActivity.this, "菜单2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(MainActivity.this, "菜单3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(MainActivity.this, "菜单4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu5:
                Toast.makeText(MainActivity.this, "菜单5", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
