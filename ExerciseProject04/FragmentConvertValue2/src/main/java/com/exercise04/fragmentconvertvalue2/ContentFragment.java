package com.exercise04.fragmentconvertvalue2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    private Button getValue;
    private TextView recValue;
    private String msg;

    public void setMSg(String msg){
        this.msg = msg;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content,null);
        getValue = (Button)view.findViewById(R.id.getValue);
        recValue = (TextView)view.findViewById(R.id.recValue);


        getValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msg != null){
                    recValue.setText(msg);
                }else {
                    recValue.setText("值为空");
                }
            }
        });

        return view;
    }
}
