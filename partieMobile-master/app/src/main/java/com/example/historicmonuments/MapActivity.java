package com.example.historicmonuments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    Location locations = new Location("");
    Location currentLocation;
    Spinner spType;
    Button btFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0,currentLong = 0;
    Bundle extras ;
    Circle circle;
    String name = null;
    int size = 0;




    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return size;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);



        //Assign variable
        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        //Initialize array of place type
        String[] placeTypeList = {"monument","historique"};
        //Initialize array of place name
        String[] placeNameList = {"Monument","historique"};

        //set adapter on spinner
        spType.setAdapter(new ArrayAdapter<>(MapActivity.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));
        //Initialize fused location

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //Check permission
        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted
            //call method
            getCurrentLocation();
        }else{
            //when permission denied
            ActivityCompat.requestPermissions(MapActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i =spType.getSelectedItemPosition();
                extras = getIntent().getExtras();

                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+ //url
                        +currentLat +","+currentLong+ //location latitude and longitude
                        "&radius="+extras.getInt("radius")+    //nearby radisu
                        "&keyword="+placeTypeList[i]+ //place type
                        "&key="+getResources().getString(R.string.google_map_key);
                Log.d("url",url);


                //Excute place task method to download json data
                new PlaceTask().execute(url);

            }
        });
    }

    private void getCurrentLocation() {
        //Initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when success
                if(location !=null){
                    //when location  is not equal to null
                    //Get current latitude
                    currentLat = 33.2568;//location.getAltitude();
                    currentLong = -8.5088; //location.getLongitude();


                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            map = googleMap;
                            extras = getIntent().getExtras();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                circle = map.addCircle(new CircleOptions()
                                        .center(new LatLng(33.2568, -8.5088))
                                        .fillColor(getColor(R.color.primary_opaque))
                                        .strokeColor(getColor(R.color.primary))
                                        .radius(extras.getInt("radius")));
                            }


                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat,currentLong),10
                            ));

                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    currentLocation = new Location("");
                                    currentLocation.setLatitude(33.2568);
                                    currentLocation.setLongitude(-8.5088);


                                    Log.d("locationnnnnnnnnn", String.valueOf(currentLocation.distanceTo(locations)));

                                    locations.setLatitude(marker.getPosition().latitude);
                                    locations.setLongitude(marker.getPosition().longitude);
                                    float distance = currentLocation.distanceTo(locations);
                                    Intent i = new Intent(getApplicationContext(),LandMarkInfo.class);
                                    i.putExtra("name",marker.getTitle());
                                    i.putExtra("position",distance);
                                    i.putExtra("id",getSize());
                                    i.putExtra("longitude",marker.getPosition().longitude);
                                    i.putExtra("lattitude",marker.getPosition().latitude);

                                    Log.d("idddddddddddddddddd", String.valueOf(getSize()));
                                    startActivity(i);



                                    return  false;
                                }
                            });

                        }
                    });

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //when permission granted
                //call method
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            //Initialize data
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            //Execute parser task
            new ParserTask().execute(s);
        }
    }
    private String downloadUrl(String string) throws IOException {
        URL url = new URL(string);
        //initialize connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line="";

        while ((line=reader.readLine())!= null){
            //Append
            builder.append(line);

        }
        String data = builder.toString();

        reader.close();

        return data;
    }

    private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>>{

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            //Create json  parser class
            JsonParser jsonParser = new JsonParser();
            //Initialize hash map list
            List<HashMap<String,String>> mapList=null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                //Parse json object
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            map.clear();

            setSize(hashMaps.size());
            for(int i = 0; i<hashMaps.size(); i++){
                HashMap<String,String> hashMapList = hashMaps.get(i);
                double lat = Double.parseDouble(hashMapList.get("lat"));
                //GET longitude
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");
                LatLng latlng = new LatLng(lat,lng);
                MarkerOptions options = new MarkerOptions();
                options.position(latlng);
                options.title(name);



                map.addMarker(options);
                Log.d("tesssst", String.valueOf(options.title(name)));
            }
        }
    }
}