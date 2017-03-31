package com.huangj.myapp.utils;

import android.content.Context;

import com.huangj.myapp.MyApplication;

/**
 * Created by Administrator on 2017/3/13.
 */

public class UiUtils {

    public static Context getContext(){
        return MyApplication.getApplication();
    }


    public static void runOnUiThread(Runnable runnable) {
        // 在主线程运行
        if(android.os.Process.myTid()==MyApplication.getMainTid()){
            runnable.run();
        }else{
            //获取handler
            MyApplication.getHandler().post(runnable);
        }
    }


}
