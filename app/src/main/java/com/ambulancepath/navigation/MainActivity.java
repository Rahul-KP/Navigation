package com.ambulancepath.navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String[] PERMISSIONS_REQUIRED = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();
    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.quit:
                System.exit(0);
                return true;
            case R.id.skip:
                Toast.makeText(this, "Loading Map", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MapActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            startActivity(new Intent(this, MapActivity.class));
        } else {
            Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissions() {
        if(checkSelfPermission(PERMISSIONS_REQUIRED[0]) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(PERMISSIONS_REQUIRED[1]) == PackageManager.PERMISSION_GRANTED) {
//            startActivity(new Intent(this, MapActivity.class));
        } else {
            requestPermissions(PERMISSIONS_REQUIRED, 100);
        }
    }
}