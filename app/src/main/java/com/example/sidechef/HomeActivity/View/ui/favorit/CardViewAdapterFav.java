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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.Utils;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
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
        View v = layoutInflater.inflate(R.layout.home_card_view, parent, false);
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

            AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
            builderSingle.setIcon(R.drawable.ic_favorite);
            builderSingle.setTitle("Select day:-");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add(Week.Saturday.toString());
            arrayAdapter.add(Week.Sunday.toString());
            arrayAdapter.add(Week.Monday.toString());
            arrayAdapter.add(Week.Tuesday.toString());
            arrayAdapter.add(Week.Wednesday.toString());
            arrayAdapter.add(Week.Thursday.toString());
            arrayAdapter.add(Week.Friday.toString());
            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(context);
                    builderInner.setMessage(strName);
                    builderInner.setTitle("Your Selected ");
                    builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            WeekMeals weekMeals = Utils.converter(strName,"breakfast",data.get(position));
                            repository.insertdaily(weekMeals);
                            dialog.dismiss();
                        }
                    });
                    builderInner.show();
                }
            });
            builderSingle.show();
        }

                //adapterConnector.sendData(data.get(position))
                 );
        Glide.with(context).load(data.get(position).getStrMealThumb()).into(holder.ivRecipePhoto);
        // holder.mealButton.setOnClickListener(
        // view -> {adapterConnector.callRepo(data.get(position),position);}
        // );
        // holder.mealButton.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        // // Toast.makeText(context, data.get(position).description,
        // Toast.LENGTH_SHORT);
        //
        // }
        // });
        Log.i(TAG, "onBindViewHolder 2");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface AdapterConnector {
        public void sendData(Meal meal);

        public void callRepo(Meal meal, int position);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeName;
    TextView tvCountry;
    TextView tvRecipeYield;
    TextView tvCat;
    ImageView ivRecipePhoto;
    public LinearLayout viewHolder;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
        this.tvCountry = itemView.findViewById(R.id.txCountry);
        this.tvRecipeYield = itemView.findViewById(R.id.tvCategory);
        this.tvCat = itemView.findViewById(R.id.tvCategory);
        this.ivRecipePhoto = itemView.findViewById(R.id.ivRecipePhoto);
        this.viewHolder = itemView.findViewById(R.id.viewHolder);

    }

}
