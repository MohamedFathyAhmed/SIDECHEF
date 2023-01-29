package com.example.sidechef.SingInActivity.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.sidechef.ForgetPassword;
import com.example.sidechef.HomeActivity.View.HomeActivity;
import com.example.sidechef.R;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.presenter.LoginPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignIn extends AppCompatActivity implements View.OnClickListener, LoginViewinterface {
    Button btn_login;
    EditText tv_name;
    EditText tv_pass;
    LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;
     SignInButton googeSignIn;
     GoogleSignInOptions gso;
     GoogleSignInClient gsc;
     TextView forgetPassword;

     FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();





    private void initUI() {
        btn_login = findViewById(R.id.btn_forget_password);
        tv_name = findViewById(R.id.email_forget_password);
        tv_pass = findViewById(R.id.password_register);
        googeSignIn = findViewById(R.id.btn_signInWithGoogle);
        forgetPassword=findViewById(R.id.forgetPassword);
        mLoginPresenter = new LoginPresenter(this,this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
        btn_login.setOnClickListener(this);
        googeSignIn.setOnClickListener(this);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //For google sign in
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(getApplicationContext(), gso);

       setContentView(R.layout.activity_sign_in);
        initUI();
        tv_name.setText("fathy@gmail.com");
        tv_pass.setText("123456");
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

        final String PASSWORD_PATTERN = "^(?=.[0-9]).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void checkLoginDetails() {

//
        if(!TextUtils.isEmpty(tv_name.getText().toString())  && isValidEmail(tv_name.getText().toString())  && isValidPassword(tv_pass.getText().toString()) && !TextUtils.isEmpty(tv_pass.getText().toString())){
            initLogin(tv_name.getText().toString(), tv_pass.getText().toString());
        }else{
            if(TextUtils.isEmpty(tv_name.getText().toString()) || !isValidEmail(tv_name.getText().toString()) ){
                tv_name.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(tv_pass.getText().toString()) || !isValidPassword(tv_pass.getText().toString())){
               tv_pass.setError("Please enter password");
            }
        }
    }
    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(email, password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_forget_password:
                Log.i("hellplab", "btn_login: ");
               checkLoginDetails();
                break;
            case R.id.btn_signInWithGoogle:
                //Google sign in button
                Log.i("hellplab", "btn_login: ");
                signInGoogle();
                break;
        }
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onLoginSuccess(String message , String email) {
        YourPreference yourPrefrence = YourPreference.getInstance(getApplicationContext());
        yourPrefrence.saveData("email",email);
        mProgressDialog.dismiss();
      Toast.makeText(getApplicationContext(), "Successfully Logged in" , Toast.LENGTH_SHORT).show();
  finish();
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
    }

    //For google sign in
    void signInGoogle() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            firebaseAuth.signInWithCredential(GoogleAuthProvider.getCredential(task.getResult().getIdToken(), null)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        YourPreference yourPrefrence = YourPreference.getInstance(getApplicationContext());
                        yourPrefrence.saveData("email",  task.getResult().getUser().getEmail());
                        Toast.makeText(getApplicationContext(), "Sign in was successful", Toast.LENGTH_SHORT).show();
                        mLoginPresenter.setDataFromFirebase();
                        startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

