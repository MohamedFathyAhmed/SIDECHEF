package com.example.sidechef.HomeActivity.View.ui.user;

import android.content.Context;

import com.example.sidechef.model.Repository;

public class UserPresenter {
    Repository repository;
    UserViewInterface profileViewInterface;

    UserPresenter(Context context, UserViewInterface userViewInterface){
        repository = Repository.getInstance(context);
        this.profileViewInterface=userViewInterface;

    }
    public void deleteTableRoom(){
        new Thread(() -> repository.deleteTableRoom()).start();
    }
    public void deleteWeekRoom(){
        new Thread(() -> repository.deleteweekRoom()).start();
    }
    public void  onDelete(){
        profileViewInterface.onDelete();
    }


}
