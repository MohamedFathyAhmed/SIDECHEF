package com.example.sidechef.HomeActivity.View.ui.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sidechef.R;
import com.example.sidechef.model.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class OnSearchByIngredientRecViewAdapter extends RecyclerView.Adapter<OnSearchByViewHolder> {
    private List<Ingredient> data;
    private Context context;
    Connector adapterConnector;

    private static final String TAG = "RECYCLER_VIEW_TAG";

    OnSearchByIngredientRecViewAdapter(List<Ingredient> dataset, Context con, Connector adapterConnector) {
        this.context = con;
        this.data = dataset;
        this.adapterConnector=adapterConnector;

    }
    public void filterList(ArrayList<Ingredient> filterlist) {

        data = filterlist;

        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public OnSearchByViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.search_by_item, parent, false);
        OnSearchByViewHolder myViewHolder = new OnSearchByViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OnSearchByViewHolder holder, int position) {

        holder.myCardView.setOnClickListener(view -> adapterConnector.sendData(data.get(position).getStrIngredient()));
        holder.myTextView.setText(data.get(position).getStrIngredient());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}

class OnSearchByIngredientViewHolder extends RecyclerView.ViewHolder {
    CardView myCardView;
    TextView myTextView;

    public OnSearchByIngredientViewHolder(View itemView) {
        super(itemView);
        myCardView=itemView.findViewById(R.id.search_by_card);
        myTextView=itemView.findViewById(R.id.search_by_text);
    }

}

