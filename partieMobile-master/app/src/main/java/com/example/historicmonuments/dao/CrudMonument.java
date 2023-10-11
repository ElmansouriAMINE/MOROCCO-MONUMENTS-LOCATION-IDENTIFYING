package com.example.historicmonuments.dao;

import com.example.historicmonuments.model.Monument;
import com.example.historicmonuments.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudMonument {
    @GET("Monument")
    Call<List<Monument>> getAllMonument();

    @GET("Monument/{id}")
    Call<Monument> getOne(@Path("id") int id);

    @POST("/addMonument")
    Call<Monument> create(@Body Monument monument);

    @PUT("updatemonument/{id}")
    Call<Monument> edit(@Path("id") int id, @Body Monument monument);

    @DELETE("deletemonument/{id}")
    Call<Monument> delete(@Path("id") int id);
}
