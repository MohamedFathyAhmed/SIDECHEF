package com.example.sidechef;

import android.content.Context;

import com.example.sidechef.SearchView;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;


public class SearchPresenter {
    Repository repository;
    SearchView searchView;

    public SearchPresenter(Context context, SearchView searchView) {
        repository = Repository.getInstance(context);
        this.searchView = searchView;
    }

public  void  getMealByCountry(String countryName, Repository.OnGetMealByFilter onGetMealByFilter){
        repository.getMealByCountry(countryName, onGetMealByFilter);
}
    public  void  getMealByCategory(String categoryName, Repository.OnGetMealByFilter onGetMealByFilter){
        repository.getMealByCategory(categoryName, onGetMealByFilter);
    }
    public  void  getMealByIngredient(String ingredientName, Repository.OnGetMealByFilter onGetMealByFilter){
        repository.getMealByIngredient(ingredientName, onGetMealByFilter);
    }
}
