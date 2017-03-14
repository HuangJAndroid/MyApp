package com.huangj.myapp.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ColorUtils {
    public static int randomColor(){
        Random random=new Random();
        int red = random.nextInt(200)+22;
        int green = random.nextInt(200)+22;
        int blue = random.nextInt(200)+22;
        int color= Color.rgb(red, green, blue);
        return  color;
    }
}
