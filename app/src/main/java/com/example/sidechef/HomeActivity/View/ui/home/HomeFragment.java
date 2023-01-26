package com.example.sidechef.HomeActivity.View.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sidechef.HomeActivity.View.ui.search.Connector;
import com.example.sidechef.R;

import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.model.models.Categories;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Meals;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements NetworkInterface , Connector {
    RecyclerView categoryrecyclerView;
    RecyclerView randomrecyclerView;
    CardViewAdapter adapter;
    NetworkPresenter networkPresenter;
    Categoriesadapter mAdapter;
    //StackView stackView;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    Meal meal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          hidenbarguest();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initrandommealRecView(view);
        initCategoryRecView(view);
        //stackView = (StackView) view.findViewById(R.id.stack_view);
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });

        networkPresenter = new NetworkPresenter(requireContext(), this);
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
        SliderAdapter sliderdapter = new SliderAdapter(mealsLists, viewPager2,requireContext());
        viewPager2.setAdapter(sliderdapter);


    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
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
