package com.example.sidechef.SignUpActivity.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidechef.HomeActivity.View.HomeActivity;
import com.example.sidechef.R;
import com.example.sidechef.SingInActivity.View.SignIn;
import com.example.sidechef.presenter.RegistrationPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener, RegisterViewInterface{
    TextView tvLogin;
    Button btnRegistration;
    EditText edtEmail, edtPassword , corPassword;
    RegistrationPresenter mRegisterPresenter;
    ProgressDialog mPrgressDialog;
Button btskup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        initViews();
    }


    private void initViews() {
        btnRegistration = findViewById(R.id.btn_register);
        btnRegistration.setOnClickListener(this);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
        edtEmail = findViewById(R.id.email_register);
        edtPassword = findViewById(R.id.password_register);
        corPassword=findViewById(R.id.password_register_cor)  ;
        mRegisterPresenter = new RegistrationPresenter(this);
        btskup = findViewById(R.id.btn_skip);
        btskup.setOnClickListener(this);
        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("Please wait, Adding profile to database.");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                checkRegistrationDetails();
                break;
            case R.id.tv_login:
                moveToLoginActivity();
                break;
        case R.id.btn_skip:
            startActivity(  new Intent(getApplicationContext(), HomeActivity.class));
        break;
    }

    }

    private void moveToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), SignIn.class);
        startActivity(intent);
    }
    public static boolean isValidEmail(CharSequence target) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        matcher = pattern.matcher(target);
        return (!TextUtils.isEmpty(target) && matcher.matches());
    }

    public static boolean isValidPassword(CharSequence password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.[0-9])(?=.[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void checkRegistrationDetails() {

        if(!TextUtils.isEmpty(edtEmail.getText().toString()) &&isValidEmail(edtEmail.getText().toString()) && isValidPassword(edtPassword.getText().toString()) && corPassword.getText().toString()==edtPassword.getText().toString() && !TextUtils.isEmpty(edtPassword.getText().toString())){
            initLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
        }else{
            if(TextUtils.isEmpty(edtEmail.getText().toString()) || !isValidEmail(edtEmail.getText().toString()) ){
                edtEmail.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(edtPassword.getText().toString()) || !isValidPassword(edtPassword.getText().toString())){
                edtPassword.setError("Please enter password");
            }if(TextUtils.isEmpty(corPassword.getText().toString()) || corPassword.getText().toString()!=edtPassword.getText().toString()){
                corPassword.setError("Please enter re_password correct");
            }
        }



    }

    private void initLogin(String email, String password) {
        mPrgressDialog.show();
        mRegisterPresenter.register(email, password);
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailure(String message) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}