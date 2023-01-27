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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        hidenbarguest();
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
    }

    @Override
    public void onSuccessResponse(List<WeekMeals> meals) {

    }
    public void hidenbarguest() {
        View fav ,plan,home,search;
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).hide();

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration;
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_planweek, R.id.navigation_favorite,R.layout.fragment_search)
                .build();

        fav = getActivity().findViewById(R.id.navigation_favorite);
        plan = getActivity().findViewById(R.id.navigation_planweek);
        home =getActivity().findViewById(R.id.navigation_home);
        search=getActivity().findViewById(R.id.navigation_search);
        String email  = YourPreference.getInstance(requireActivity()).getData("email");
        if( email.equals("")){
            fav.setVisibility(View.GONE);
            plan.setVisibility(View.GONE);
        }else {
            fav.setVisibility(View.VISIBLE);
            plan.setVisibility(View.VISIBLE);
        }
        if(!Utils.isNetworkAvailable(getContext())){
            search.setVisibility(View.GONE);
        }else {
            search.setVisibility(View.VISIBLE);
        }


        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) requireActivity(), navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
}


