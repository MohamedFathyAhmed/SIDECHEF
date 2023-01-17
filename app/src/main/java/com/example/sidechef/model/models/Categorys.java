package com.example.sidechef.model.models;


import java.util.ArrayList;

public class Categorys {
    private ArrayList<Category> categorys;

    public Categorys() {
    }

    public Categorys(ArrayList<Category> categorys) {
        this.categorys = categorys;

    }

    public ArrayList<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(ArrayList<Category> categorys) {
        this.categorys = categorys;
    }



}
