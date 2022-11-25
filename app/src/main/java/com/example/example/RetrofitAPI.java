package com.example.example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<newsModelClass> getAllNews(@Url String url);

    @GET
    Call<newsModelClass> getNewsByCategory(@Url String url);
}
