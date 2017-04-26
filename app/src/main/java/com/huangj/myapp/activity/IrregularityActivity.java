package com.huangj.myapp.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.huangj.myapp.R;
import com.huangj.myapp.view.NavMapView;

import java.io.InputStream;

public class IrregularityActivity extends AppCompatActivity {

    NavMapView navMap;
    int[] bitmaps,bitmapss;
    Bitmap[] bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irregularity);
        navMap = (NavMapView)findViewById(R.id.nav1);
        bitmapss = new int[] {R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7};
        bitmaps =new int[] {R.drawable.part_base_frame,R.drawable.part_1_yc,R.drawable.part_2_yc,R.drawable.part_3_yc,R.drawable.part_4_yc,R.drawable.part_5_yc
                ,R.drawable.part_6_yc,R.drawable.part_7_yc,R.drawable.part_8_yc,R.drawable.part_9_yc,R.drawable.part_10_yc,R.drawable.part_11_yc
                ,R.drawable.part_13_yc,R.drawable.part_14_yc,R.drawable.part_15_yc,R.drawable.part_16_yc,R.drawable.part_17_yc
                ,R.drawable.part_18_yc,R.drawable.part_19_yc,R.drawable.part_20_yc,R.drawable.part_21_yc,R.drawable.part_22_yc
                ,R.drawable.part_12_yc,R.drawable.part_23_yc,R.drawable.part_24_yc,R.drawable.part_25_yc,R.drawable.part_26_yc
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
 };
        bitmap = new Bitmap[bitmaps.length];

        for (int i = 0; i < bitmaps.length; i++) {
            bitmap[i] = decodeSampledBitmapFromResource(IrregularityActivity.this, bitmaps[i]);
        }
        navMap.setBitmaps(bitmap);
        navMap.setOnClickBitmapListener(new NavMapView.OnClickBitmapListener() {
            @Override
            public void ClickBitmap(int index) {
//                bitmap[index]=decodeSampledBitmapFromResource(IrregularityActivity.this, bitmapss[index%6]);
//                navMap.setBitmaps(bitmap);
                Toast.makeText(IrregularityActivity.this, "======"+index, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static Bitmap decodeSampledBitmapFromResource(Context context, int resId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opts);
    }

}
