package com.huangj.myapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.huangj.myapp.R;
import com.huangj.myapp.utils.UiUtils;
import com.huangj.myapp.view.lazylayout.BaseFragment;
import com.huangj.myapp.view.lazylayout.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment {

   List<String> list = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.fragment_two, null);

        return view;
    }

    @Override
    public LoadingPage.LoadResult load() {
        for (int i = 0; i < 4; i++) {
            list.add(""+i+i);
        }
        return checkData(list);
    }

}
