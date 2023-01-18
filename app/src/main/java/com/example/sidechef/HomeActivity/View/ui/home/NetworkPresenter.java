package com.example.sidechef.HomeActivity.View.ui.home;

import android.content.Context;

import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;


public class NetworkPresenter {
    Repository repository;
   NetworkInterface networkInterface;

    public NetworkPresenter(Context context, NetworkInterface networkInterface) {
        repository = Repository.getInstance(context);
        this.networkInterface = networkInterface;
    }

    public void insertItem(Meal meal){
        repository.insert(meal);
    }

    public void getMeals() {
        repository.getAllMeals(networkInterface);
    }
    public void getCategories(){repository.getAllCategories(networkInterface);}
}
