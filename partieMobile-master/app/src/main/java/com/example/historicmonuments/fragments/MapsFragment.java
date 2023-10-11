package com.example.historicmonuments.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.historicmonuments.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {
    GoogleMap mMap ;
    WindowManager windowManager;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap)  {
                    LatLng sydney = new LatLng(33.2568, -8.5088);
                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Votre position"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    mMap = googleMap;
                    //direction();


                    LatLng startPosition = new LatLng(33.2568, -8.5088);
                    LatLng endPosition = new LatLng(getArguments().getDouble("lattitude"),getArguments().getDouble("longitude"));
                    mMap.addMarker(new MarkerOptions().position(endPosition).title("destination"));
                    googleMap.addPolyline(new PolylineOptions()
                            .add(startPosition, endPosition)
                            .width(10)
                            .color(Color.BLUE));









        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }



    private void direction(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Double llongitude = getArguments().getDouble("longitude");
        Double lattitude = getArguments().getDouble("lattitude");
        String test = llongitude.toString();
        String test1 = lattitude.toString();

        String url = Uri.parse("https://maps.googleapis.com/maps/api/directions/json")
                .buildUpon()
                .appendQueryParameter("origin","33.2568, -8.5088")
                .appendQueryParameter("destination",test1+","+test)
                .appendQueryParameter("mode","driving")
                .appendQueryParameter("key","AIzaSyB7Zs0olUYd10DYNum0jRG636e2A1KPqhI")
                .toString();
        Log.d("URLLLLLLLLLLLLLLLLLLL",url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                        try {
                            String status = response.getString("status");
                            Log.d("STTTTTTTTTTTTTTT",status);

                            if(status.equals("OK")){
                                JSONArray routes = response.getJSONArray("routes");
                                ArrayList<LatLng> points;
                                PolylineOptions polylineOptions = null;

                                for(int i = 0;i<routes.length();i++){
                                    points = new ArrayList<>();
                                    polylineOptions = new PolylineOptions();
                                    JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
                                    for (int j =0;j<legs.length();j++){
                                        JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");

                                        for(int k = 0;k<steps.length();k++){
                                            String polyline = steps.getJSONObject(k).getJSONObject("polyline").getString("points");
                                            List<LatLng> list = decodePoly(polyline);

                                            for(int l=0;l<list.size();l++){
                                                LatLng position = new LatLng((list.get(l)).latitude,(list.get(l)).longitude);
                                                points.add(position);
                                                Log.d("points", String.valueOf(points));

                                            }
                                        }
                                    }

                                    polylineOptions.addAll(points);
                                    polylineOptions.width(30);
                                    polylineOptions.color(Color.RED);
                                    polylineOptions.geodesic(true);

                                }


                                mMap.addPolyline(polylineOptions);
                                mMap.addMarker(new MarkerOptions().position(new LatLng(-8.5088,33.2568)).title("votre position"));
                                mMap.addMarker(new MarkerOptions().position(new LatLng(getArguments().getDouble("lattitude"),getArguments().getDouble("longitude"))).title("destination"));
                                Log.d("wiiiiiiiiiiiii3", String.valueOf(getArguments().getDouble("lattitude")));
                                LatLngBounds bounds = new LatLngBounds.Builder()
                                        .include(new LatLng(33.2568,-8.5088))
                                        .include(new LatLng(getArguments().getDouble("lattitude"),getArguments().getDouble("longitude"))).build();
                                Point point = new Point();

                                Log.d("BOUNDDDDDDDDDSS", String.valueOf(bounds));

                                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200,100,30));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RetryPolicy retryPolicy = new DefaultRetryPolicy(3000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(retryPolicy);
        Log.d("yyyyyyyyyyyyyyyyyy", String.valueOf(jsonObjectRequest));
        requestQueue.add(jsonObjectRequest);

    }
    private List<LatLng> decodePoly(String encoded){
        List<LatLng> poly = new ArrayList<>();
        int index = 0,len = encoded.length();
        int lat = 0,lng=0;

        while(index < len){
            int b,shift = 0,result=0;
            do{
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            }while (b>=0x20);
            int dlat = ((result & 1) != 0? ~(result >> 1):(result >> 1));
            lat += dlat;

            shift = 0;
            result =0;
            do{
                b=encoded.charAt(index++) -63;
                result |= (b & 0x1f) << shift;
                shift +=5;
            }while (b > 0x20);
            int dlng = ((result & 1) != 0? ~(result >> 1):(result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat/155)),((double) lng/155));
            poly.add(p);
        }
        return poly;
    }
}