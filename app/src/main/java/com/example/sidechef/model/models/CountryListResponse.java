package com.example.sidechef.model.models;

import java.util.ArrayList;

public class CountryListResponse {
    private ArrayList<Country> meals;


    public CountryListResponse() {}

    public CountryListResponse(ArrayList<Country> meals) {
        this.meals = meals;

    }

    public ArrayList<Country> getmeals() {
        return meals;
    }

    public void setmeals(ArrayList<Country> meals) {
        this.meals = meals;
    }


}
