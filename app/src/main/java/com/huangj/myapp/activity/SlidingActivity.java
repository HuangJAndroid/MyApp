package com.huangj.myapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huangj.myapp.R;
import com.huangj.myapp.view.myslide5.SlideSideMenuTransitionLayout;

import org.xutils.x;


public class SlidingActivity extends AppCompatActivity {

    private SlideSideMenuTransitionLayout mSlideSideMenu;

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);
        x.view().inject(this);

        // Grab the widget
        mSlideSideMenu = (SlideSideMenuTransitionLayout)findViewById(R.id.slide_side_menu);
        //数值越大侧滑布局宽度越小
//        mSlideSideMenu.setContentPeekDistancePercent(0.8f);
        //数值越小内容布局比例越小
//        mSlideSideMenu.setContentPeekSizePercent(0.8f);
        //侧滑动画的效果
//        mSlideSideMenu.setMenuSizePercent(1.9f);
        //关闭侧滑的时间
//        mSlideSideMenu.setAnimationDuration(6000);
        // Setup the toolbar
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);

        // Wire SideMenu with Toolbar
        mToolbar.setNavigationIcon(R.mipmap.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideSideMenu.toggle();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mSlideSideMenu != null && mSlideSideMenu.closeSideMenu()) {
            // Closed the side menu, override the default back pressed behavior
            return;
        }
        super.onBackPressed();
    }
}
