package com.example.sidechef.HomeActivity.View.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.models.Meals;

import java.util.List;


public class IngredientsAdapter extends  RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    Context context;
    List<String> values;

    public IngredientsAdapter(Context context, List<String> values) {
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.ingredients_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingTxt.setText(values.get(position).toString());
        String[] s=values.get(position).split(" : " );
        Glide.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",s[0]))
                .placeholder(R.drawable.ic_launcher_foreground).into(holder.ingImg);

    }



    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ingImg;
        TextView ingTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingImg=itemView.findViewById(R.id.ingredient_img);
            ingTxt=itemView.findViewById(R.id.ingredient_text);


        }
    }
}
