package com.huangj.myapp.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huangj.myapp.R;
import com.huangj.myapp.activity.CircleLockActivity;
import com.huangj.myapp.activity.InputActivity;
import com.huangj.myapp.activity.IrregularityActivity;
import com.huangj.myapp.activity.MyPreferenceActivity;
import com.huangj.myapp.activity.ScratchActivity;
import com.huangj.myapp.activity.SlidingActivity;
import com.huangj.myapp.activity.StellarMapActivity;
import com.huangj.myapp.activity.WaterActivity;
import com.huangj.myapp.activity.WebViewActivity;
import com.huangj.myapp.activity.ZhuanPanActivity;
import com.huangj.myapp.adapter.TagAdapter;
import com.huangj.myapp.view.flowtag.FlowTagLayout;
import com.huangj.myapp.view.flowtag.OnTagClickListener;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    private View view;
    @ViewInject(R.id.flowTagLayout)
    FlowTagLayout flowTagLayout;
    @ViewInject(R.id.one_iv)
    private ImageView one_iv;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);
        x.view().inject(this,view);

        final Class[] activityClass = new Class[]{ MyPreferenceActivity.class, WebViewActivity.class,ZhuanPanActivity.class,
                SlidingActivity.class, ScratchActivity.class, StellarMapActivity.class,IrregularityActivity.class,
                InputActivity.class, WaterActivity.class, CircleLockActivity.class};


        Glide.with(this).load("http://upload.17u.net/uploadpicbase/2012/02/13/ad/2012021316452454712.jpg").centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(one_iv);
//        x.image().bind(one_iv,"http://upload.17u.net/uploadpicbase/2012/02/13/ad/2012021316452454712.jpg",new ImageOptions.Builder().build());

        List<String> str = new ArrayList<>();
        str.add("对话框");
        str.add("PreferenceActivity");
        str.add("WebView");
        str.add("大转盘");
        str.add("炫侧滑");
        str.add("刮刮卡");
        str.add("随机位置");
        str.add("无规则图片");
        str.add("输入框在键盘上面");
        str.add("水波纹seekbar");
        str.add("解锁");

        TagAdapter<String> tagAdapter = new TagAdapter<>(getActivity());
        flowTagLayout.setAdapter(tagAdapter);
//        FlowTagLayout.FLOW_TAG_CHECKED_SINGLE
        tagAdapter.onlyAddAll(str);
        flowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
             if (position == 0){
                //5.0以上的对话框因为父类的theme背景的.9图四周 边距太宽的原因，和宽度设置无关。其实是填满了的，只是四周是透明的而已
                //自定义style，继承Theme.Dialog，重写背景属性  R.style.dialog
                Dialog dialog = new Dialog(getActivity(), R.style.dialog);
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_scratch, null);
                dialog.setContentView(view1);
                //返回当前的窗体对象
                Window win = dialog.getWindow();
                //设置距离屏幕的边距为0
                win.getDecorView().setPadding(0, 0, 0, 0);
                //获取窗体的所有属性
                WindowManager.LayoutParams lp = win.getAttributes();
                //设置宽和高
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = 500;
                //将修改的内容给当前窗体进行设置
                win.setAttributes(lp);
                //设置窗体弹出和退出的动画(用style描述)
//                win.setWindowAnimations(R.style.DialogAnimation);
                dialog.show();

              }else {
                 startActivity(new Intent(getActivity(),activityClass[position-1]));
             }
            }
        });

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),InputActivity.class);
                intent.putExtra("str","String");
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_FIRST_USER) {

            if (resultCode == RESULT_CANCELED) {
                Bundle bundle = data.getExtras();
                Log.e("======OneOnactivity",bundle.getString("str"));
            }
        }
    }
}
