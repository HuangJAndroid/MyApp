package com.huangj.myapp.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.huangj.myapp.MyApplication;

/**
 * Created by Administrator on 2017/3/14.
 */

public class DrawableUtils {


    public static GradientDrawable createShape(int color){
        GradientDrawable drawable=new GradientDrawable();
        drawable.setCornerRadius(dip2px(5));//设置4个角的弧度
        drawable.setColor(color);// 设置颜色
        return drawable;


    }

    public static StateListDrawable createSelectorDrawable(Drawable pressedDrawable, Drawable normalDrawable){
//		<selector xmlns:android="http://schemas.android.com/apk/res/android"  android:enterFadeDuration="200">
//	    <item  android:state_pressed="true" android:drawable="@drawable/detail_btn_pressed"></item>
//	    <item  android:drawable="@drawable/detail_btn_normal"></item>
//	</selector>
        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);// 按下显示的图片
        stateListDrawable.addState(new int[]{}, normalDrawable);// 抬起显示的图片
        return stateListDrawable;
    }

    public static int dip2px(int dip) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
