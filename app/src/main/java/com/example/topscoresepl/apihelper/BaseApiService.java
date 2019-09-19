package com.example.topscoresepl.apihelper;

import com.example.topscoresepl.model.Value;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BaseApiService {

    @GET("competitions/2021/scorers")
    Call<Value> getValue(
            @Header("X-Auth-Token") String token);
}
