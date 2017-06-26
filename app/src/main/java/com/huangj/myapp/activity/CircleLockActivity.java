package com.huangj.myapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huangj.myapp.R;
import com.huangj.myapp.view.LockPatternView;

public class CircleLockActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_lock);
        mTextView = (TextView) findViewById(R.id.textView);
        normal = (Button) findViewById(R.id.normal);

        final LockPatternView lockPatternView = (LockPatternView) findViewById(R.id.lock_pattern_view);
        lockPatternView.setOnLockPatternListener(new LockPatternView.OnLockPatternListener() {
            @Override
            public void onLockPatternSuccess(String pwd) {
                mTextView.setText(pwd);
            }

            @Override
            public void onLockPatterError() {
                mTextView.setText("绘制长度不够");
            }

            @Override
            public void onLockPatterStart() {
                mTextView.setText("请开始绘制");
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockPatternView.setmBitmapNormal(Color.parseColor("#ff9932CD"), Color.parseColor("#ffDA70D6"));
            }
        });
    }

}
