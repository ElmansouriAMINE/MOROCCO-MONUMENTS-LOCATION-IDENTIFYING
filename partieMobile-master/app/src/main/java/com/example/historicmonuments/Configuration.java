package com.example.historicmonuments;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Configuration extends AppCompatActivity implements OnMapReadyCallback {
    Button button_next;
    SeekBar seekBar;
    CheckBox checkBox;
    TextView myTv;
    GoogleMap map;
    MapView mapView;
    Circle circle;
    int radius = 0;
    private Thread mThread;
    private final int DURATION = 3000;



    FusedLocationProviderClient fusedLocationProviderClient;
    public void setRadius(int radius) {
        this.radius = radius;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        seekBar = findViewById(R.id.sb_radius);
        //checkBox = findViewById(R.id.cb_limit);
        myTv =findViewById(R.id.label_radius);
        mapView = (MapView) findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
//                    case R.id.home:

                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),AccueilActivity.class));
                        overridePendingTransition(0,0);
                        finish();

                        return;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        finish();

                        return;
                }
            }
        });










        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        button_next = findViewById(R.id.button_next);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                myTv.setVisibility(View.VISIBLE);
                myTv.setText(progress+"/5000");
                setRadius(progress);
                circle.setRadius(progress);





            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MapActivity.class);
                i.putExtra("radius",radius);
                startActivity(i);
                Log.d("hhhhhhhhhh", String.valueOf(radius));

            }
        });



    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            circle = map.addCircle(new CircleOptions()
                    .center(new LatLng(33.2568, -8.5088))
                    .fillColor(getColor(R.color.primary_opaque))
                    .strokeColor(getColor(R.color.primary))
                    .radius(radius));
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(33.2568,-8.5088),12
        ));


    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

}