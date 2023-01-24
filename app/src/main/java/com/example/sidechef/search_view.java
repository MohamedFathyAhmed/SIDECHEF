package com.example.sidechef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.sidechef.model.models.FilterResponseModel;
import com.example.sidechef.model.models.FilteredMeal;

import java.util.ArrayList;


public class search_view extends Fragment implements  SearchView{

    String requiredSearch;
    String searchKeyword;
     GridAdapter gridAdapter;
    GridView gridView;
    androidx.appcompat.widget.SearchView searchView;
    ArrayList<FilteredMeal> meals;
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
        gridAdapter= new GridAdapter(requireContext(),meals.getMeals());
        gridView.setAdapter(gridAdapter);
    }

    @Override
    public void OnGetMealByFilterFailure(String errorMessage) {

    }
}