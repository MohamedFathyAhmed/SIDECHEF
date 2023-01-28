package com.example.sidechef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.sidechef.HomeActivity.View.ui.home.HomeFragmentDirections;
import com.example.sidechef.HomeActivity.View.ui.search.Connector;
import com.example.sidechef.HomeActivity.View.ui.search.GridAdapter;
import com.example.sidechef.HomeActivity.View.ui.search.SearchPresenter;
import com.example.sidechef.model.models.FilteredMeal;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.ArrayList;
import java.util.List;

public class searchByName extends Fragment implements SearchByNameView, GridViewAdapter.AdapterConnector {

    GridViewAdapter gridAdapter;
    GridView gridView;
    androidx.appcompat.widget.SearchView searchView;
    List<Meal> meals=new ArrayList<Meal>();
    Meal selectedMeal;
    SearchByNamePresenter searchByNamePresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView=view.findViewById(R.id.searchgrid);
        searchByNamePresenter=new SearchByNamePresenter(requireContext(),this);
        searchView=view.findViewById(R.id.searchID);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                meals=new ArrayList<>();
                if(!newText.equals("")){
                    searchByNamePresenter.getMealByName(newText);
                }else {
                    gridAdapter= new GridViewAdapter(requireContext(),searchByName.this.meals,searchByName.this);
                    gridView.setAdapter(gridAdapter);
                }


                return false;
            }
        });
    }

    @Override
    public void OnGetMealByNameSuccess(Meals meals) {
        this.meals=meals.getmeals();
        gridAdapter= new GridViewAdapter(requireContext(),this.meals,this);
        gridView.setAdapter(gridAdapter);

    }

    @Override
    public void OnGetMealByNameFailure(String errorMessage) {

    }

    @Override
    public void navigate(Meal meal) {
        searchByNameDirections.ActionSearchByNameToNavigationDes action = searchByNameDirections
                .actionSearchByNameToNavigationDes(meal);
        action.setDataMeal(meal);
        Navigation.findNavController(getView()).navigate(action);
    }
}