package com.example.sidechef.HomeActivity.View.ui.planWeek;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.models.WeekMeals;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<WeekMeals> data;
    private Context context;
    private static final String TAG = "RECYCLER_VIEW_TAG";
    AdapterConnector adapterConnector;


    ChildItemAdapter(List<WeekMeals> dataset, Context con,AdapterConnector adapterConnector) {
        context = con;
        this.data = dataset;
      this.adapterConnector = adapterConnector;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.plan_card_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvRecipeName.setText(data.get(position).getStrMeal());
        holder.tvCountry.setText(data.get(position).getStrArea());
        holder.tvCat.setText(data.get(position).getStrCategory());
        holder.tvRecipetime.setText(data.get(position).getTime());
        holder.viewHolder.setOnClickListener(
                view -> {
                    WeekFragmentDirections.ActionNavigationPlanweekToNavigationDes action =
                            WeekFragmentDirections.actionNavigationPlanweekToNavigationDes(data.get(position));
                    Navigation.findNavController(view).navigate(action);
              }
        );

        holder.btn_delete_item.setOnClickListener(
                view -> {
                  adapterConnector.callRepo(data.get(position),position);
                }
        );


        Glide.with(context).load(data.get(position).getStrMealThumb()).into(holder.ivRecipePhoto);

        Log.i(TAG, "onBindViewHolder 2");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface AdapterConnector {
        public void sendData(WeekMeals meal);
        public void callRepo(WeekMeals meal, int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeName;
    TextView tvCountry;
    TextView tvCat;
    ImageView ivRecipePhoto;
    public LinearLayout viewHolder;
    ImageView btn_delete_item;
    TextView tvRecipetime;
    public MyViewHolder(View itemView) {
        super(itemView);
        this.tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
        this.tvCountry = itemView.findViewById(R.id.txCountry);
        this.tvCat = itemView.findViewById(R.id.tvCategory);
        this.ivRecipePhoto = itemView.findViewById(R.id.ivRecipePhoto);
        this.viewHolder = itemView.findViewById(R.id.viewHolder);
        this.btn_delete_item = itemView.findViewById(R.id.btn_delete_item);
        this.tvRecipetime = itemView.findViewById(R.id.tvRecipetime);

    }

}
