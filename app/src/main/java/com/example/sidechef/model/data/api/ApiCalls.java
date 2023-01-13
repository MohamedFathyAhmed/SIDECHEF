package com.example.sidechef.model.data.api;

import com.example.sidechef.model.data.models.Categorys;
import com.example.sidechef.model.data.models.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("random.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categorys> getCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c")String category);


    @GET("search.php")
    Call<Meals> getMealByName(@Query("s")String mealName);

}
