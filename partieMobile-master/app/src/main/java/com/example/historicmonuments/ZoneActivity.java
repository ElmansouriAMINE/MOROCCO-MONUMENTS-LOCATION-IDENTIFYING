package com.example.historicmonuments;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.activities.CreateZoneActivity;
import com.example.historicmonuments.adapters.ZonesAdapter;
import com.example.historicmonuments.dao.CrudZone;
import com.example.historicmonuments.model.Zone;
import com.example.historicmonuments.model.Zone;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZoneActivity extends AppCompatActivity {
    List<Zone> zones;
    CrudZone crudZone;
    ListView listView;
    FloatingActionButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);
        listView = findViewById(R.id.listView);
        createButton =findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCreate();
            }
        });


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

        getAll();

    }



    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudZone = retrofit.create(CrudZone.class);
        Call<List<Zone>> call = crudZone.getAllZones();
        call.enqueue(new Callback<List<Zone>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }

                zones =response.body();
                ZonesAdapter zonesAdapter =new ZonesAdapter(zones,getApplicationContext());
                listView.setAdapter(zonesAdapter);
                zones.forEach(v ->System.out.println(v.toString()));
            }

            @Override
            public void onFailure(Call<List<Zone>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                System.out.println(t.getMessage());
            }
        });


    }

    private void callCreate() {
        Intent intent= new Intent(getApplicationContext(), CreateZoneActivity.class);
        startActivity(intent);

    }




//    fin
}