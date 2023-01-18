package com.example.sidechef.HomeActivity.View.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.models.Meals;

import java.util.List;

public class slideAdapter extends ArrayAdapter {
    List<Meals> mealList;
    int itemLayout;
    Context c;

    // constructor is called to initialize the objects
    public slideAdapter(List<Meals> meal, int resource, Context context) {
        super(context, resource, meal);
        mealList = meal;
        itemLayout = resource;
        c = context;
    }
    @Override
    public int getCount() {
        return mealList.size();
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.text_view);
        ImageView imageView = convertView.findViewById(R.id.image_view);
        textView.setText(mealList.get(position).getmeals().get(0).getStrArea());
        Glide.with(getContext()).load(mealList.get(position).getmeals().get(0).getStrMealThumb()).into(imageView);
        return convertView;
    }
}