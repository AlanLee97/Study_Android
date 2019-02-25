package com.test01.activityconvertvalues;

import android.app.Application;

/*
 *Application传递数据
 */

public class MyApplication extends Application {
    String name;
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
