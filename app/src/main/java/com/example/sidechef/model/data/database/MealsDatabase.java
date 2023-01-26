package com.example.sidechef.model.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.WeekMeals;


@androidx.room.Database(entities = {Meal.class , WeekMeals.class}, version = 2,exportSchema = false)
public abstract class MealsDatabase extends RoomDatabase{
    public static final String MEAL_TABLE_NAME = "Meals";
    public static final String Week_TABLE_NAME = "Week";

    private  static MealsDatabase instance = null;
    public abstract MealsDAO mealsDAO();
    public abstract PlaneDAO planeDAO();

    public static synchronized MealsDatabase getInstance(Context context){
        if (instance == null)
       //     instance = Room.databaseBuilder(context.getApplicationContext(), MealsDatabase.class,"MealDB")
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDatabase.class,"foodDB")
                    .fallbackToDestructiveMigration()
                    .build();
        return instance;
    }
}
