package com.example.sidechef.HomeActivity.View.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class SearchFragment extends Fragment {
   /* GridAdapter gridAdapter;
    GridView gridView;
    SearchView searchView;
    ArrayList<Meal> meals;
    SearchPresenter searchPresenter;*/
    CardView countryCardView;
    CardView ingredientCardView;
    CardView nameCardView;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        hidenbarguest();
        countryCardView=view.findViewById(R.id.search_by_country_id);
        ingredientCardView=view.findViewById(R.id.search_by_ingredient_id);
        nameCardView=view.findViewById(R.id.search_by_name_id);
        nameCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(getView()).navigate(SearchFragmentDirections.actionNavigationSearchToSearchByName());
            }
        });
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

    public void hidenbarguest() {
        View fav ,plan,home;
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).hide();

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration;
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_planweek, R.id.navigation_favorite,R.layout.fragment_search)
                .build();

        fav = getActivity().findViewById(R.id.navigation_favorite);
        plan = getActivity().findViewById(R.id.navigation_planweek);
        home =getActivity().findViewById(R.id.navigation_home);
        String email  = YourPreference.getInstance(requireActivity()).getData("email");
        if( email.equals("") || (!Utils.isNetworkAvailable(requireActivity()) )){
            fav.setVisibility(View.GONE);
            plan.setVisibility(View.GONE);
        }else {
            fav.setVisibility(View.VISIBLE);
            plan.setVisibility(View.VISIBLE);
        }


        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) requireActivity(), navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
}