package com.huangj.myapp;

import android.app.Application;
import android.content.Context;

import org.xutils.x;


/**
 * Created by tjj on 2016/6/5.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }


}
