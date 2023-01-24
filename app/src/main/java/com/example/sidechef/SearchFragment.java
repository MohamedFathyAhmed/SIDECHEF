package com.example.sidechef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.sidechef.HomeActivity.View.ui.home.HomeFragmentDirections;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements com.example.sidechef.SearchView {
   /* GridAdapter gridAdapter;
    GridView gridView;
    SearchView searchView;
    ArrayList<Meal> meals;
    SearchPresenter searchPresenter;*/
    CardView countryCardView;
    CardView ingredientCardView;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countryCardView=view.findViewById(R.id.search_by_country_id);
        ingredientCardView=view.findViewById(R.id.search_by_ingredient_id);
        countryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragmentDirections.ActionNavigationSearchToOnSearchBy action = SearchFragmentDirections
                        .actionNavigationSearchToOnSearchBy("country");
                action.setRequiredSearchBy("country");
                //Log.i("TAG", "onCreateView: " + meal.getStrArea());
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        ingredientCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragmentDirections.ActionNavigationSearchToOnSearchBy action = SearchFragmentDirections
                        .actionNavigationSearchToOnSearchBy("ingredient");
                action.setRequiredSearchBy("ingredient");
                //Log.i("TAG", "onCreateView: " + meal.getStrArea());
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        /*searchView=view.findViewById(R.id.searchView);
        gridView=view.findViewById(R.id.searchgrid);
        searchPresenter=new SearchPresenter(requireContext(),this);
        searchPresenter.getMealByName("Arrabiata",this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void OnGetMealByNameSuccess(Meals meals) {
       /* gridAdapter= new GridAdapter(requireContext(),meals.getmeals());
        gridView.setAdapter(gridAdapter);*/
    }

    @Override
    public void OnGetMealByNameFailure(String errorMessage) {

    }
}