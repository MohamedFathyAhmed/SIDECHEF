package com.example.sidechef.HomeActivity.View.ui.favorit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.HomeActivity.View.ui.home.CardViewAdapter;
import com.example.sidechef.HomeActivity.View.ui.home.HomeFragmentDirections;
import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Time;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FavoritFragment extends Fragment implements FavoritInterface {

    private RecyclerView recyclerView;
    private CardViewAdapterFav adapter;
    private FavoritPresenter favoritPresenter;
    private List<Meal> mealsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hidenbarguest();
        recyclerView = view.findViewById(R.id.meal_rv_fav);
        favoritPresenter = new FavoritPresenter(requireContext(),this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new  GridLayoutManager(requireContext(), 1));
        favoritPresenter.fetchData();

    }


    @Override
    public void sendData(Meal meal) {
 FavoritFragmentDirections.ActionNavigationFavoriteToNavigationDes action = FavoritFragmentDirections.actionNavigationFavoriteToNavigationDes(meal);
      Navigation.findNavController(getView()).navigate(action);

    }

    @Override
    public void callRepo(Meal meal, int position) {
        favoritPresenter.deleteItem(meal);
        if (adapter!=null && mealsList.size()!=0) {
            mealsList.remove(position);
            adapter.notifyDataSetChanged();
            adapter.notifyItemRemoved(position);
        }
    }

    @Override
    public void addToPlan(Meal meal) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(requireContext());
        builderSingle.setIcon(R.drawable.ic_dashboard_black_24dp);
        builderSingle.setTitle("Select day:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(Week.Saturday.toString());
        arrayAdapter.add(Week.Sunday.toString());
        arrayAdapter.add(Week.Monday.toString());
        arrayAdapter.add(Week.Tuesday.toString());
        arrayAdapter.add(Week.Wednesday.toString());
        arrayAdapter.add(Week.Thursday.toString());
        arrayAdapter.add(Week.Friday.toString());
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInnertime = new AlertDialog.Builder(requireContext());
                builderInnertime.setTitle("Select time:-");
                builderInnertime.setIcon(R.drawable.baseline_timer_24);

                final ArrayAdapter<String> arrayAdapterday = new ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice);
                arrayAdapterday.add(Time.Breakfast.toString());
                arrayAdapterday.add(Time.Dinner.toString());
                arrayAdapterday.add(Time.Lunch.toString());

                builderInnertime.setAdapter(arrayAdapterday, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strNameDay = arrayAdapterday.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(requireContext());
                        builderInner.setMessage(strName + " at " + strNameDay + " to your plan");
                        builderInner.setTitle("Your add " + meal.getStrMeal() );
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                WeekMeals weekMeals = Utils.converter(strName,strNameDay,meal);
                                favoritPresenter.addToPlan(weekMeals);
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();

                    }
                });
                builderInnertime.show();
            }
        });
        builderSingle.show();
    }


    @Override
    public void onSuccessResponse(List<Meal> meal) {
      adapter = new CardViewAdapterFav(meal,requireContext() ,this);
        recyclerView.setAdapter(adapter);

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