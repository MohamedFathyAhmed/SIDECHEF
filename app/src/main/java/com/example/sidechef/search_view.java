package com.example.sidechef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sidechef.model.models.Country;
import com.example.sidechef.model.models.FilterResponseModel;
import com.example.sidechef.model.models.FilteredMeal;

import java.util.ArrayList;
import java.util.List;


public class search_view extends Fragment implements  SearchView{

    String requiredSearch;
    String searchKeyword;
     GridAdapter gridAdapter;
    GridView gridView;
    androidx.appcompat.widget.SearchView searchView;
    List<FilteredMeal> meals=new ArrayList<FilteredMeal>();
    SearchPresenter searchPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requiredSearch=search_viewArgs.fromBundle(getArguments()).getRequiredSearch();
        searchKeyword=search_viewArgs.fromBundle(getArguments()).getKeywordToSearch();
        searchView=view.findViewById(R.id.search_view_id);
        gridView=view.findViewById(R.id.searchgrid);
        searchPresenter=new SearchPresenter(requireContext(),this);
        searchView=view.findViewById(R.id.searchID);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterMealsList(newText);
                return false;
            }
        });


        if (requiredSearch.equals("ingredient")){
            searchPresenter.getMealByIngredient(searchKeyword,this);

        } else if (requiredSearch.equals("category")) {
            searchPresenter.getMealByCategory(searchKeyword,this);

        }else {
            searchPresenter.getMealByCountry(searchKeyword,this);
        }
    }

    @Override
    public void OnGetMealByFilterSuccess(FilterResponseModel meals) {
        this.meals=meals.getMeals();
        gridAdapter= new GridAdapter(requireContext(),this.meals);
        gridView.setAdapter(gridAdapter);
    }

    @Override
    public void OnGetMealByFilterFailure(String errorMessage) {

    }
    private void filterMealsList(String text) {
        ArrayList<FilteredMeal> filteredlist = new ArrayList<FilteredMeal>();
        for (FilteredMeal item : this.meals) {
            if (item.getStrMeal().toLowerCase().startsWith(text.toLowerCase())) {

                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            gridAdapter=new GridAdapter(requireContext(),filteredlist);
            gridView.setAdapter(gridAdapter);
        }
    }
}