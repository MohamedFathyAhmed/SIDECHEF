package com.example.sidechef.HomeActivity.View;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;

import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding =  ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration;
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_planweek, R.id.navigation_favorite,R.layout.fragment_search)
                .build();
        View fav ,plan,home;
        fav = findViewById(R.id.navigation_favorite);
        plan = findViewById(R.id.navigation_planweek);
        home = findViewById(R.id.navigation_home);
        String email  = YourPreference.getInstance(this).getData("email");
        if( email.equals("") || (!Utils.isNetworkAvailable(this) )){
            fav.setVisibility(View.GONE);
            plan.setVisibility(View.GONE);
        }else {
            fav.setVisibility(View.VISIBLE);
            plan.setVisibility(View.VISIBLE);
        }


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



    }

}