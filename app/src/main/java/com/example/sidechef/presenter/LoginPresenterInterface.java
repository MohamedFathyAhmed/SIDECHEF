package com.example.sidechef.presenter;

import android.app.Activity;

import com.example.sidechef.model.data.firebase.LoginListener;

interface LoginPresenterInterface {
    void login(String email, String password);
}
