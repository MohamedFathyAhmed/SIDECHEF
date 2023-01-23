package com.example.sidechef.model.data.firebase;


    public interface LoginListener{
        void onSuccess(String message,String email);
        void onFailure(String message);
    }

