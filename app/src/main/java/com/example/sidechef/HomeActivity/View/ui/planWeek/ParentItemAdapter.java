package com.example.sidechef.HomeActivity.View.ui.planWeek;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.R;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.WeekMeals;

import java.util.List;

public class ParentItemAdapter extends RecyclerView
        .Adapter<ParentItemAdapter.ParentViewHolder> {
Context context;
    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();
    private List<ParentItem> itemList;
    ChildItemAdapter.AdapterConnector adapterConnector;

    ChildItemAdapter childItemAdapter;
    public void setItemList(List<ParentItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    ParentItemAdapter(List<ParentItem> itemList, Context contex, ChildItemAdapter.AdapterConnector adapterConnector)
    {
        context =contex;
        this.itemList = itemList;
        this.adapterConnector=adapterConnector;

    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the parent item
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.parent_item,
                        viewGroup, false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ParentViewHolder parentViewHolder,
            int position)
    {

        // Create an instance of the ParentItem
        // class for the given position
        ParentItem parentItem
                = itemList.get(position);

        // For the created instance,
        // get the title and set it
        // as the text for the TextView
        parentViewHolder
                .ParentItemTitle
                .setText(parentItem.getParentItemTitle());

        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                parentViewHolder
                        .ChildRecyclerView
                        .getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method

        layoutManager.setInitialPrefetchItemCount(
                        parentItem.getChildItemList().size());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
         childItemAdapter
                = new ChildItemAdapter(
                parentItem
                        .getChildItemList(),context,adapterConnector);
        parentViewHolder
                .ChildRecyclerView
                .setLayoutManager(layoutManager);
        parentViewHolder
                .ChildRecyclerView
                .setAdapter(childItemAdapter);
        parentViewHolder
                .ChildRecyclerView
                .setRecycledViewPool(viewPool);
    }

    // This method returns the number
    // of items we have added in the
    // ParentItemList i.e. the number
    // of instances we have created
    // of the ParentItemList
    @Override
    public int getItemCount()
    {

        return itemList.size();
    }

//    @Override
//    public void sendData(WeekMeals meal) {
//
//    }
//
//
//    @Override
//    public void callRepo(WeekMeals meal, int position) {
//       repository.deletedaily(meal);
//        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
//        if (childItemAdapter!=null && itemList.size()!=0) {
//            itemList.remove(position);
//            childItemAdapter.notifyDataSetChanged();
//            childItemAdapter.notifyItemRemoved(position);
//        }
//    }
//
//    @Override
//    public void onSuccessResponse(List<WeekMeals> meals) {
//
//    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    class ParentViewHolder
            extends RecyclerView.ViewHolder {

        private TextView ParentItemTitle;
        private RecyclerView ChildRecyclerView;

        ParentViewHolder(final View itemView)
        {
            super(itemView);

            ParentItemTitle
                    = itemView
                    .findViewById(
                            R.id.parent_item_title);
            ChildRecyclerView
                    = itemView
                    .findViewById(
                            R.id.child_recyclerview);
        }
    }
}
