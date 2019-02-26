package com.exercise04.fragmentviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public FragmentManager fragmentManager;
    public ViewPager viewPager;
    public List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        fragmentList = new ArrayList<Fragment>();
        for (int i = 1; i < 6; i++){
            ContentFragment contentFragment = ContentFragment.newInstance("第" + i + "个Fragment");
            fragmentList.add(contentFragment);
        }

        fragmentManager = getSupportFragmentManager();

        MyAdapter myAdapter = new MyAdapter(fragmentManager);
        viewPager.setAdapter(myAdapter);
    }







    class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
