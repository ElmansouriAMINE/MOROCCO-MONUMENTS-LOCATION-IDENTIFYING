package com.example.historicmonuments;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.adapters.MonumentsAdapater;
import com.example.historicmonuments.adapters.VillesAdapter;
import com.example.historicmonuments.dao.CrudMonument;
import com.example.historicmonuments.dao.CrudVille;
import com.example.historicmonuments.model.Monument;
import com.example.historicmonuments.model.Ville;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Monuments extends AppCompatActivity {
    ListView listView;
    List<Monument> monuments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monuments);

        listView = findViewById(R.id.listView1);

        getAll();

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
    }

    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CrudMonument crudMonument = retrofit.create(CrudMonument.class);
        Call<List<Monument>> call = crudMonument.getAllMonument();
        call.enqueue(new Callback<List<Monument>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Monument>> call, Response<List<Monument>> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }

                monuments =response.body();
                MonumentsAdapater monumentsAdapater =new MonumentsAdapater(monuments,getApplicationContext());
                listView.setAdapter(monumentsAdapater);
                monuments.forEach(v ->System.out.println(v.toString()));
            }

            @Override
            public void onFailure(Call<List<Monument>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                System.out.println(t.getMessage());
            }
        });


    }

}