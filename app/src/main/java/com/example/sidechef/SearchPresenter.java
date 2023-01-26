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

public  void  getMealByCountry(String countryName, SearchView searchView){
        repository.getMealByCountry(countryName, searchView);
}
    public  void  getMealById(String id, SearchView searchView){
        repository.getMealById(id, searchView);
    }
    public  void  getMealByCategory(String categoryName, SearchView searchView){
        repository.getMealByCategory(categoryName, searchView);
    }
    public  void  getMealByIngredient(String ingredientName, SearchView searchView){
        repository.getMealByIngredient(ingredientName, searchView);
    }
}
