package com.example.sidechef.model.data.api;

import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.Meals;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("random.php")
    Observable<Meals> getMeal();
//"filter.php?i=chicken_breast"
//random.php


    @GET("categories.php")
    Single<Categories> getCategories();

    @GET("filter.php")
    Observable<Meals> getMealByCategory(@Query("c")String category);


    @GET("search.php")
    Single<Meals> getMealByName(@Query("s")String mealName);

}
