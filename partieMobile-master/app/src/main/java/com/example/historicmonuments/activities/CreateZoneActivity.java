package com.example.historicmonuments.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.historicmonuments.MainActivity;
import com.example.historicmonuments.R;
import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.dao.CrudZone;
import com.example.historicmonuments.model.Zone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateZoneActivity extends AppCompatActivity {
    EditText nomText;
    EditText longitudeText;
    EditText lattitudeText;
    Button createButton;

    CrudZone crudzone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_zone);
        nomText =findViewById(R.id.nomText);
        longitudeText=findViewById(R.id.longitudeText);
        lattitudeText=findViewById(R.id.lattitudeText);
        createButton=findViewById(R.id.createButton);
        nomText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        createButton.setEnabled(buttonEnabled());
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zone zone = new Zone(nomText.getText().toString(),Double.valueOf(longitudeText.getText().toString()),Double.valueOf(lattitudeText.getText().toString()));
                create(zone);
            }
        });
    }

    private void create(Zone zone){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudzone = retrofit.create(CrudZone.class);
        Call<Zone> call = crudzone.create(zone);
        call.enqueue(new Callback<Zone>() {
            @Override
            public void onResponse(Call<Zone> call, Response<Zone> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }

                Zone zone= response.body();
                Toast toast = Toast.makeText(getApplicationContext(),zone.getNom() + " crée!!",Toast.LENGTH_LONG);
                toast.show();
                callMain();
            }

            @Override
            public void onFailure(Call<Zone> call, Throwable t) {

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
        return nomText.getText().toString().trim().length() > 0;

    }
}

