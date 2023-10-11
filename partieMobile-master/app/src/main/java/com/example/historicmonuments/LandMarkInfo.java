package com.example.historicmonuments;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.historicmonuments.Utils.Constants;
import com.example.historicmonuments.dao.CrudMonument;
import com.example.historicmonuments.dao.CrudVille;
import com.example.historicmonuments.fragments.MapsFragment;
import com.example.historicmonuments.model.Monument;
import com.example.historicmonuments.model.Ville;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandMarkInfo extends AppCompatActivity {

    TextView label_target;
    TextView longitude;
    TextView latitude;
    TextView label_remaining;
    TextView label_distance;
    Button button_take_photo;
    Bundle extras;
    Button button_finish_session,button_save_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mark_info);
        //getSupportActionBar().hide();



        button_finish_session = findViewById(R.id.button_finish_session);
        button_save_progress = findViewById(R.id.button_save_progress);
        label_target = findViewById(R.id.label_target);
        longitude = findViewById(R.id.label_remaining);
        latitude = findViewById(R.id.label_distance);
        button_take_photo = findViewById(R.id.button_take_photo);

        extras = getIntent().getExtras();
        label_target.setText("Target:"+extras.getString("name"));
        longitude.setText("There are "+extras.getInt("id")+" landmarks remaining in your session");
        latitude.setText("your target is:"+extras.getFloat("position")+" meters away");


        button_finish_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        button_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.commit();
                Bundle bundle = new Bundle();
                bundle.putDouble("longitude",extras.getDouble("longitude") );
                bundle.putDouble("lattitude",extras.getDouble("lattitude"));
                MapsFragment fragobj = new MapsFragment();
                fragmentTransaction.add(R.id.container_fragment, fragobj);
                fragobj.setArguments(bundle);




            }
        });



        button_save_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Monument monument = new Monument(extras.getString("name"),extras.getDouble("longitude"),extras.getDouble("lattitude"),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCo4QPfPiJQt2HnH5mk1ABXr2tUGEFwV2czg&usqp=CAU");
                //Monument monument = new Monument("hhh",33.23333, -8.50000,"hhhh");
                create(monument);

            }
        });




    }

    private void create(Monument monument){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CrudMonument crudMonument = retrofit.create(CrudMonument.class);
        Call<Monument> call = crudMonument.create(monument);
        call.enqueue(new Callback<Monument>() {
            @Override
            public void onResponse(Call<Monument> call, Response<Monument> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println(response.message());
                }



                Intent i = new Intent(getApplicationContext(),Monuments.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<Monument> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                System.out.println(t.getMessage());
            }


        });



    }



}