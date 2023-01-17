package com.example.sidechef.presenter;

import com.example.sidechef.SingInActivity.View.LoginViewinterface;
import com.example.sidechef.model.data.firebase.FireBaseAuth;
import com.example.sidechef.model.data.firebase.LoginListener;


public class LoginPresenter implements LoginPresenterInterface, LoginListener {
    private LoginViewinterface mLoginView;
    private FireBaseAuth fireBaseAuth;

    public LoginPresenter(LoginViewinterface mLoginView){
        this.mLoginView = mLoginView;
        fireBaseAuth = new FireBaseAuth();
    }
    @Override
    public void login(String email, String password) {
        fireBaseAuth.login(email, password,this);

    }

    @Override
    public void onSuccess(String message) {
        mLoginView.onLoginSuccess(message);

    }

    @Override
    public void onFailure(String message) {
        mLoginView.onLoginFailure(message);

    }
}
