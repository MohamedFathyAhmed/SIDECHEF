package com.example.sidechef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.sidechef.model.models.Meal;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<Meal> {
    Context context;

    public GridAdapter(@NonNull Context context, ArrayList<Meal> mealModelArrayList) {
        super(context, 0, mealModelArrayList);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.search_card, parent, false);
        }

        Meal mealModel = getItem(position);
        TextView mealTV = listitemView.findViewById(R.id.tvsearchRecipeName);
        ImageView mealIV = listitemView.findViewById(R.id.ivRecipesearchPhoto);

        mealTV.setText(mealModel.getStrMeal());
        Glide.with(context).load(mealModel.getStrMealThumb()).into(mealIV);

        return listitemView;
    }
}
