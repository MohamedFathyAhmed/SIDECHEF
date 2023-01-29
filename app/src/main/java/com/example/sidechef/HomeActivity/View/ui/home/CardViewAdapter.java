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
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Meals> data;
    private Context context;
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
        holder.tvCountry.setText(data.get(position).getmeals().get(0).getStrArea());
        holder.tvCat.setText(data.get(position).getmeals().get(0).getStrCategory());
        holder.tvRecipeName.setText(data.get(position).getmeals().get(0).getStrMeal());

        // holder.viewHolder.setOnClickListener(view ->
        // adapterConnector.sendData(data.get(position).getmeals().get(0)));
        // holder.tvRecipePreparationTime.setText(data.get(position).getmeals().get(0).getStrCategory());

        holder.viewHolder.setOnClickListener(view -> adapterConnector.navigate(data.get(position).getmeals().get(0)));

        Glide.with(context).load(data.get(position).getmeals().get(0).getStrMealThumb()).into(holder.ivRecipePhoto);
        holder.btn_love_item.setOnClickListener(
                view -> {

                    holder.btn_love_item.setBackgroundResource(R.drawable.ic_favorite);
                    adapterConnector.callRepo(data.get(position).getmeals().get(0), position);
                });

        Log.i(TAG, "onBindViewHolder 2");
        String email = YourPreference.getInstance(context).getData("email");
        if (email.equals("") && Utils.isNetworkAvailable(context)) {
            holder.btn_love_item.setVisibility(View.GONE);
        } else {
            holder.btn_love_item.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface AdapterConnector {
        public void navigate(Meal meal);

        public void callRepo(Meal meal, int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeName;
    TextView tvCountry;
    TextView tvCat;
    ImageView ivRecipePhoto;
    public LinearLayout viewHolder;
    ImageView btn_love_item;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.tvRecipeName = itemView.findViewById(R.id.tvRecipetime);
        this.tvCountry = itemView.findViewById(R.id.txCountry);
        this.tvCat = itemView.findViewById(R.id.tvCategory);
        this.ivRecipePhoto = itemView.findViewById(R.id.ivRecipePhoto);
        this.viewHolder = itemView.findViewById(R.id.viewHolder);
        this.btn_love_item = itemView.findViewById(R.id.btn_love_item);
    }

}
