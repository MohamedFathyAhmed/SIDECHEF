package com.example.sidechef.model.models;

import com.google.gson.annotations.SerializedName;

public class FilteredMeal {
    @SerializedName("strMeal")
    private String strMeal;
    @SerializedName("strMealThumb")
    private String strMealThumb;

    @SerializedName("idMeal")
    private String idMeal;



    public String getStrMealThumb(){
        return strMealThumb;
    }

    public String getIdMeal(){
        return idMeal;
    }

    public String getStrMeal(){
        return strMeal;
    }
}