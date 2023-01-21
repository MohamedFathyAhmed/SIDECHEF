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

import com.example.sidechef.HomeActivity.View.ui.favorit.CardViewAdapterFav;
import com.example.sidechef.R;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;

import java.util.ArrayList;
import java.util.List;


public class WeekFragment extends Fragment implements WeekInterface {
Repository repository;

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
                ParentItemList(),requireContext());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);

    }
    ///////////////////////////////////////////


    ///////////////////////////////////////////////
    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();


        ParentItem item
                = new ParentItem(
                "Saturday",
                ChildItemList(Week.Sunday.toString()));
        itemList.add(item);
//        ParentItem item1
//                = new ParentItem(
//                "sunday",
//                ChildItemList(Week.Sunday.toString()));
//        itemList.add(item1);
//        ParentItem item2
//                = new ParentItem(
//                "monday",
//                ChildItemList(Week.Sunday.toString()));
//        itemList.add(item2);
//        ParentItem item3
//                = new ParentItem(
//                "tuesday",
//                ChildItemList(Week.Sunday.toString()));
//        itemList.add(item3);
//        ParentItem item4
//                = new ParentItem(
//                "wednesday",
//                ChildItemList(Week.Sunday.toString()));
//        itemList.add(item4);
//        ParentItem item5
//                = new ParentItem(
//                "thursday",
//                ChildItemList(Week.Sunday.toString()));
//        itemList.add(item5);
//        ParentItem item6
//                = new ParentItem(
//                "friday",
//                ChildItemList(Week.Sunday.toString()));
//        itemList.add(item6);
        return itemList;
    }

    private List<WeekMeals> ChildItemList(String day)
    {
        List<WeekMeals> ChildItemList
                = new ArrayList<>();

       // ChildItemList = repository.getweek(day);
        ChildItemList.add(new WeekMeals());
        return ChildItemList;
    }


    @Override
    public void sendData(Meal meal) {

    }

    @Override
    public void callRepo(Meal meal, int position) {

    }

    @Override
    public void onSuccessResponse(List<WeekMeals> meals) {

    }
}


