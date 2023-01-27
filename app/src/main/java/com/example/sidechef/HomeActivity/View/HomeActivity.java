package com.example.sidechef.HomeActivity.View;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sidechef.MainActivity;
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
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity {
     int timerInternetIsConnected ;
    private ActivityHomeBinding binding;
    BottomNavigationView navBar;
TextView tv_internetConnection;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding =  ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        Timer t = new Timer( );
        tv_internetConnection = findViewById(R.id.tx_internet);
        navBar = findViewById(R.id.nav_view);
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!Utils.isNetworkAvailable( getBaseContext())){
                    HomeActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_internetConnection.setVisibility(View.VISIBLE);
                            tv_internetConnection.setText("No Internet Connection");
                            tv_internetConnection.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            timerInternetIsConnected = 5001;


                            navBar.setVisibility(View.GONE);
                        }
                    });

                } else{
                    HomeActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(timerInternetIsConnected > 0){
                                timerInternetIsConnected = timerInternetIsConnected - 500;
                                tv_internetConnection.setBackgroundColor(getResources().getColor(R.color.blue));
                                tv_internetConnection.setText("Internet is back!");
                            } else{
                                tv_internetConnection.setVisibility(View.GONE);
                                navBar.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                }

            }
        }, 1000,500);

    }



}