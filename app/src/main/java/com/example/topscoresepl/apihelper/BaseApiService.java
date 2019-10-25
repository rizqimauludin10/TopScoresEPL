package com.example.topscoresepl.apihelper;

import android.content.Intent;

import com.example.topscoresepl.model.Player;
import com.example.topscoresepl.model.Value;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("competitions/2021/scorers")
    Call<Value> getValue(
            @Header("X-Auth-Token") String token);

    @GET("players/{id}")
    Call<Player> getPlayers(
            @Header("X-Auth-Token") String token,
            @Path("id") Integer idPlayer);
}
