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
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.sidechef.HomeActivity.View.ui.search.Connector;
import com.example.sidechef.R;
import com.example.sidechef.model.models.FilteredMeal;
import com.example.sidechef.model.models.Ingredient;
import com.example.sidechef.model.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends ArrayAdapter<Meal> {
    Context context;
    List<Meal> data=new ArrayList<>();
    AdapterConnector connector;
    public GridViewAdapter(@NonNull Context context, List<Meal> mealModelArrayList,AdapterConnector connector) {
        super(context, 0, mealModelArrayList);
        this.context=context;
        this.data=mealModelArrayList;
        this.connector=connector;
    }

    @Override
    public int getCount() {
        if(data!=null){
            return data.size();
        }else {
            return 0;
        }

    }
    public interface AdapterConnector {
        public void navigate(Meal meal);

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
        CardView cardView=listitemView.findViewById(R.id.filtered_meal_item);

        mealTV.setText(mealModel.getStrMeal());
        Glide.with(context).load(mealModel.getStrMealThumb()).into(mealIV);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connector.navigate(mealModel);
            }
        });

        return listitemView;
    }
}
