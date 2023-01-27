package com.example.sidechef;

import com.example.sidechef.model.models.Meals;

public interface SearchByNameView {

        void OnGetMealByNameSuccess(Meals meals);

        void OnGetMealByNameFailure(String errorMessage);

}
