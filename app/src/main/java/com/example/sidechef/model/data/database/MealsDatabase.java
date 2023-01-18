package com.example.sidechef.model.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sidechef.model.models.Meal;


@androidx.room.Database(entities = {Meal.class}, version = 1)
public abstract class MealsDatabase extends RoomDatabase{
    public static final String MEAL_TABLE_NAME = "Meals";

    private  static MealsDatabase instance = null;
    public abstract MealsDAO productsDAO();

    public static synchronized MealsDatabase getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDatabase.class,"MealDB")
                    .fallbackToDestructiveMigration()
                    .build();
        return instance;
    }
}
