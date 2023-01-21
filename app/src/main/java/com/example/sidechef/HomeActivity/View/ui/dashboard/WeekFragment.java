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
import com.example.sidechef.model.models.Meal;

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
    List<ParentItem> itemList
            = new ArrayList<>();


    List<Meal> ChildItemList
            = new ArrayList<>();

    ///////////////////////////////////////////////
    private List<ParentItem> ParentItemList()
    {

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

    private List<Meal> ChildItemList()
    {
       Meal  meal =new Meal( "52842", "Broccoli & Stilton soup",
                null, "Starter", "British", "Heat the rapeseed oil in a large saucepan and then add the onions. Cook on a medium heat until soft. Add a splash of water if the onions start to catch. Add the celery, leek, potato and a knob of butter. Stir until melted, then cover with a lid. Allow to sweat for 5 minutes. Remove the lid. Pour in the stock and add any chunky bits of broccoli stalk. Cook for 10 – 15 minutes until all the vegetables are soft. Add the rest of the broccoli and cook for a further 5 minutes. Carefully transfer to a blender and blitz until smooth. Stir in the stilton, allowing a few lumps to remain. Season with black pepper and serve.", "https://www.themealdb.com/images/media/meals/tvvxpv1511191952.jpg", null, "https://www.youtube.com/watch?v=_HgVLpmNxTY", "Rapeseed Oil", "Onion", "Celery", "Leek", "Potatoes", "Butter", "Vegetable Stock", "Broccoli",
                "Stilton Cheese", "", "", "", "", "", "", "", "", "", "", "", "2 tblsp ", "1 finely chopped ", "1", "1 sliced", "1 medium", "1 knob", "1 litre hot", "1 Head chopped", "140g", "", "", "",
                "", "", "", "", "", "", "", "",
                "https://www.bbcgoodfood.com/recipes/1940679/broccoli-and-stilton-soup", null, null,null);
        ChildItemList.add(meal);
        ChildItemList.add(meal);
        ChildItemList.add(meal);
        ChildItemList.add(meal);

        return ChildItemList;
    }




    }


