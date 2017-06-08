package com.huangj.myapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huangj.myapp.R;
import com.huangj.myapp.fragment.HomeFragment;
import com.huangj.myapp.fragment.OneFragment;
import com.huangj.myapp.fragment.ThreeFragment;
import com.huangj.myapp.fragment.TwoFragment;
import com.huangj.myapp.view.lazylayout.LoadingPage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 88;
    private DrawerLayout mDrawerLayout;
    private TextView main_tv_login,main_tv_login2;
    private SlidingMenu mSlidingMenu;
    private RadioGroup radioGroup;

    private FragmentManager mFragmentManager;
    private HomeFragment homeFragment;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;

    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                                              Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                                              new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                              MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }else {
            //已申请了权限
        }

        LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public View createSuccessView() {
                return MainActivity.this.createSuccessView();
            }

            @Override
            protected LoadResult load() {
                return MainActivity.this.load();
            }
        };
        loadingPage.show();
        setContentView(loadingPage);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //允许申请了权限
                } else {
                    Toast.makeText(this,"无法读取图片",Toast.LENGTH_LONG).show();
                }

                break;
        }

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    public void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (oneFragment != null) {
            transaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            transaction.hide(threeFragment);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_HOME) {
             //不做任何操作
             }if (keyCode == KeyEvent.KEYCODE_MENU) {

            }
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return true;
    }


    public View createSuccessView(){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        main_tv_login = (TextView) view.findViewById(R.id.main_tv_login);
        main_tv_login2 = (TextView) view.findViewById(R.id.main_tv_login2);
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        mFragmentManager = getSupportFragmentManager();
        if (homeFragment==null){
            homeFragment = new HomeFragment();
        }
        mFragmentManager.beginTransaction().add(R.id.frameLayout,homeFragment).commit();

        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.RIGHT);
        mSlidingMenu.setMenu(R.layout.fragment_slide);
        mSlidingMenu.setBehindWidth((getWindowManager().getDefaultDisplay().getWidth())/2);
        mSlidingMenu.setFadeDegree(0.6f);
        mSlidingMenu.setBehindScrollScale(0.6f);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_WINDOW);
        main_tv_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlidingMenu.showSecondaryMenu();
            }
        });
        mSlidingMenu.findViewById(R.id.slide_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"slide点击成功",Toast.LENGTH_SHORT).show();
            }
        });
        main_tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
                mDrawerLayout.setFocusable(true);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                hideFragment(transaction);
                switch (i){
                    case R.id.button_home:
                        if (homeFragment!=null&&homeFragment.isAdded()){
                            transaction.show(homeFragment);
                        }else {
                            homeFragment = new HomeFragment();
                            transaction.add(R.id.frameLayout,homeFragment);
                        }
                        break;
                    case R.id.button_one:
                        if (oneFragment!=null&&oneFragment.isAdded()){
                            transaction.show(oneFragment);
                        }else {
                            oneFragment = new OneFragment();
                            transaction.add(R.id.frameLayout,oneFragment);
                        }
                        break;
                    case R.id.button_two:
                        if (twoFragment!=null&&twoFragment.isAdded()){
                            transaction.show(twoFragment);
                        }else {
                            twoFragment = new TwoFragment();
                            transaction.add(R.id.frameLayout,twoFragment);
                        }
                        break;
                    case R.id.button_three:
                        if (threeFragment!=null&&threeFragment.isAdded()){
                            transaction.show(threeFragment);
                        }else {
                            threeFragment = new ThreeFragment();
                            transaction.add(R.id.frameLayout,threeFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
        return view;
    }


    public LoadingPage.LoadResult load(){
        for (int i = 0; i < 4; i++) {
            data.add(""+i+i);
        }
        if(data.size() == 0){
            return LoadingPage.LoadResult.error;
        }else{
            return LoadingPage.LoadResult.success;
        }
    }
}
