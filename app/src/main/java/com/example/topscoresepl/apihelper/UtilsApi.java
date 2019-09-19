package com.example.topscoresepl.apihelper;

public class UtilsApi {

    public static final String BASE_URL_API = "https://api.football-data.org/v2/";

    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
