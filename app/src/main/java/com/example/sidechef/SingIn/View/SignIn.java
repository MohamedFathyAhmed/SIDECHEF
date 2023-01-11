package com.example.sidechef.SingIn.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.sidechef.R;




public class SignIn extends AppCompatActivity {
Button btn_login;
TextView tv_name;
TextView tv_pass;
    private void initUI() {
        btn_login = findViewById(R.id.btn_login);
        tv_name = findViewById(R.id.tx_Name);
        tv_pass = findViewById(R.id.tx_pass);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initUI();


    }







}