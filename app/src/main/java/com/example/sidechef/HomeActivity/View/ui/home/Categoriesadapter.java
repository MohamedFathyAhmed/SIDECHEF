package com.example.sidechef.HomeActivity.View.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sidechef.Connector;
import com.example.sidechef.R;
import com.example.sidechef.model.models.Category;

import java.util.List;

public class Categoriesadapter  extends RecyclerView.Adapter<CategoriesViewHolder> {
    private List<Category> data;
    private final Context context;
    private  Connector connector;
    private static final String TAG = "RECYCLER_VIEW_TAG";

    Categoriesadapter(List<Category> dataset, Context con,Connector connector) {
        context = con;
        this.data = dataset;
        this.connector=connector;

    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.categories_card_view, parent, false);
        CategoriesViewHolder myViewHolder = new CategoriesViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.categoryTextView.setText(data.get(position).getStrCategory());
        Glide.with(context).load(data.get(position).getStrCategoryThumb()).into(holder.categoryImageView);
        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, data.get(position).description, Toast.LENGTH_SHORT);
                connector.sendData(data.get(position).getStrCategory());



            }
        });
        Log.i(TAG, "onBindViewHolder2");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class CategoriesViewHolder extends RecyclerView.ViewHolder {
    public ImageView categoryImageView;
    public TextView categoryTextView;
    public CardView categoryCardView;
    public CategoriesViewHolder(View view) {
        super(view);
        this.categoryImageView = (ImageView) view.findViewById(R.id.category_image);
        this.categoryTextView =  view.findViewById(R.id.category_text);
        this.categoryCardView=(CardView) view.findViewById(R.id.category_card);
    }

}

