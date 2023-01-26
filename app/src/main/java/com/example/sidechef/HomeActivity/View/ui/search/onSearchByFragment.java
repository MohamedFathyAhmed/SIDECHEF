package com.example.sidechef.HomeActivity.View.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.sidechef.R;
import com.example.sidechef.model.models.Country;
import com.example.sidechef.model.models.CountryListResponse;
import com.example.sidechef.model.models.Ingredient;
import com.example.sidechef.model.models.IngredientResponse;

import java.util.ArrayList;
import java.util.List;


public class onSearchByFragment extends Fragment implements OnSearchByView, Connector {


    String requiredSearch;
    RecyclerView myRecyclerView;
    OnSearchByCountryRecViewAdapter adapter;
    OnSearchByIngredientRecViewAdapter onSearchByIngredientRecViewAdapter;
    List<Country> countryList=new ArrayList<Country>();
    List<Ingredient>ingredientList=new ArrayList<Ingredient>();
    SearchView mySearchView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on__search_by, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requiredSearch = onSearchByFragmentArgs.fromBundle(getArguments()).getRequiredSearchBy();
        myRecyclerView = (RecyclerView) view.findViewById(R.id.onSearchByRec);
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        OnSearchByPresenter onSearchByPresenter= new OnSearchByPresenter(requireContext(),this);
        if(requiredSearch.equals("country")){

            onSearchByPresenter.getAllCountries();
            mySearchView=view.findViewById(R.id.search_view_id);
            mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    filterCountryList(newText);
                    return false;
                }
            });



        }else {
            onSearchByPresenter.getAllIngredients();
            mySearchView=view.findViewById(R.id.search_view_id);
            mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    filterIngredientList(newText);
                    return false;
                }
            });

        }
    }

    @Override
    public void OnGetIngredientListSuccess(IngredientResponse ingredientResponse) {
        ingredientList=ingredientResponse.getmeals();
        onSearchByIngredientRecViewAdapter = new OnSearchByIngredientRecViewAdapter(ingredientList, requireContext(),this);
        myRecyclerView.setAdapter(onSearchByIngredientRecViewAdapter);
        System.out.println(ingredientResponse.getmeals().get(5).getStrIngredient());

    }

    @Override
    public void OnGetIngredientListFailure(String errorMessage) {

    }



    private void filterCountryList(String text) {
        ArrayList<Country> filteredlist = new ArrayList<Country>();
        for (Country item : countryList) {
            if (item.getStrArea().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            adapter.filterList(filteredlist);
        }
    }
    private void filterIngredientList(String text) {
        ArrayList<Ingredient> filteredlist = new ArrayList<Ingredient>();
        for (Ingredient item : ingredientList) {
            if (item.getStrIngredient().toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            onSearchByIngredientRecViewAdapter.filterList(filteredlist);
        }
    }
    @Override
    public void OnGetCountryListSuccess(CountryListResponse countryListResponse) {
        countryList=countryListResponse.getmeals();
        adapter = new OnSearchByCountryRecViewAdapter(countryList, requireContext(),this);
        myRecyclerView.setAdapter(adapter);
        System.out.println(countryListResponse.getmeals().get(5).getStrArea());

    }

    @Override
    public void OnGetCountryListFailure(String errorMessage) {

    }

    @Override
    public void sendData(String keyword) {
        onSearchByFragmentDirections.ActionOnSearchByToSearchView action = onSearchByFragmentDirections.actionOnSearchByToSearchView(requiredSearch, keyword);

        action.setRequiredSearch(requiredSearch);
        action.setKeywordToSearch(keyword);
        //Log.i("TAG", "onCreateView: " + meal.getStrArea());
        Navigation.findNavController(getView()).navigate(action);
    }
}