package com.huangj.myapp;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import org.xutils.x;


/**
 * Created by tjj on 2016/6/5.
 */
public class MyApplication extends Application {
    public static MyApplication application;
    private static int mainTid;
    private static Handler handler;
    @Override
//  在主线程运行的
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        application=this;
        mainTid = android.os.Process.myTid();
        handler=new Handler();
    }
    public static Context getApplication() {
        return application;
    }
    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }
}
