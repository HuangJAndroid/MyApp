package com.huangj.myapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapView;
import com.huangj.myapp.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    @ViewInject(R.id.gps_tv)
    private TextView gps_tv;
    @ViewInject(R.id.gps_button)
    private Button gps_button;
    @ViewInject(R.id.gd_tv)
    private TextView gd_tv;
    @ViewInject(R.id.gd_button)
    private Button gd_button;
    @ViewInject(R.id.mapView)
    MapView mMapView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        x.view().inject(this);

        openGPSSettings();

//声明AMapLocationClient类对象
         AMapLocationClient mLocationClient = null;
//声明定位回调监听器
         AMapLocationListener mLocationListener = new AMapLocationListener() {
             @Override
             public void onLocationChanged(AMapLocation amapLocation) {
                 if (amapLocation != null) {
                     if (amapLocation.getErrorCode() == 0) {
                         //解析定位结果
                         String str = amapLocation.getAddress() + ";" + amapLocation.getAoiName() +
                                ";"+amapLocation.getCity() + ";" + amapLocation.getCountry() +
                                 ";"+amapLocation.getDescription() + ";" + amapLocation.getDistrict() +
                                 ";"+amapLocation.getFloor() + ";" + amapLocation.getLocationDetail() +
                                 ";"+amapLocation.getErrorInfo() + ";" + amapLocation.getPoiName() +
                                  ";" + amapLocation.getProvider() +
                                 ";"+amapLocation.getSpeed() + ";" + amapLocation.getSatellites() +
                                 ";"+amapLocation.getLongitude() + ";" + amapLocation.getLocationType() +
                                 ";"+amapLocation.getLatitude() +
                                 ";"+amapLocation.getProvince() + ";" + amapLocation.getStreet() +
                                 ";"+amapLocation.getStreetNum() + ";" + amapLocation.getRoad() +
                                 ";"+amapLocation.getAccuracy() + ";" + amapLocation.getAltitude() +
                                 ";"+amapLocation.getBearing() + ";" + amapLocation.getGpsAccuracyStatus();
                         gd_tv.setText(str);
                         Log.e("======",str);
                     }
                 }
             }
         };
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
//启动定位
        mLocationClient.startLocation();
//异步获取定位结果



        gps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
        RequestParams requestParams = new RequestParams("http://restapi.amap.com/v3/assistant/coordinate/convert?locations=120.12507697564229,30.340062421576214&coordsys=gps&output=json&key=04e678645ced52ce3912d75bdc5f775b");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("====",result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


        mMapView.getMap().showIndoorMap(true);
//在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        if (alm
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS模块正常 ", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent, 0); //此为设置完成后返回到获取界面
    }

    private void getLocation() {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        updateToNewLocation(location);
        // 设置监听*器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500,
                                               new LocationListener() {
                                                   @Override
                                                   public void onLocationChanged(Location location) {
                                                       updateToNewLocation(location);
                                                   }

                                                   @Override
                                                   public void onStatusChanged(String provider, int status, Bundle extras) {

                                                   }

                                                   @Override
                                                   public void onProviderEnabled(String provider) {

                                                   }

                                                   @Override
                                                   public void onProviderDisabled(String provider) {

                                                   }
                                               });  }
    private void updateToNewLocation(Location location) {
            String latLongString;
        double lat = 0;
        double lng= 0;
            if (location != null) {
                lat= location.getLatitude();
                lng= location.getLongitude();
                latLongString = "纬度:" + lat + "n经度:" + lng;

            } else {
                latLongString = "无法获取地理信息";
            }
            List<Address> addList = null;
            Geocoder ge = new Geocoder(this);
            try {
                addList = ge.getFromLocation(lat,lng, 1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(addList!=null && addList.size()>0){
                for(int i=0; i<addList.size(); i++){
                    Address ad = addList.get(i);
                    latLongString += "n";
                    latLongString += ad.getCountryName() + ";" + ad.getLocality()
                    +";"+ad.getAdminArea() + ";" + ad.getSubLocality()
                            +";"+ad.getPhone() + ";" + ad.getPremises()
                            +";"+ad.getSubAdminArea() + ";" + ad.getFeatureName()
                            +";"+ad.getSubThoroughfare() + ";" + ad.getUrl();
                }
            }
            gps_tv.setText("您当前的位置是:n" +latLongString);
    }


}
