package com.example.sidechef.model.data.firebase;

import com.google.firebase.auth.FirebaseUser;


    public interface RegistrationListener {
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }

