package com.exercise04.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView tv_title;
    private List<ImageView> imageViewList;
    private int[] images = new int[]{
            R.drawable.vp1,
            R.drawable.vp2,
            R.drawable.vp3,
            R.drawable.vp4,
            R.drawable.vp5
    };
    private String[] title = new String[]{
            "标题1",
            "标题2",
            "标题3",
            "标题4",
            "标题5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.vp);
        tv_title = (TextView)findViewById(R.id.title);

        tv_title.setText(title[0]);

        imageViewList = new ArrayList<ImageView>();
        //数据源
        for(int i = 0; i < images.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageViewList.add(imageView);
        }

        viewPager.setAdapter(new MyAdapter());

        //设置标题与图片同步
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tv_title.setText(title[position]);
            }
        });
    }




    //适配器
    class MyAdapter extends PagerAdapter {

        //返回子视图数量
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        //判断是否需要重新生成子视图
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //产生新视图
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(imageViewList.get(position));
        }
    }
}
