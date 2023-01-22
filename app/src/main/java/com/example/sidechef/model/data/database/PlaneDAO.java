package com.example.sidechef.model.data.database;

import androidx.lifecycle.LiveData;
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
public interface PlaneDAO {

    @Query("SELECT * FROM "+ MealsDatabase.Week_TABLE_NAME)
    List<WeekMeals> getAll();


    @Query("SELECT * FROM Week WHERE day =:day" )
    LiveData<List<WeekMeals>> getForDay(String day);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeekMeals meal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<WeekMeals> meals);

    @Delete
    void delete(WeekMeals meal);


}
