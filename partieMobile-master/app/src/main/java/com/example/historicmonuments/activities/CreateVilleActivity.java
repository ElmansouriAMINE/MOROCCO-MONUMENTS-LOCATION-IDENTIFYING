package com.example.historicmonuments.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.historicmonuments.MainActivity;
import com.example.historicmonuments.MapActivity;
import com.example.historicmonuments.R;
import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.dao.CrudVille;
import com.example.historicmonuments.model.Ville;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateVilleActivity extends AppCompatActivity {
    Spinner nomText;
    Spinner paysText;
    Spinner longitudeText;
    Spinner lattitudeText;
    Button createButton;

    CrudVille crudville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ville);
        nomText =findViewById(R.id.sp_type1);
        paysText=findViewById(R.id.sp_type2);

        createButton=findViewById(R.id.createButton);


        String[] pay = {"Morroco"};
        //Initialize array of place name
        String[] villeNameList = {"Casablanca", "Rabat", "Marrakech", "Fès", "Tanger","El Jadida"};
        String[] longitude = {" -7.58333 ", " -6.84167 ", " -8.00889 ", " -50.00000 ", " -5.80000 ","-8.50000"};
        //set adapter on spinner
        String[] latitude = {" 33.53333", " 34.02083", " 31.63000", " 34.03333", " 35.76611","33.23333"};
        nomText.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, villeNameList));
        //Initialize fused location

        paysText.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, pay));
        //Initialize fused location







        createButton.setEnabled(buttonEnabled());
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(nomText.getSelectedItem().toString().equals("Casablanca"))  {
                    Ville ville = new Ville("Casablanca", "Morroco", -7.58333 , 33.53333);
                    create(ville);

                }
                if(nomText.getSelectedItem().toString().equals("Rabat"))  {
                    Ville ville = new Ville("Rabat", "Morroco", -6.84167, 34.02083);
                    create(ville);
                }
                if(nomText.getSelectedItem().toString().equals("Marrakech"))  {
                    Ville ville = new Ville("Marrakech", "Morroco", -8.00889, 31.63000);
                    create(ville);
                }
                if(nomText.getSelectedItem().toString().equals("Fès"))  {
                    Ville ville = new Ville("Fès", "Morroco", -50.00000,  34.03333);
                    create(ville);
                }
                if(nomText.getSelectedItem().toString().equals("Tanger"))  {
                    Ville ville = new Ville("Tanger", "Morroco",-5.80000 ,35.76611);
                    create(ville);
                }
                if(nomText.getSelectedItem().toString().equals("El Jadida"))  {
                    Ville ville = new Ville("El Jadida", "Morroco", -8.50000, 33.23333);
                    create(ville);
                }
            }
        });
    }

    private void create(Ville ville){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudville = retrofit.create(CrudVille.class);
        Call<Ville> call = crudville.create(ville);
        call.enqueue(new Callback<Ville>() {
            @Override
            public void onResponse(Call<Ville> call, Response<Ville> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }

                Ville ville= response.body();
                Toast toast = Toast.makeText(getApplicationContext(),ville.getNom() + " crée!!",Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Ville> call, Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                System.out.println(t.getMessage());


            }
        });


    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean buttonEnabled(){
        return nomText.getSelectedItem().toString().trim().length() > 0 &&
                paysText.getSelectedItem().toString().trim().length() > 0;
    }







}