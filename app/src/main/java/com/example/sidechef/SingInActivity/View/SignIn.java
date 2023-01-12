package com.example.sidechef.SingInActivity.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.sidechef.R;
import com.example.sidechef.model.firebase.Login.LoginContract;
import com.example.sidechef.model.firebase.Login.LoginPresenter;


public class SignIn extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
Button btn_login;
EditText tv_name;
    EditText tv_pass;
    LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;




    private void initUI() {
        btn_login = findViewById(R.id.btn_login);
        tv_name = findViewById(R.id.tx_Name);
        tv_pass = findViewById(R.id.tx_pass);
        mLoginPresenter = new LoginPresenter(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initUI();

    }


    private void checkLoginDetails() {
        if(!TextUtils.isEmpty(tv_name.getText().toString()) && !TextUtils.isEmpty(tv_pass.getText().toString())){
            initLogin(tv_name.getText().toString(), tv_pass.getText().toString());
        }else{
            if(TextUtils.isEmpty(tv_name.getText().toString())){
                tv_name.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(tv_pass.getText().toString())){
               tv_pass.setError("Please enter password");
            }
        }
    }
    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }

    @Override
    public void onClick(android.view.View view) {
        switch (view.getId()){
            case R.id.btn_login:
                Log.i("hellplab", "btn_login: ");
              //  initLogin(tv_name.getText().toString(),tv_pass.getText().toString());
                checkLoginDetails();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Log.i("hellplab", "onLoginSuccess: ");
        Toast.makeText(getApplicationContext(), "Successfully Logged in" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Log.i("hellplab", "onLoginFailure: ");
        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
    }
}