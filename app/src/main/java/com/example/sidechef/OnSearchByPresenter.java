package com.example.sidechef;

import android.content.Context;

import com.example.sidechef.HomeActivity.View.ui.home.NetworkInterface;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;

public class OnSearchByPresenter {


    Repository repository;
    OnSearchByView onSearchByView;

    public OnSearchByPresenter(Context context, OnSearchByView onSearchByView) {
        repository = Repository.getInstance(context);
        this.onSearchByView = onSearchByView;
    }



    public void getAllCountries() {
        repository.getAllCountries("list",onSearchByView);
    }

    public void getAllIngredients() {
        repository.getAllIngredients("list",onSearchByView);
    }

}