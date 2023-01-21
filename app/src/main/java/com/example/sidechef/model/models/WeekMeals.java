package com.example.sidechef.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.sidechef.model.data.database.MealsDatabase;

import java.util.ArrayList;

@Entity(tableName = MealsDatabase.Week_TABLE_NAME)
public class WeekMeals  implements Parcelable  {
    protected WeekMeals(Parcel in) {
        id = in.readInt();
        meals = in.createTypedArrayList(Meal.CREATOR);
    }

    public static final Creator<WeekMeals> CREATOR = new Creator<WeekMeals>() {
        @Override
        public WeekMeals createFromParcel(Parcel in) {
            return new WeekMeals(in);
        }

        @Override
        public WeekMeals[] newArray(int size) {
            return new WeekMeals[size];
        }
    };

    //    enum Days {
//        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WeekMeals(int id, ArrayList<Meal> meals) {
        this.id = id;
        this.meals = meals;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;


    private ArrayList<Meal> meals;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeTypedList(meals);
    }
}
