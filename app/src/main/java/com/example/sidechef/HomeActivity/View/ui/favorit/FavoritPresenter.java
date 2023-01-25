package com.example.sidechef.HomeActivity.View.ui.favorit;

import android.content.Context;

import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.WeekMeals;

import java.util.WeakHashMap;


public class FavoritPresenter{
    Repository repository;
    FavoritInterface favoritInterface;

    public FavoritPresenter(Context context,FavoritInterface favoritInterface) {
        this.favoritInterface = favoritInterface;
        repository = Repository.getInstance(context);
    }

    public void deleteItem(Meal meal){
        repository.delete(meal);
    }
    public void addToPlan(WeekMeals meal){
        repository.insertdaily(meal);
    }
    public void fetchData() {
        repository.getAll(favoritInterface);
    }

}
