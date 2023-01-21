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

public  void  getMealByName(String mealName, Repository.OnGetMealByName onGetMealByName){
        repository.getMealByName(mealName,onGetMealByName);
}
}
