package com.huangj.myapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huangj.myapp.R;
import com.huangj.myapp.view.randomLayout.StellarMap;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StellarMapActivity extends AppCompatActivity{

    @ViewInject(R.id.stellarMap)
    StellarMap stellarMap;
    List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stellar_map);
        x.view().inject(this);

        for (int i = 0; i < 99; i++) {
            mData.add(i+""+i+i);
        }
        stellarMap.setAdapter(new MyAdapter());
        //setRegularity的两个参数之必须等于getCount(),这里也就是15
        stellarMap.setRegularity(10, 16);

        //arg0: 从哪组动画开始执行
        stellarMap.setGroup(0, true);
    }
    class MyAdapter implements StellarMap.Adapter {

        @Override
        public int getGroupCount() {
            //两组动画
            return 2;
        }

        @Override
        public int getCount(int group) {
            //标签的条数
            return 26;
        }

        @Override
        public View getView(int group, final int position, View convertView) {
            TextView textView = new TextView(StellarMapActivity.this);

            int red = 30 + new Random().nextInt(210);
            int green = 30 + new Random().nextInt(210);
            int blue = 30 + new Random().nextInt(210);
            int rgb = Color.rgb(red, green, blue);
            textView.setTextColor(rgb);

            float textSize = 10 + new Random().nextInt(17);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

            //判断一下请求网络返回数据的集合,避免角标越界
            if (mData.size() > 14) {
                textView.setText(mData.get(position));
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(StellarMapActivity.this, "======"+ mData.get(position), Toast.LENGTH_SHORT).show();
                }
            });
            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            // 不断的替换上一组展示效果
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            // 不断的替换下一组展示效果
            return 1;
        }
    }
}
