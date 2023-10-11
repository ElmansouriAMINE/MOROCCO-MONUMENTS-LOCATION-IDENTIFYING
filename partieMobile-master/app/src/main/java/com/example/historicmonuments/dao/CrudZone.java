package com.example.historicmonuments.dao;

import com.example.historicmonuments.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudZone {

    @GET("zones")
    Call<List<Zone>> getAllZones();

    @GET("zone/{id}")
    Call<Zone> getOne(@Path("id") int id);

    @POST("/addzone")
    Call<Zone> create(@Body Zone zone);

    @PUT("updatezone/{id}")
    Call<Zone> edit(@Path("id") int id, @Body Zone zone);

    @DELETE("deletezone/{id}")
    Call<Zone> delete(@Path("id") int id);
}
