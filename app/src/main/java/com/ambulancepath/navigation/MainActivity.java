package com.ambulancepath.navigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String[] PERMISSIONS_REQUIRED = {Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();
    }

    private void checkPermissions() {
        if(checkSelfPermission(PERMISSIONS_REQUIRED[0]) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, MapActivity.class));
        } else {
            requestPermissions(PERMISSIONS_REQUIRED, 100);
            checkPermissions();
        }
    }
}