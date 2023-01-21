package com.example.sidechef.HomeActivity.View.ui.dashboard;

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
import com.example.sidechef.model.models.Meal;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Meal> data;
    private Context context;
    private static final String TAG = "RECYCLER_VIEW_TAG";
    AdapterConnector adapterConnector;

    ChildItemAdapter(List<Meal> dataset, Context con) {
        context = con;
        this.data = dataset;

    }


    ChildItemAdapter(List<Meal> dataset, Context context, AdapterConnector adapterConnector) {
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
        holder.tvCountry.setText(data.get(position).getStrArea());
        holder.tvCat.setText(data.get(position).getStrCategory());
        holder.viewHolder.setOnClickListener(view -> adapterConnector.sendData(data.get(position)));
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


/*
package com.example.sidechef.HomeActivity.View.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sidechef.R;
import com.example.sidechef.model.models.Meal;

import java.util.List;

public class ChildItemAdapter
        extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<Meal> ChildItemList;

    // Constructor
    ChildItemAdapter(List<Meal> childItemList)
    {
        this.ChildItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the child item
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.child_item,
                        viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ChildViewHolder childViewHolder,
            int position)
    {

        // Create an instance of the ChildItem
        // class for the given position
        Meal childItem
                = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder
                .ChildItemTitle
                .setText(childItem.getStrMeal());
    }

    @Override
    public int getItemCount()
    {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size();
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    class ChildViewHolder
            extends RecyclerView.ViewHolder {

        TextView ChildItemTitle;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            ChildItemTitle
                    = itemView.findViewById(
                    R.id.child_item_title);
        }
    }
}

 */