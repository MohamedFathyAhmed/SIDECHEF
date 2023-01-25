package com.example.sidechef.presenter;

import android.content.Context;

import com.example.sidechef.SignUpActivity.View.RegisterViewInterface;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.data.firebase.FireBaseAuth;
import com.example.sidechef.model.data.firebase.RegistrationListener;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationPresenter implements RegisterPresenterInterface, RegistrationListener {
    private RegisterViewInterface mRegisterView;
    private FireBaseAuth fireBaseAuth;
Repository repository;
    public RegistrationPresenter(RegisterViewInterface registerView, Context context){
        this.mRegisterView = registerView;
        fireBaseAuth = new FireBaseAuth();
        repository =Repository.getInstance(context);
    }
    @Override
    public void register(String email, String password) {
        fireBaseAuth.Registration(email,password,this);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        mRegisterView.onRegistrationSuccess(firebaseUser);
        repository.getAllMealFirebase();
       repository.getAllMealPlanFirebase();
    }

    @Override
    public void onFailure(String message) {
        mRegisterView.onRegistrationFailure(message);

    }
}
