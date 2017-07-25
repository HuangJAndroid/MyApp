package com.huangj.myapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.huangj.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class MySelectAdapter extends BaseAdapter {
    List<String> list = new ArrayList<>();
    Context context;

    public MySelectAdapter(Context context, List<String> list) {
        this.context = context;
        this.list.addAll(list) ;
    }

    public void setList(List<String> list) {
        this.list.addAll(list) ;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            Log.e("===", i+"");
            view = LayoutInflater.from(context).inflate(R.layout.item_select_item, null);
            holder = new ViewHolder();
            view.setTag(holder);
//            holder.radiobutton = (CheckBox) view.findViewById(R.id.cb_add);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Log.e("===", i+"");
        return view;
    }

    class ViewHolder{
        public CheckBox radiobutton;
    }
}
