package com.example.sidechef.HomeActivity.View.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.R;

import java.util.ArrayList;
import java.util.List;


public class WeekFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView
                ParentRecyclerViewItem
                = view.findViewById(
                R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                requireContext());

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        ParentItemAdapter
                parentItemAdapter
                = new ParentItemAdapter(
                ParentItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);

    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ParentItem item
                = new ParentItem(
                "Saturday",
                ChildItemList());
        itemList.add(item);
        ParentItem item1
                = new ParentItem(
                "sunday",
                ChildItemList());
        itemList.add(item1);
        ParentItem item2
                = new ParentItem(
                "monday",
                ChildItemList());
        itemList.add(item2);
        ParentItem item3
                = new ParentItem(
                "tuesday",
                ChildItemList());
        itemList.add(item3);
        ParentItem item4
                = new ParentItem(
                "wednesday",
                ChildItemList());
        itemList.add(item4);
        ParentItem item5
                = new ParentItem(
                "thursday",
                ChildItemList());
        itemList.add(item5);
        ParentItem item6
                = new ParentItem(
                "friday",
                ChildItemList());
        itemList.add(item6);
        return itemList;
    }

    private List<ChildItem> ChildItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new ChildItem("Card 1"));
        ChildItemList.add(new ChildItem("Card 2"));
        ChildItemList.add(new ChildItem("Card 3"));
        ChildItemList.add(new ChildItem("Card 4"));

        return ChildItemList;
    }




    }


