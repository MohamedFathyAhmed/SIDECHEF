package com.example.sidechef.HomeActivity.View.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hidenbarguest();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
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



