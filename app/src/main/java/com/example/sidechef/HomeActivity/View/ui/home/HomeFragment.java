package com.example.sidechef.HomeActivity.View.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.StackView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sidechef.Connector;
import com.example.sidechef.R;

import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;

import java.util.List;

public class HomeFragment extends Fragment implements NetworkInterface , Connector {
    RecyclerView categoryrecyclerView;
    RecyclerView randomrecyclerView;
    CardViewAdapter adapter;
    NetworkPresenter networkPresenter;
    Categoriesadapter mAdapter;
    StackView stackView;

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
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initrandommealRecView(view);
        initCategoryRecView(view);
        stackView = (StackView) view.findViewById(R.id.stack_view);
        networkPresenter = new NetworkPresenter(requireContext(), this);
        networkPresenter.getMeals();
        networkPresenter.getCategories();

       // stackView
//       NestedScrollView nestedScrollView= ((NestedScrollView) view.findViewById(R.id.scrollView));
//        nestedScrollView.setEnabled(false);
//
//       nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (stackView.getHeight() < scrollY) {
//                    stackView.showNext();
//                }
//
//            }
//        });

//        stackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                stackView.showNext();
//            }
//
//        });
    }

    void initCategoryRecView(View view) {
        categoryrecyclerView = (RecyclerView) view.findViewById(R.id.ct_rv);
        categoryrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryrecyclerView.setLayoutManager(layoutManager);
    }

    void initrandommealRecView(View view) {
        randomrecyclerView = (RecyclerView) view.findViewById(R.id.meal_rv_fav);
        randomrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        randomrecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void navigate(Meal meal) {
        HomeFragmentDirections.ActionNavigationHomeToDetailFragment action = HomeFragmentDirections
                .actionNavigationHomeToDetailFragment(meal);
        action.setDataMeal(meal);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void callRepo(Meal meal, int position) {
        networkPresenter.insertItem(meal);
        Toast.makeText(requireContext(), "add to favorit" , Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccessResponse(List<Meals> mealsLists) {
        adapter = new CardViewAdapter(mealsLists, requireContext(), this);
        randomrecyclerView.setAdapter(adapter);
        slideAdapter slidadapter = new slideAdapter(mealsLists, R.layout.slide_iteam, getContext());
        stackView.setAdapter(slidadapter);

    }

    @Override
    public void onGetCategoriesSuccessResponse(Categories categories) {
        mAdapter = new Categoriesadapter(categories.getCategories(), requireContext(),this);
        categoryrecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onErrorResponse(String errorMessage) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendData(String keyword) {
        HomeFragmentDirections.ActionNavigationHomeToSearchView action=HomeFragmentDirections.actionNavigationHomeToSearchView("category",keyword);
        action.setRequiredSearch("category");
        action.setKeywordToSearch(keyword);
        //Log.i("TAG", "onCreateView: " + meal.getStrArea());
        Navigation.findNavController(getView()).navigate(action);
    }
}
