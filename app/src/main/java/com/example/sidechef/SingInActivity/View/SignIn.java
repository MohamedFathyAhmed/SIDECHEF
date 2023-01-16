package com.example.sidechef.SingInActivity.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.sidechef.HomeActivity.View.HomeActivity;
import com.example.sidechef.R;
import com.example.sidechef.model.firebase.Login.LoginContract;
import com.example.sidechef.model.firebase.Login.LoginPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class SignIn extends AppCompatActivity implements View.OnClickListener, LoginContract.View {
    Button btn_login;
    EditText tv_name;
    EditText tv_pass;
    LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;

    SignInButton googeSignIn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private void initUI() {
        btn_login = findViewById(R.id.btn_register);
        tv_name = findViewById(R.id.email_register);
        tv_pass = findViewById(R.id.password_register);
        googeSignIn = findViewById(R.id.btn_signInWithGoogle);
        mLoginPresenter = new LoginPresenter(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
        btn_login.setOnClickListener(this);
        googeSignIn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // For google sign in
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(getApplicationContext(), gso);

        setContentView(R.layout.activity_sign_in);

        initUI();

    }

    private void checkLoginDetails() {
        if (!TextUtils.isEmpty(tv_name.getText().toString()) && !TextUtils.isEmpty(tv_pass.getText().toString())) {
            initLogin(tv_name.getText().toString(), tv_pass.getText().toString());
        } else {
            if (TextUtils.isEmpty(tv_name.getText().toString())) {
                tv_name.setError("Please enter a valid email");
            }
            if (TextUtils.isEmpty(tv_pass.getText().toString())) {
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
        switch (view.getId()) {
            case R.id.btn_register:
                Log.i("hellplab", "btn_login: ");
                checkLoginDetails();
                break;
            case R.id.btn_signInWithGoogle:
                // Google sign in button
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
    public void onLoginSuccess(String message) {
        mProgressDialog.dismiss();
        Log.i("hellplab", "onLoginSuccess: ");
        Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Log.i("hellplab", "onLoginFailure: ");
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    // For google sign in
    void signInGoogle() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    // callback of sign in with google request (for google sign in)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Sign in was successful", Toast.LENGTH_SHORT).show();

                // Next 2 lines were used to link google sign in with firebase
                AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(task.getResult().getIdToken(),
                        null);
                firebaseAuth.signInWithCredential(firebaseCredential);

                // Intent intent = new Intent(requireContext(), MainActivity.class);
                // startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                // Toast.makeText(requireContext(), "Sign in failed",
                // Toast.LENGTH_SHORT).show();
            }

        }
    }

}