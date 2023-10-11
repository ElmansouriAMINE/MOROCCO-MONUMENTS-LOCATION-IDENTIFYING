package com.example.historicmonuments.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.historicmonuments.About;
import com.example.historicmonuments.Dashboard;
import com.example.historicmonuments.MainActivity;
import com.example.historicmonuments.R;
import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.dao.CrudZone;
import com.example.historicmonuments.dao.DeleteZoneInterface;
import com.example.historicmonuments.dao.DeleteZoneInterface;
import com.example.historicmonuments.fragments.DeleteZoneFragment;
import com.example.historicmonuments.model.Zone;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZoneDetailActivity extends AppCompatActivity implements DeleteZoneInterface {

    TextView idText;
    TextView nomText;
    TextView longitudeText;
    TextView lattitudeText;
    Button editButton;
    Button deleteButton;

    CrudZone crudZone;
    Zone zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_detail);
        idText=findViewById(R.id.idText);
        nomText=findViewById(R.id.nomText);
        longitudeText=findViewById(R.id.longitudeText);
        lattitudeText=findViewById(R.id.lattitudeText);
        editButton=findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callEdit();
            }
        });
        int id= getIntent().getExtras().getInt("id");
        deleteButton =findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(zone.getId());
            }
        });
        getOne(id);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return;

                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0,0);
                        finish();

                        return;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0,0);
                        finish();

                        return;
                }
            }
        });

    }



    private void getOne(int id) {

        crudZone = getCrudZone();
        Call<Zone> call = crudZone.getOne(id);
        call.enqueue(new Callback<Zone>() {
            @Override
            public void onResponse(Call<Zone> call, Response<Zone> response) {

                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();

                    Log.e("Response err: ",response.message());
                    return;
                }

                zone = response.body();
                idText.setText(zone.getId()+"");
                nomText.setText(zone.getNom());
                longitudeText.setText(zone.getLongitude()+"");
                lattitudeText.setText(zone.getLattitude()+"");

            }

            @Override
            public void onFailure(Call<Zone> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ",t.getMessage());
            }
        });




    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(),EditZoneActivity.class);
        intent.putExtra("zone",zone);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteZoneFragment deleteZoneFragment = new DeleteZoneFragment("Supprimer la zone", zone.getId(),this);
        deleteZoneFragment.show(fragmentManager,"Alert");

    }

    @Override
    public void delete(int id) {
        crudZone = getCrudZone();
        Call<Zone> call = crudZone.delete(id);
        call.enqueue(new Callback<Zone>() {
            @Override
            public void onResponse(Call<Zone> call, Response<Zone> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();

                    Log.e("Response err: ",response.message());
                    return;
                }

                zone = response.body();
                Toast toast = Toast.makeText(getApplicationContext(),"Zone suprimée!!",Toast.LENGTH_LONG);
                toast.show();
                callMain();



            }

            @Override
            public void onFailure(Call<Zone> call, Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ",t.getMessage());

            }
        });

    }

    private CrudZone getCrudZone(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudZone = retrofit.create(CrudZone.class);

        return crudZone;

    }

    private void callMain(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }


}
