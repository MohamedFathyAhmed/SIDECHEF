package com.example.sidechef.HomeActivity.View.ui.favorit;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;

import java.util.List;

public class CardViewAdapterFav extends RecyclerView.Adapter<MyViewHolder> {
    private List<Meal> data;
    private Context context;
    private static final String TAG = "RECYCLER_VIEW_TAG";
    AdapterConnector adapterConnector;
Repository repository;
    CardViewAdapterFav(List<Meal> dataset, Context con) {
        context = con;
        this.data = dataset;

    }

    CardViewAdapterFav(List<Meal> dataset, Context context, AdapterConnector adapterConnector) {
        this.context = context;
        this.data = dataset;
        this.adapterConnector = adapterConnector;
        repository = Repository.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.favorite_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvRecipeName.setText(data.get(position).getStrMeal());
        holder.tvCountry.setText(data.get(position).getStrArea());
        holder.tvCat.setText(data.get(position).getStrCategory());
        holder.viewHolder.setOnClickListener(view -> {
                    adapterConnector.sendData(data.get(position));
        });
        Glide.with(context).load(data.get(position).getStrMealThumb()).into(holder.ivRecipePhoto);
         holder.btndelete.setOnClickListener(
         view -> {adapterConnector.callRepo(data.get(position),position);}
         );
        holder.addToPlan.setOnClickListener(
                view -> {adapterConnector.addToPlan(data.get(position));}
        );



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface AdapterConnector {
        public void sendData(Meal meal);
        public void callRepo(Meal meal, int position);
        public void addToPlan(Meal meal);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeName;
    TextView tvCountry;
Button btndelete;
    Button addToPlan;
    TextView tvCat;
    ImageView ivRecipePhoto;
    public LinearLayout viewHolder;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.tvRecipeName = itemView.findViewById(R.id.tvRecipeListName);
        this.tvCountry = itemView.findViewById(R.id.tvRecipecountry);
        this.tvCat = itemView.findViewById(R.id.tvRecipeListCategory);
        this.ivRecipePhoto = itemView.findViewById(R.id.ivRecipeListPhoto);
        this.viewHolder = itemView.findViewById(R.id.viewHolderfav);
        this.btndelete = itemView.findViewById(R.id.btnRecipeListDelete);
        this.addToPlan = itemView.findViewById(R.id.btnRecipeListPlan);
    }

}
