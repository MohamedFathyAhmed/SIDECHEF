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
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.HomeActivity.View.ui.home.CardViewAdapter;
import com.example.sidechef.HomeActivity.View.ui.home.HomeFragmentDirections;
import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Time;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;

import java.util.ArrayList;
import java.util.List;


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
        recyclerView = view.findViewById(R.id.meal_rv_fav);
        favoritPresenter = new FavoritPresenter(requireContext(),this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new  GridLayoutManager(requireContext(), 1));
        favoritPresenter.fetchData();

    }


    @Override
    public void sendData(Meal meal) {
 FavoritFragmentDirections.ActionNavigationFavoriteToNavigationDes action = FavoritFragmentDirections.actionNavigationFavoriteToNavigationDes(meal);
        Log.i("TAG", "onCreateView: " + meal.getStrArea());
        Navigation.findNavController(getView()).navigate(action);

    }

    @Override
    public void callRepo(Meal meal, int position) {
        favoritPresenter.deleteItem(meal);
        Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builderInnertime = new AlertDialog.Builder(requireContext());
                builderInnertime.setTitle("Select time:-");
                builderInnertime.setIcon(R.drawable.baseline_timer_24);

                final ArrayAdapter<String> arrayAdapterday = new ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice);
                arrayAdapterday.add(Time.Saturday.toString());
                arrayAdapterday.add(Time.Sunday.toString());
                arrayAdapterday.add(Time.Monday.toString());

                builderInnertime.setAdapter(arrayAdapterday, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        String strNameDay = arrayAdapterday.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(requireContext());
                        builderInner.setMessage(strName + " at " + strNameDay + " to your plan");
                        builderInner.setTitle("Your add " + meal.getStrMeal() );
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                WeekMeals weekMeals = Utils.converter(strName,"breakfast",meal);
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
        Log.i("TAGfav", "onSuccessResponse: "+meal.get(0).getStrArea());

    }

}