package com.example.sidechef.SignUpActivity.View;

import com.google.firebase.auth.FirebaseUser;

public interface RegisterViewInterface {
    void onRegistrationSuccess(FirebaseUser firebaseUser);
    void onRegistrationFailure(String message);
}
