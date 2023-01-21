package com.example.sidechef.HomeActivity.View.ui.favorit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.sidechef.model.models.Meal;

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
        recyclerView.setLayoutManager(new  GridLayoutManager(requireContext(), 2));
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
//        favoritPresenter.deleteItem(meal);
//        Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show();
//
//        if (adapter!=null && mealsList.size()!=0) {
//            mealsList.remove(position);
//            adapter.notifyDataSetChanged();
//            adapter.notifyItemRemoved(position);
//        }
    }

    @Override
    public void onSuccessResponse(List<Meal> meal) {
      adapter = new CardViewAdapterFav(meal,requireContext() ,this);
        recyclerView.setAdapter(adapter);
        Log.i("TAGfav", "onSuccessResponse: "+meal.get(0).getStrArea());

    }

}