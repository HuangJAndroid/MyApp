package com.huangj.myapp.activity;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;

import com.huangj.myapp.R;
import com.huangj.myapp.view.CircularFillableLoaders;
import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class WaterActivity extends AppCompatActivity {

    @ViewInject(R.id.circularFillableLoaders)
    private CircularFillableLoaders circularFillableLoaders;
    @ViewInject(R.id.seekBarProgress)
    private DiscreteSeekBar seekBarProgress;
    @ViewInject(R.id.seekBarBorderWidth)
    private DiscreteSeekBar seekBarBorderWidth;
    @ViewInject(R.id.seekBarAmplitude)
    private DiscreteSeekBar seekBarAmplitude;
    @ViewInject(R.id.shadeslider)
    private LobsterShadeSlider shadeslider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        x.view().inject(this);

        seekBarProgress.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                circularFillableLoaders.setProgress(value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarBorderWidth.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                circularFillableLoaders.setBorderWidth(value * getResources().getDisplayMetrics().density);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });

        seekBarAmplitude.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                circularFillableLoaders.setAmplitudeRatio((float) value / 1000);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });

        shadeslider.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                circularFillableLoaders.setColor(color);
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
            }
        });

    }


}
