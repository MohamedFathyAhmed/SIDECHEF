package com.example.sidechef.HomeActivity.View.ui.planWeek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.R;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;

import java.util.ArrayList;
import java.util.List;


public class WeekFragment extends Fragment implements WeekInterface {
Repository repository;
    ParentItemAdapter parentItemAdapter;

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
                ParentItemList(),requireContext(),this);

        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);


    }
    List<ParentItem> itemList
            = new ArrayList<>();


    private List<ParentItem> ParentItemList()
    {

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

    private synchronized LiveData<List<WeekMeals>> ChildItemList(String day)
    {
        return  repository.getweek(day);
    }


    @Override
    public void sendData(WeekMeals meal) {

    }

    @Override
    public void callRepo(WeekMeals meal, int position) {
        itemList = new ArrayList<>();
        repository.deletedaily(meal);
        Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessResponse(List<WeekMeals> meals) {

    }
}


