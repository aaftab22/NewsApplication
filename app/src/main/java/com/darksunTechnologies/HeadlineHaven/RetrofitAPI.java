package com.darksunTechnologies.HeadlineHaven;

import com.example.HeadlineHaven.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<News> getAllNews(@Url String url);

    @GET
    Call<News> getNewsByCategory(@Url String url);
}
