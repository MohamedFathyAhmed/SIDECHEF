package com.example.sidechef.HomeActivity.View.ui.profile;

import android.content.Context;

import com.example.sidechef.model.Repository;

public class ProfilePresenter {
    Repository repository;
    ProfileViewInterface profileViewInterface;

    ProfilePresenter(Context context, ProfileViewInterface profileViewInterface){
        repository = Repository.getInstance(context);
        this.profileViewInterface=profileViewInterface;

    }
    public void deleteTableRoom(){
        new Thread(() -> repository.deleteTableRoom()).start();
        profileViewInterface.onDelete();
    }

}
