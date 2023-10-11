package com.example.historicmonuments.dao;

import com.example.historicmonuments.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudVille {


    @GET("villes")
    Call<List<Ville>> getAllCities();

    @GET("ville/{id}")
    Call<Ville> getOne(@Path("id") int id);

    @POST("/addville")
    Call<Ville> create(@Body Ville ville);

    @PUT("updateville/{id}")
    Call<Ville> edit(@Path("id") int id, @Body Ville ville);

    @DELETE("deleteville/{id}")
    Call<Ville> delete(@Path("id") int id);









}
