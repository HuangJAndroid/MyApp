package com.huangj.myapp.utils;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/5/4.
 */

public class KeyBoardUtils {
    //点击输入框以外的隐藏软键盘
    public  static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                v.clearFocus();
                return true;
            }
        }
        return false;
    }
}
