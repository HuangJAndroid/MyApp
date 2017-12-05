package com.huangj.myapp.utils;

import android.app.Activity;
import android.content.Intent;

import com.huangj.myapp.R;

/**
 * 桌面快捷方式有什么用？
 * *可以给用户一个常用功能的快捷入口（推荐）
 * *搭配插件化技术实现模拟安装后的app体验（推荐）
 * *做黑产（黑色产业链的东西我不想说了，只需要记得咱们是有原则的开发者，坚决抵制做垃圾App。即使别人给钱也不做。就这么任性　（ˇ＾ˇ〉）
 */

public class ShortCutUtils {
    /**
     *  添加当活动为启动项
     * @param cx
     * @param name 快捷方式名称
     */
    public static void addShortcut(Activity cx, String name) {
        // 创建快捷方式的intent广播
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 添加快捷名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        //  快捷图标是允许重复
        shortcut.putExtra("duplicate", false);
        // 快捷图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(cx, R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
        //  我们下次启动要用的Intent信息
        Intent carryIntent = new Intent(Intent.ACTION_MAIN);
        carryIntent.putExtra("name", name);
        carryIntent.setClassName(cx.getPackageName(),cx.getClass().getName());
        carryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //添加携带的Intent
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, carryIntent);
        //   发送广播
        cx.sendBroadcast(shortcut);
    }
}
