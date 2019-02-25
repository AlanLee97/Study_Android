package com.test01.gridviewcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class ResultActivity extends Activity {
    private GridView gridView;
    private List<Map<String,Object>> list;
    private int[] images = {
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a4,
                R.mipmap.a5,
                R.mipmap.a6,
                R.mipmap.a7,
                R.mipmap.a8,
                R.mipmap.a9,
                R.mipmap.a10
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //1.准备数据源
        gridView = (GridView)findViewById(R.id.gv);
        list = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < images.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("img",images[i]);
            map.put("text","头像" + i);
            list.add(map);
        }

        //
        MyBaseAdapter adapter = new MyBaseAdapter();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("imageId",images[i]);//获取当前点击头像的资源id
                setResult(Activity.RESULT_OK,intent);//将资源id存储到intent中进行回传
                ResultActivity.this.finish();
            }
        });


    }

    class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(view == null){
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.grid_view,null);
                holder = new ViewHolder();
                holder.imageView = (ImageView)view.findViewById(R.id.gv_iv);
                holder.textView = (TextView)view.findViewById(R.id.gv_tv);
                view.setTag(holder);
            }else {
                holder = (ViewHolder)view.getTag();
            }
            holder.textView.setText((CharSequence)list.get(i).get("text"));
            holder.imageView.setImageResource((Integer)list.get(i).get("img"));
            return view;
        }
    }
    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
