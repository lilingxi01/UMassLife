package cn.tacitech.umasslife.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cn.tacitech.umasslife.R;

public class ExplorationFragment extends Fragment implements OnMapReadyCallback {


    /**
     * Exploration Fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_exploration, container, false);

        MapView mMap = view.findViewById(R.id.mapView);
        mMap.onCreate(savedInstanceState);
        mMap.onResume();
        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (ConnectionResult.SUCCESS != errorCode) {
            GooglePlayServicesUtil.getErrorDialog(errorCode, getActivity(), 0).show();
        } else {
            mMap.getMapAsync(this);
        }
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = 40.73;
        double lng = -73.99;
        LatLng appointLoc = new LatLng(lat, lng);

        // 移动地图到指定经度的位置
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc));

        //添加标记到指定经纬度
        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));


    }
}
