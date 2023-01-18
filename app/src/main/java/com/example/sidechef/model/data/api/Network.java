package com.example.sidechef.model.data.api;

import android.content.Context;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class  Network{

    private volatile static Retrofit instance = null;

    public static synchronized Retrofit getInstance(Context context){
        if (instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        return instance;
    }

}