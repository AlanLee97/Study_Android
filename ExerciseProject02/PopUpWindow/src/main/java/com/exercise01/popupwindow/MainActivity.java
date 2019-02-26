package com.exercise01.popupwindow;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View contentView;
    private PopupWindow popupWindow;
    private ImageView iv_record,iv_favor,iv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPopupWindow();
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.record:
                Toast.makeText(this, "记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favor:
                Toast.makeText(this, "喜好", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if(popupWindow != null){
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    private void initPopupWindow(){
        contentView = getLayoutInflater().inflate(R.layout.content_layout,null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
    }

    public void initView(){
        iv_record = (ImageView)findViewById(R.id.record);
        iv_favor = (ImageView)findViewById(R.id.favor);
        iv_search = (ImageView)findViewById(R.id.search);

        iv_record.setOnClickListener(this);
        iv_favor.setOnClickListener(this);
        iv_search.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_MENU:
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    popupWindow.showAtLocation(contentView,Gravity.BOTTOM,0,0);
                }
                break;
            case KeyEvent.KEYCODE_BACK:
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                break;
                default:
                    break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
