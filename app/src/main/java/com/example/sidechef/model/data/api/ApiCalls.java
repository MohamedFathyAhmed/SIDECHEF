package com.example.sidechef.model.data.api;

import com.example.sidechef.model.data.models.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCalls {

    @GET("Meals")
    public Call<Meals> getAllMeals();

}
