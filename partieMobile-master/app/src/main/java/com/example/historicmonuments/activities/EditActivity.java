package com.example.historicmonuments.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.historicmonuments.MainActivity;
import com.example.historicmonuments.R;
import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.dao.CrudVille;
import com.example.historicmonuments.model.Ville;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {
    Ville ville;
    EditText nomText;
    EditText paysText;
    EditText longitudeText;
    EditText lattitudeText;
    Button editButton;
    CrudVille crudVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent detailIntent = getIntent();
        ville =(Ville) detailIntent.getSerializableExtra("ville");
        nomText =findViewById(R.id.nomText);
        paysText=findViewById(R.id.paysText);
        longitudeText=findViewById(R.id.longitudeText);
        lattitudeText=findViewById(R.id.lattitudeText);
        nomText.setText(ville.getNom());
        paysText.setText(ville.getPays());
        longitudeText.setText(ville.getLongitude()+"");
        lattitudeText.setText(ville.getLongitude()+"");
        editButton=findViewById(R.id.editButton);
        Log.i("AMINE ville : ",ville.toString());
        nomText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        paysText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(buttonEnabled());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editButton.setEnabled(buttonEnabled());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ville ville2 = new Ville(nomText.getText().toString(),paysText.getText().toString(),Double.valueOf(longitudeText.getText().toString()),Double.valueOf(lattitudeText.getText().toString()));
                edit(ville2);
            }
        });
    }

    private void edit(Ville villemodifier){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudVille =retrofit.create(CrudVille.class);
        int id = ville.getId();
        Call<Ville> call = crudVille.edit(id,villemodifier);
        call.enqueue(new Callback<Ville>() {
            @Override
            public void onResponse(Call<Ville> call, Response<Ville> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }

                Ville ville= response.body();
                Toast toast = Toast.makeText(getApplicationContext(),ville.getNom() + " modifiée!!",Toast.LENGTH_LONG);
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
        return nomText.getText().toString().trim().length() > 0 &&
                paysText.getText().toString().trim().length() > 0;
    }

}