package com.example.sidechef.model.data;

import android.content.Context;

import com.example.sidechef.model.data.api.ApiCalls;
import com.example.sidechef.model.data.api.Network;
import com.example.sidechef.model.data.database.MealsDatabase;
import com.example.sidechef.model.data.database.MealsDAO;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    ApiCalls api;
    MealsDAO db;
    private static Repository instance = null;
    private Repository(Context context) {
        Retrofit network = Network.getInstance(context);
        api = network.create(ApiCalls.class);
        MealsDatabase productDatabase = MealsDatabase.getInstance(context);
        db = productDatabase.productsDAO();
    }

    public static synchronized Repository getInstance(Context context){
        if (instance == null)
            instance = new Repository(context);
        return instance;
    }

    public void getAllMeals(ApiResponse apiResponse) {
        Call<Meals> request = api.getMeal();
        request.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()) {
                    ArrayList<Meal> meals = response.body().getmeals();
                    apiResponse.onSuccessResponse(meals);
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                apiResponse.onErrorResponse(t.getMessage());
            }
        });
    }


    public void getAll(DBResponse dbResponse){
        new Thread(() ->
                dbResponse.onSuccessResponse(db.getAll())
        ).start();
    };

    public void insert(Meal meal){
        new Thread(() -> db.insert(meal)).start();
    };


    public void delete(Meal meal){
        new Thread(() -> db.delete(meal)).start();
    };

    public interface ApiResponse{
        void onSuccessResponse(ArrayList<Meal> meals);
        void onErrorResponse(String errorMessage);
    }

    public interface DBResponse{
        void onSuccessResponse(List<Meal> meals);
    }



}
