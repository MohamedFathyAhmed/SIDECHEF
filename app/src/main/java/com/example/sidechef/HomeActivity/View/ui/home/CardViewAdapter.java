package com.example.sidechef.HomeActivity.View.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter  extends RecyclerView.Adapter<MyViewHolder> {
    private List<Meal> data;
    private  Context context;
    private static final String TAG = "RECYCLER_VIEW_TAG";
    AdapterConnector adapterConnector;

    CardViewAdapter(List<Meal> dataset, Context con) {
        context = con;
        this.data = dataset;

    }

    CardViewAdapter(ArrayList<Meal> dataset, Context context, CardViewAdapter.AdapterConnector adapterConnector) {
        this.context = context;
        this.data = dataset;
        this.adapterConnector = adapterConnector;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.home_card_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvRecipeName.setText(data.get(position).getStrMeal());
        holder.tvRecipePreparationTime.setText(data.get(position).getStrCategory());
        Glide.with(context).load(data.get(position).getStrMealThumb()).into(holder.ivRecipePhoto);
//        holder.mealButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Toast.makeText(context, data.get(position).description, Toast.LENGTH_SHORT);
//
//            }
//        });
        Log.i(TAG, "onBindViewHolder 2");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface AdapterConnector {
        public void sendData(Meal meal);
        public void callRepo(Meal meal,int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeName;
    TextView tvRecipePreparationTime;
    TextView tvRecipeYield;
    TextView tvRecipeId;
    ImageView ivRecipePhoto;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
        this.tvRecipePreparationTime = itemView.findViewById(R.id.tvRecipePreparationTime);
        this.tvRecipeYield = itemView.findViewById(R.id.tvRecipeYield);
        this.tvRecipeId = itemView.findViewById(R.id.tvRecipeId);
        this.ivRecipePhoto = itemView.findViewById(R.id.ivRecipePhoto);
    }

}
