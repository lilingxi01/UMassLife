package cn.tacitech.umasslife.Fragments;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import cn.tacitech.umasslife.R;

public class ExplorationFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,OnMyLocationButtonClickListener,OnMyLocationClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

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

        getLocationPermission();
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

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
        this.mMap = googleMap;

        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getActivity(),R.raw.google_style);
        mMap.setMapStyle(mapStyleOptions);
        double lng=0;
     double lat=0;
        LatLng campusCenterLoc = new LatLng(42.391706, -72.527121);
        LatLng libraryLoc = new LatLng(42.3897,-72.528356);
        LatLng HaigisMallLoc = new LatLng(42.386,-72.525);
        LatLng LgrcLoc = new LatLng(42.394397,-72.527195);

       // GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
        // move the camera to specific location
        //case didn't get location permission
        if(mLocationPermissionGranted==false) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(campusCenterLoc));
            //add marker to specific location
            mMap.addMarker(new MarkerOptions().position(campusCenterLoc).title("Campus Center")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campusCenterLoc, 12));
        }else {
            // mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            getDeviceLocation();
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
            //default zoom in the location
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng), 12));
            //add some building marker into map
            mMap.addMarker(new MarkerOptions().position(libraryLoc).title("Library")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round)));
            mMap.addMarker(new MarkerOptions().position(campusCenterLoc).title("Campus Center")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
            mMap.addMarker(new MarkerOptions().position(HaigisMallLoc).title("HagisMall")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
            mMap.addMarker(new MarkerOptions().position(LgrcLoc).title("Lederle Graduate Research Center")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    boolean mLocationPermissionGranted=false;
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */

        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
             mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private Location mLastKnownLocation;

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            // TODO Permission 判定 missed
    if(mLocationPermissionGranted==true){
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


    }else{

        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mLastKnownLocation = null;
        getLocationPermission();
    }
            Task locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        mLastKnownLocation = (Location) task.getResult();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(mLastKnownLocation.getLatitude(),
                                        mLastKnownLocation.getLongitude()), 15));
                    } else {
                        // Log.d(TAG, "Current location is null. Using defaults.");
                        // Log.e(TAG, "Exception: %s", task.getException());
                        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 1));
                        mMap.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                }
            });
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        getDeviceLocation();
    }
}
