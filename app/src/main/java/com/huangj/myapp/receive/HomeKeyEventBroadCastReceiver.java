package com.huangj.myapp.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 一般在开发中，我们无法直接在活动中收到用户点击Home返回这样的操作回调。
 * 但多数情况下，我们开发的应用是需要感知用户离开的状态的。这里我们可以利用
 * 广播来做这件事情。有这样一个动态广播来做监听。
 */

public class HomeKeyEventBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
            String reason = intent.getStringExtra("reason");
            if (reason != null) {
                if (reason.equals("homekey")) {
                    Toast.makeText(context, "HomeKey", Toast.LENGTH_SHORT).show();
                }else if (reason.equals("recentapps")){
                    Toast.makeText(context, "长按home键或者点击任务键", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
