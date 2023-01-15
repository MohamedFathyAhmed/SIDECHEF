package com.example.sidechef.HomeActivity.View.ui;

import com.example.sidechef.model.data.models.Meal;

public interface DetailViewinterface {

    void setMeal(Meal meal);
    void onErrorLoading(String message);
}
