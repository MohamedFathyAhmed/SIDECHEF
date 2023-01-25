package com.example.sidechef.HomeActivity.View.ui.search;

import android.content.Context;

import com.example.sidechef.model.Repository;

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