package com.example.sidechef.model.data.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuth {

    public FireBaseAuth() {}

    public void  login(String email,String password,LoginListener mOnLoginListener){
    FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mOnLoginListener.onSuccess(task.getResult().toString(),task.getResult().getUser().getEmail());
                    }
                    else {
                        mOnLoginListener.onFailure(
                                task.getException().getMessage().toString());
                    }
                }
            });

}
    public void Registration(String email, String password, RegistrationListener mOnRegistrationListener) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            mOnRegistrationListener.onFailure(
                                    task.getException().getMessage());
                        }else{
                            mOnRegistrationListener.onSuccess(
                                    task.getResult().getUser());
                        }
                    }
                });
    }

}
