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
import com.example.historicmonuments.dao.CrudVille;
import com.example.historicmonuments.dao.DeleteVilleInterface;
import com.example.historicmonuments.fragments.DeleteVilleFragment;
import com.example.historicmonuments.model.Ville;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VilleDetailActivity extends AppCompatActivity implements DeleteVilleInterface {

    TextView idText;
    TextView nomText;
    TextView paysText;
    TextView longitudeText;
    TextView lattitudeText;
    Button editButton;
    Button deleteButton;

    CrudVille crudVille;
    Ville ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville_detail);
        idText=findViewById(R.id.idText);
        nomText=findViewById(R.id.nomText);
        paysText=findViewById(R.id.paysText);
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
                showDeleteDialog(ville.getId());
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

        crudVille = getCrudVille();
        Call<Ville> call = crudVille.getOne(id);
        call.enqueue(new Callback<Ville>() {
            @Override
            public void onResponse(Call<Ville> call, Response<Ville> response) {

                if(!response.isSuccessful()){
                     Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                     toast.show();

                    Log.e("Response err: ",response.message());
                    return;
                }

                ville = response.body();
                idText.setText(ville.getId()+"");
                nomText.setText(ville.getNom());
                paysText.setText(ville.getPays());
                longitudeText.setText(ville.getLongitude()+"");
                lattitudeText.setText(ville.getLattitude()+"");

            }

            @Override
            public void onFailure(Call<Ville> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ",t.getMessage());
            }
        });




    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(),EditActivity.class);
        intent.putExtra("ville",ville);
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteVilleFragment deleteVilleFragment = new DeleteVilleFragment("Supprimer la ville", ville.getId(),this);
        deleteVilleFragment.show(fragmentManager,"Alert");

    }

    @Override
    public void delete(int id) {
        crudVille = getCrudVille();
        Call<Ville> call = crudVille.delete(id);
        call.enqueue(new Callback<Ville>() {
            @Override
            public void onResponse(Call<Ville> call, Response<Ville> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();

                    Log.e("Response err: ",response.message());
                    return;
                }

                ville = response.body();
                Toast toast = Toast.makeText(getApplicationContext(),"Ville suprimée!!",Toast.LENGTH_LONG);
                toast.show();
                callMain();



            }

            @Override
            public void onFailure(Call<Ville> call, Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ",t.getMessage());

            }
        });

    }

    private CrudVille getCrudVille(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudVille = retrofit.create(CrudVille.class);

        return crudVille;

    }

    private void callMain(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }


}