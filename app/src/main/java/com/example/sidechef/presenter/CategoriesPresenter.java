package com.example.sidechef.presenter;

import com.example.sidechef.model.models.Categories;

interface CategoriesPresenterInterface {
    public  void onGetCategoriesSuccess(Categories categories);
    public  void onGetCategoryFailure();

}

public class CategoriesPresenter implements CategoriesPresenterInterface{


    @Override
    public void onGetCategoriesSuccess(Categories categories) {

    }

    @Override
    public void onGetCategoryFailure() {

    }
}