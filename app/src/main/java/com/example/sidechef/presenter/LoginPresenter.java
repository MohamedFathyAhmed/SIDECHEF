package com.example.sidechef.presenter;

import android.content.Context;

import com.example.sidechef.SingInActivity.View.LoginViewinterface;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.data.firebase.FireBaseAuth;
import com.example.sidechef.model.data.firebase.LoginListener;


public class LoginPresenter implements LoginPresenterInterface, LoginListener {
    private LoginViewinterface mLoginView;
    private FireBaseAuth fireBaseAuth;
    Repository repository;

    public LoginPresenter(LoginViewinterface mLoginView, Context context){
        this.mLoginView = mLoginView;
        fireBaseAuth = new FireBaseAuth();
        repository = Repository.getInstance(context);
    }
    @Override
    public void login(String email, String password) {
        fireBaseAuth.login(email, password,this);

    }

    @Override
    public void setDataFromFirebase() {
        repository.getAllMealFirebase();
        repository.getAllMealPlanFirebase();
    }

    @Override
    public void onSuccess(String message,String email) {
        mLoginView.onLoginSuccess(message,email);
        repository.getAllMealFirebase();
        repository.getAllMealPlanFirebase();
    }

    @Override
    public void onFailure(String message) {
        mLoginView.onLoginFailure(message);

    }
}
