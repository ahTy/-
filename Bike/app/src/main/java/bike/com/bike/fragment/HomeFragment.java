package bike.com.bike.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

import java.util.List;

import bike.com.bike.HomeActivity;
import bike.com.bike.R;
import bike.com.bike.UserActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BDLocationListener{
    private MapView mMapView = null;
    private Button user;
    private Button location;
    private EditText search;
    private TextView getText;

    private Overlay overlay=null;  //保存上一次的位置图标
    int sj_num=4;
    private Overlay[] Sj_location=new Overlay[sj_num];//保存商家位置图标
    public LocationClient mLocationClient = null;
    LatLng point=null;
    int lotime=0;//定位次数
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 0;//默认权限
    public HomeFragment()  {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        user=(Button)view.findViewById(R.id.user);
        location=(Button)view.findViewById(R.id.location);
        search=(EditText) view.findViewById(R.id.search);
        getText=(TextView)view.findViewById(R.id.getText);
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);   //隐藏缩放
        search.setCursorVisible(false);
        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    search.setCursorVisible(true);
                }
                return false;
            }
        });
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getText.setText(getText.getText());//获取edittext的内容
                search.setText(null);
                search.setCursorVisible(false);
                return false;
            }
        });
        initLocationClient();



        //mMapView
        //compassEnabled(boolean enabled);

        mLocationClient.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    //showToast("自Android 6.0开始需要打开位置权限才可以搜索到Ble设备");
                }
                //请求权限
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_ACCESS_COARSE_LOCATION);
            } else{
                mLocationClient.start();
                //定义地图状态
                MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(18).build();
                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                //改变地图状态
                mMapView.getMap().setMapStatus(mMapStatusUpdate);
            }
        } else{
            mLocationClient.start();
            //定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(18).build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mMapView.getMap().setMapStatus(mMapStatusUpdate);
        }

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.requestLocation();

                //定义地图状态
                MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(18).build();
                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                //改变地图状态
                mMapView.getMap().setMapStatus(mMapStatusUpdate);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),UserActivity.class);
                startActivityForResult(intent,1);
            }
        });

        return view;
    }


    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            mLocationClient.stop();
        }else{
            mLocationClient.start();
            //定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(18).build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mMapView.getMap().setMapStatus(mMapStatusUpdate);
        }
        super.onHiddenChanged(hidden);
    }

    private void initLocationClient(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocationClient = new LocationClient(getContext());
        mLocationClient.registerLocationListener(this);
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span=2000;
        option.setScanSpan(span);    //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setNeedDeviceDirect(true);        //返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
    }
    //当收到位置信息时的回掉函数
    @Override
    public void onReceiveLocation(BDLocation location) {
        lotime++;
        //Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nradius : ");
        sb.append(location.getRadius());


        if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());


            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// 单位度
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\ndescribe : ");
            sb.append("gps定位成功");
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
            //调整百度地图api的网络定位偏差
            location.setLatitude(location.getLatitude() - 0.000682);

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());

            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");

            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不通导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        List<Poi> list = location.getPoiList();// POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        Log.i("BaiduLocationApiDem", sb.toString());
        //定义Maker坐标点
        point = new LatLng(location.getLatitude(),location.getLongitude());

        double[] my_Latitude=new double[sj_num];
        my_Latitude[0]= 37.481678;
        my_Latitude[1]=37.488923;
        my_Latitude[2]=37.489037;
        my_Latitude[3]=37.485509;

        double[] my_Longitude=new double[sj_num];
        my_Longitude[0]=121.458454;
        my_Longitude[1]=121.462652;
        my_Longitude[2]=121.455789;
        my_Longitude[3]=121.463069;

        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.lc);
        BitmapDescriptor sj_bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.sj);

        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);


        if(lotime<3){
            //定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(18).build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mMapView.getMap().setMapStatus(mMapStatusUpdate);
        }

        for(int i=0;i<sj_num;i++){
            //手动添加商家位置
            LatLng sj_point = new LatLng(my_Latitude[i],my_Longitude[i]);
            OverlayOptions sj_option = new MarkerOptions().position(sj_point).icon(sj_bitmap);
            Sj_location[i]=mMapView.getMap().addOverlay(sj_option);
        }

        if(overlay!=null){
            overlay.remove();
            overlay=null;
        }
        overlay= mMapView.getMap().addOverlay(option);
    }
    @Override
    public void onConnectHotSpotMessage(String s, int i) {
        System.out.print(s);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户允许改权限，0表示允许，-1表示拒绝 PERMISSION_GRANTED = 0， PERMISSION_DENIED = -1
                //permission was granted, yay! Do the contacts-related task you need to do.
                //这里进行授权被允许的处理
                mLocationClient.start();
            } else {
                //permission denied, boo! Disable the functionality that depends on this permission.
                //这里进行权限被拒绝的处理
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}