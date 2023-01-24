package com.example.sidechef.model.models;



import java.util.ArrayList;

public class IngredientResponse {
    private ArrayList<Ingredient> meals;


    public IngredientResponse() {}

    public IngredientResponse(ArrayList<Ingredient> meals) {
        this.meals = meals;

    }

    public ArrayList<Ingredient> getmeals() {
        return meals;
    }

    public void setmeals(ArrayList<Ingredient> meals) {
        this.meals = meals;
    }



}

