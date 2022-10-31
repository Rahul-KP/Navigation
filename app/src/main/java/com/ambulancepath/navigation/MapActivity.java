package com.ambulancepath.navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.location.LocationListenerCompat;

import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.LocationComponentActivationOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListenerCompat {

    private FirebaseData fd;
    private MapplsMap mapplsMap;
    private MapView mapView;
    public LatLng lastKnownLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        fd = new FirebaseData();

//        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fd.sendData();
//            }
//        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, ExampleFragment.class, null)
                    .commit();
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String locationProvider = locationManager.getBestProvider(criteria, true);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            not needed
                return;
            }
            locationManager.requestLocationUpdates(locationProvider, 1000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    MapActivity.this.lastKnownLocation = new LatLng(location);
                }
            });
        }
        else {
            new AlertDialog.Builder(this)
                    .setMessage("Location not enabled, Please turn on!!!")
                    .setPositiveButton("Enable location", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
        }
    }

    public void requestCamLocChange() {
        mapplsMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLocation, 18));
    }

    @Override
    public void onMapReady(@NonNull MapplsMap mapplsMap) {
        this.mapplsMap = mapplsMap;

        mapplsMap.getStyle(new Style.OnStyleLoaded() {
            @SuppressLint("MissingPermission")
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                mapplsMap.getLocationComponent().activateLocationComponent(LocationComponentActivationOptions.builder(MapActivity.this,style).build());
                mapplsMap.getLocationComponent().setLocationComponentEnabled(true);
            }
        });
    }

    @Override
    public void onMapError(int i, String s) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}