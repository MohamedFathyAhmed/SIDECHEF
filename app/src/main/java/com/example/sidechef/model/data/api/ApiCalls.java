package com.example.sidechef.model.data.api;

import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.CountryListResponse;
import com.example.sidechef.model.models.FilterResponseModel;
import com.example.sidechef.model.models.IngredientResponse;
import com.example.sidechef.model.models.Meals;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("random.php")
    Observable<Meals> getMeal();
    //"filter.php?i=chicken_breast"
//random.php
    @GET("lookup.php")
    Maybe<Meals> getMealById(@Query("i")String id);


    @GET("categories.php")
    Single<Categories> getCategories();

    @GET("filter.php")
    Single<FilterResponseModel> getMealByCategory(@Query("c")String category);
    @GET("filter.php")
    Single<FilterResponseModel> getMealByIngredient(@Query("i")String ingredient);
    @GET("filter.php")
    Single<FilterResponseModel> getMealByCountry(@Query("a")String area);


    @GET("search.php")
    Single<Meals> getMealByName(@Query("s")String mealName);
    @GET("list.php")
    Single<IngredientResponse> getAllIngredients(@Query("i") String ingredientName);
    @GET("list.php?a=list")
    Single<CountryListResponse> getAllCountries();


}