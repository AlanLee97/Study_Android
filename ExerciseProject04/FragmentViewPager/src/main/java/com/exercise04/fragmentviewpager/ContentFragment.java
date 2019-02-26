package com.exercise04.fragmentviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContentFragment extends Fragment {
    static ContentFragment newInstance(String msg){
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("msg",msg);
        contentFragment.setArguments(args);
        return contentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content,null);
        TextView textView = (TextView)view.findViewById(R.id.word);

        Bundle argments = getArguments();
        if(argments != null){
            String msg = argments.getString("msg","默认值");
            textView.setText(msg);
        }

        return view;
    }
}
