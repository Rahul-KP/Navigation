package com.ambulancepath.navigation;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String[] PERMISSIONS_REQUIRED = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = MainActivity.this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        checkPermissions();
    }

    private void updatePreferences(String usertype) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_user_type), usertype);
        editor.apply();
    }

    public void checkFirstLaunch() {
        if(sharedPref.getString(getString(R.string.saved_user_type), "").equals("")) {
            findViewById(R.id.button_driver).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updatePreferences("driver");
                }
            });
            findViewById(R.id.button_user).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updatePreferences("user");
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkFirstLaunch();
//            startActivity(new Intent(this, MapActivity.class));
        } else {
            Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissions() {
        if(checkSelfPermission(PERMISSIONS_REQUIRED[0]) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(PERMISSIONS_REQUIRED[1]) == PackageManager.PERMISSION_GRANTED) {
            checkFirstLaunch();
//            startActivity(new Intent(this, MapActivity.class));
        } else {
            requestPermissions(PERMISSIONS_REQUIRED, 100);
        }
    }
}