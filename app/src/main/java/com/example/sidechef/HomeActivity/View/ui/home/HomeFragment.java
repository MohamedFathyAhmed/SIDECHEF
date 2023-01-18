package com.example.sidechef.HomeActivity.View.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.R;

import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.List;

public class HomeFragment extends Fragment implements NetworkInterface {
    RecyclerView categoryrecyclerView;
    RecyclerView randomrecyclerView;
    CardViewAdapter adapter;
    NetworkPresenter networkPresenter;
    Categoriesadapter mAdapter;

    Meal meal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initrandommealRecView(view);
        initCategoryRecView(view);
        networkPresenter = new NetworkPresenter(requireContext(),this);
        networkPresenter.getMeals();
        networkPresenter.getCategories();

    }

    void initCategoryRecView(View view) {
        categoryrecyclerView = (RecyclerView) view.findViewById(R.id.ct_rv);
        categoryrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryrecyclerView.setLayoutManager(layoutManager);
    }

    void initrandommealRecView(View view) {
        randomrecyclerView = (RecyclerView) view.findViewById(R.id.meal_rv);
        randomrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        randomrecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void sendData(Meal meal) {
        HomeFragmentDirections.ActionNavigationHomeToDetailFragment action =  HomeFragmentDirections.actionNavigationHomeToDetailFragment(meal);
        action.setDataMeal(meal);
        Log.i("TAG", "onCreateView: "+meal.getStrArea());
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void callRepo(Meal meal, int position) {
        networkPresenter.insertItem(meal);
       adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessResponse(List<Meals> mealsLists) {
       adapter = new CardViewAdapter(mealsLists,requireContext(),this);
       randomrecyclerView.setAdapter(adapter);

    }

    @Override
    public void onGetCategoriesSuccessResponse(Categories categories) {

        mAdapter = new Categoriesadapter(categories.getCategories(), requireContext());
        categoryrecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onErrorResponse(String errorMessage) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}

