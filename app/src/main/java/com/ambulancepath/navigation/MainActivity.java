package com.ambulancepath.navigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public class MainActivity extends AppCompatActivity {

    private final String[] PERMISSIONS_REQUIRED = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrimaryDrawerItem primaryDrawerItem = new PrimaryDrawerItem();
        primaryDrawerItem.setName(new StringHolder(R.string.item1));

        Slider slider = (Slider) findViewById(R.id.slider);
//        slider.
//        checkPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, MapActivity.class));
        } else {
            Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissions() {
        if(checkSelfPermission(PERMISSIONS_REQUIRED[0]) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(PERMISSIONS_REQUIRED[1]) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, MapActivity.class));
        } else {
            requestPermissions(PERMISSIONS_REQUIRED, 100);
        }
    }
}