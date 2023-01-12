package com.example.sidechef.model.data.models;

import java.util.ArrayList;

public class Meals {
    private ArrayList<Meal> meals;


    public Meals() {
    }

    public Meals(ArrayList<Meal> meals) {
        this.meals = meals;

    }

    public ArrayList<Meal> getmeals() {
        return meals;
    }

    public void setmeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }



}

