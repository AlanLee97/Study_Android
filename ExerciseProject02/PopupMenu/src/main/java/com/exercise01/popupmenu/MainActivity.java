package com.exercise01.popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private float FontSize = 15.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tv);
        textView.setTextSize(FontSize);
    }

    public void onClick(View view){

        PopupMenu popupMenu = new PopupMenu(this,view);//参数2：锚点
        getMenuInflater().inflate(R.menu.menu_main,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu1:
                                FontSize += 5;
                                break;
                            case R.id.menu2:
                                FontSize -= 5;
                                break;
                        }
                        textView.setTextSize(FontSize);
                        return false;
                    }
                }
        );
    }
}
