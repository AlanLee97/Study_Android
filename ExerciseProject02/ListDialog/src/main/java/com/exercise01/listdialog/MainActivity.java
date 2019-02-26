package com.exercise01.listdialog;

import android.content.DialogInterface;
import android.media.tv.TvContentRating;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private TextView textView;
    private int checkedItem = 0;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tv);

    }

    public void onClick(View view){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("设置字体大小");
        builder.setIcon(R.drawable.ic_launcher_background);

        dialog = builder.setSingleChoiceItems(R.array.font_text, checkedItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checkedItem = i;
                        String[] fontTexts = getResources().getStringArray(R.array.font_text);
                        setTitle(fontTexts[i]);

                        int[] fontSizes = getResources().getIntArray(R.array.font_size);
                        textView.setTextSize(fontSizes[i]);
                        dialog.dismiss();//点击之后让弹窗消失
                    }
                }
        ).create();//创建dialog
        dialog.show();//显示dialog

    }
}
