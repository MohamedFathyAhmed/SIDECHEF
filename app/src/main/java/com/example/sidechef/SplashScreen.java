package com.example.sidechef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


import com.example.sidechef.HomeActivity.View.HomeActivity;
import com.example.sidechef.SignUpActivity.View.SignUp;
import com.example.sidechef.SingInActivity.View.SignIn;
import com.example.sidechef.Utils.Utils;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
     //   if (!Utils.isNetworkAvailable(getApplicationContext())) {

//alert here if no net

      //  } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences preferences = getSharedPreferences("sharedPreferences", 0);
                    String value = preferences.getString("first_time", null);
                    if (value == null) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        SharedPreferences.Editor pre = preferences.edit();
                        pre.putString("first_time", "no");
                        pre.apply();
                    } else {
                        if (preferences.getString("email", "guest").equals("guest")) {
                            startActivity(new Intent(SplashScreen.this, SignUp.class));
                        } else {
                            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                        }

                    }
                }
            }, 0);

     //   }
    }
}