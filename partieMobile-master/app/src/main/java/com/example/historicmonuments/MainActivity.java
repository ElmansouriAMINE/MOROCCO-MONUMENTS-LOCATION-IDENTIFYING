package com.example.historicmonuments;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.activities.CreateVilleActivity;
import com.example.historicmonuments.adapters.VillesAdapter;
import com.example.historicmonuments.dao.CrudVille;
import com.example.historicmonuments.model.Ville;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    List<Ville> villes;
    CrudVille crudVille;
    ListView listView;
    FloatingActionButton createButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        crudVille = retrofit.create(CrudVille.class);
        Call<List<Ville>> call = crudVille.getAllCities();
        call.enqueue(new Callback<List<Ville>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }

                villes =response.body();
                VillesAdapter villesAdapter =new VillesAdapter(villes,getApplicationContext());
                listView.setAdapter(villesAdapter);
                villes.forEach(v ->System.out.println(v.toString()));
            }

            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                System.out.println(t.getMessage());
            }
        });


    }

    private void callCreate() {
        Intent intent= new Intent(getApplicationContext(), CreateVilleActivity.class);
        startActivity(intent);

    }


}