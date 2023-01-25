package com.example.sidechef.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterResponseModel {

    @SerializedName("meals")
    private List<FilteredMeal> meals;

    public List<FilteredMeal> getMeals(){
        return meals;
    }
}