package com.example.sidechef.model.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sidechef.model.models.WeekMeals;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PlaneDAO {

    @Query("SELECT * FROM "+ MealsDatabase.Week_TABLE_NAME)
    List<WeekMeals> getAll();


    @Query("SELECT * FROM Week WHERE day = :selectday" )
    List<WeekMeals> getForDay(String selectday);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeekMeals meal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<WeekMeals> meals);

    @Delete
    void delete(WeekMeals meal);


}
