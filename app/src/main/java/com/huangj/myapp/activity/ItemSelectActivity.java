package com.huangj.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.huangj.myapp.R;
import com.huangj.myapp.adapter.MySelectAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemSelectActivity extends AppCompatActivity {

   ListView listview;
    List<String> list = new ArrayList<>();
    List<String> list1 = new ArrayList<>();
    private MySelectAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item);

        listview = (ListView) findViewById(R.id.listview);
        for (int i = 0; i < 16; i++) {
            list.add(i,"ITEM"+i);
        }

        adapter = new MySelectAdapter(this,list);
        listview.setAdapter(adapter);
//        adapter.setList(list);

    }
}
