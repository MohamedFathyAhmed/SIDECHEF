package com.example.sidechef;

import android.content.Context;

import com.example.sidechef.HomeActivity.View.ui.search.OnSearchByView;
import com.example.sidechef.model.Repository;

public class SearchByNamePresenter {
    Repository repository;
    SearchByNameView searchByNameView;

    public SearchByNamePresenter(Context context, SearchByNameView searchByNameView) {
        repository = Repository.getInstance(context);
        this.searchByNameView = searchByNameView;
    }
    public  void getMealByName(String mealName){
        repository.getMealByName(mealName,searchByNameView);
    }
}
