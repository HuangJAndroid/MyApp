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
    int[] bitmaps;Bitmap[] bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irregularity);
        navMap = (NavMapView)findViewById(R.id.nav1);
        bitmaps =new int[] {R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
//                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7
                ,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6,R.drawable.imag7};
        bitmap = new Bitmap[bitmaps.length];

        for (int i = 0; i < bitmaps.length; i++) {
            bitmap[i] = decodeSampledBitmapFromResource(IrregularityActivity.this, bitmaps[i]);
        }
        navMap.setBitmaps(bitmap);
        navMap.setOnClickBitmapListener(new NavMapView.OnClickBitmapListener() {
            @Override
            public void ClickBitmap(int index) {
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
