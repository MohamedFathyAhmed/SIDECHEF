package com.example.sidechef.HomeActivity.View.ui.user;

import android.content.Intent;
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
import android.widget.LinearLayout;

import com.example.sidechef.R;
import com.example.sidechef.SignUpActivity.View.SignUp;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class UserFragment extends Fragment implements  UserViewInterface{


    LinearLayout linearLayout ;
    UserPresenter userPresenter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hidenbarguest();
        linearLayout=view.findViewById(R.id.logout_id);
        userPresenter=new UserPresenter(requireContext(),this);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPresenter.deleteTableRoom();
                userPresenter.deleteWeekRoom();
                userPresenter.onDelete();

            }
        });
    }
    @Override
    public void onDelete() {
        YourPreference.getInstance(requireContext()).removeData("email");
        Intent intent =new Intent(requireContext(), SignUp.class);
        startActivity(intent);
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



