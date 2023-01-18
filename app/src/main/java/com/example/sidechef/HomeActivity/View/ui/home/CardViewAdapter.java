package com.example.sidechef.HomeActivity.View.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter  extends RecyclerView.Adapter<MyViewHolder> {
    private List<Meals> data;
    private  Context context;
    private static final String TAG = "RECYCLER_VIEW_TAG";
    AdapterConnector adapterConnector;

    CardViewAdapter(List<Meals> dataset, Context con) {
        context = con;
        this.data = dataset;

    }

    CardViewAdapter(List<Meals> dataset, Context context, CardViewAdapter.AdapterConnector adapterConnector) {
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
        holder.tvRecipeName.setText(data.get(position).getmeals().get(0).getStrMeal());
        holder.tvRecipePreparationTime.setText(data.get(position).getmeals().get(0).getStrCategory());
        holder.viewHolder.setOnClickListener(view ->
                adapterConnector.sendData(data.get(position).getmeals().get(0)));
        Glide.with(context).load(data.get(position).getmeals().get(0).getStrMealThumb()).into(holder.ivRecipePhoto);
//        holder.mealButton.setOnClickListener(
//                view -> {adapterConnector.callRepo(data.get(position),position);}
//        );
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
    public LinearLayout viewHolder;


    public MyViewHolder(View itemView) {
        super(itemView);
        this.tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
        this.tvRecipePreparationTime = itemView.findViewById(R.id.tvRecipePreparationTime);
        this.tvRecipeYield = itemView.findViewById(R.id.tvRecipeYield);
        this.tvRecipeId = itemView.findViewById(R.id.tvRecipeId);
        this.ivRecipePhoto = itemView.findViewById(R.id.ivRecipePhoto);
        this.viewHolder = itemView.findViewById(R.id.viewHolder);

    }

}

