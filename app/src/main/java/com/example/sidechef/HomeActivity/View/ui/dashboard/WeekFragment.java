package com.example.sidechef.HomeActivity.View.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.HomeActivity.View.ui.favorit.CardViewAdapterFav;
import com.example.sidechef.R;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;

import java.util.ArrayList;
import java.util.List;


public class WeekFragment extends Fragment {
Repository repository;
    ParentItemAdapter
            parentItemAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository = Repository.getInstance(requireContext());
        //delete
        repository.getAllMealFirebase();
        RecyclerView
                ParentRecyclerViewItem
                = view.findViewById(
                R.id.parent_recyclerview);


        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                requireContext());


                parentItemAdapter
                = new ParentItemAdapter(
                ParentItemList(),requireContext());

        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);


    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ChildItemList(Week.Saturday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Saturday", weekMeals);
                itemList.add(item);

            }

        });
        ChildItemList(Week.Sunday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Sunday", weekMeals);
                itemList.add(item);

            }

        });
        ChildItemList(Week.Monday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Monday", weekMeals);
                itemList.add(item);

            }

        });
        ChildItemList(Week.Tuesday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Tuesday", weekMeals);
                itemList.add(item);

            }

        });
        ChildItemList(Week.Wednesday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Wednesday", weekMeals);
                itemList.add(item);

            }

        });
        ChildItemList(Week.Thursday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Thursday", weekMeals);
                itemList.add(item);

            }

        });
        ChildItemList(Week.Friday.toString()).observe(getViewLifecycleOwner(), new Observer<List<WeekMeals>>() {
            @Override
            public void onChanged(List<WeekMeals> weekMeals) {


                ParentItem item
                        = new ParentItem(
                        "Friday", weekMeals);
                itemList.add(item);
                parentItemAdapter.setItemList(itemList);
            }

        });

        return itemList;
    }

    private LiveData<List<WeekMeals>> ChildItemList(String day)
    {

        return  repository.getweek(day);


    }

}


