package com.example.sidechef.model.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.WeekMeals;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface MealsDAO {
    @Query("SELECT * FROM "+ MealsDatabase.MEAL_TABLE_NAME)
    Flowable<List<Meal>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Meal meal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Meal> meals);

    @Delete
    void delete(Meal meal);
    @Query("DELETE FROM "+ MealsDatabase.MEAL_TABLE_NAME)
    void deleteTableRoom();


}
