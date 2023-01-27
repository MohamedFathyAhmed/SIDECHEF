package com.example.sidechef.SignUpActivity.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.presenter.RegistrationPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener, RegisterViewInterface{
    TextView tvLogin;
    Button btnRegistration;

    EditText edtEmail, edtPassword ;

    SignInButton signUpBtn;
    //EditText edtEmail, edtPassword , corPassword;

    RegistrationPresenter mRegisterPresenter;
    ProgressDialog mPrgressDialog;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button btskup;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(getApplicationContext(), gso);
        initViews();
    }


    private void initViews() {
        btnRegistration = findViewById(R.id.btn_forget_password);
        btnRegistration.setOnClickListener(this);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
        edtEmail = findViewById(R.id.email_forget_password);
        edtPassword = findViewById(R.id.password_register);

        mRegisterPresenter = new RegistrationPresenter(this,this);
        btskup = findViewById(R.id.btn_skip);
        btskup.setOnClickListener(this);
        signUpBtn = findViewById(R.id.btn_signUpWithGoogle);
        signUpBtn.setOnClickListener(this);
        mPrgressDialog = new ProgressDialog(this);
        mPrgressDialog.setMessage("Please wait, Adding profile to database.");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_forget_password:
                checkRegistrationDetails();
                break;
            case R.id.tv_login:
                moveToLoginActivity();
                break;
        case R.id.btn_skip:

            skipBtn();
            break;
            case R.id.btn_signUpWithGoogle:
                signInGoogle();
        break;
    }

    }


    private void skipBtn() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("if you skip you can't use some feature !")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(  new Intent(getApplicationContext(), HomeActivity.class));
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
         builder.create().show();
    }




    private void moveToLoginActivity() {
        YourPreference.getInstance(this).saveData("email","guest");
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
        final String PASSWORD_PATTERN = "^(?=.[0-9]).{4,}$";
       // final String PASSWORD_PATTERN = "^(?=.[0-9])(?=.[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void checkRegistrationDetails() {

        if(!TextUtils.isEmpty(edtEmail.getText().toString()) &&isValidEmail(edtEmail.getText().toString()) && isValidPassword(edtPassword.getText().toString())  && !TextUtils.isEmpty(edtPassword.getText().toString())){
            initLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
        }else{
            if(TextUtils.isEmpty(edtEmail.getText().toString()) || !isValidEmail(edtEmail.getText().toString()) ){
                edtEmail.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(edtPassword.getText().toString()) || !isValidPassword(edtPassword.getText().toString())){
                edtPassword.setError("Please enter password");
            }
        }

    }

    private void initLogin(String email, String password) {
        mPrgressDialog.show();
        mRegisterPresenter.register(email, password);
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        YourPreference yourPrefrence = YourPreference.getInstance(getApplicationContext());
        yourPrefrence.saveData("email",firebaseUser.getEmail());
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Registered" , Toast.LENGTH_SHORT).show();
        finish();
      startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onRegistrationFailure(String message) {
        mPrgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
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
                        Toast.makeText(getApplicationContext(), "Sign Up was successful", Toast.LENGTH_SHORT).show();
                        mRegisterPresenter.setDataFromFirebase();
                        startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Sign Up failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}