package com.example.sidechef.HomeActivity.View.ui.search;

import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.FilterResponseModel;
import com.example.sidechef.model.models.Meals;

public interface SearchView {

        void OnGetMealByIdSuccess(Meals meals);
        void OnGetMealByIdFailure(String errorMessage);
        void  OnGetMealByIdComplete();
        void OnGetMealByFilterSuccess(FilterResponseModel meals);
        void OnGetMealByFilterFailure(String errorMessage);
    }





