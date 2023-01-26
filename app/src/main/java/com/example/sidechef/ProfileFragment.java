package com.example.sidechef;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sidechef.SignUpActivity.View.SignUp;
import com.example.sidechef.Utils.YourPreference;

public class ProfileFragment extends Fragment implements  ProfileViewInterface{


    //String email  = YourPreference.getInstance(requireContext()).getData("email");
    LinearLayout linearLayout ;
    ProfilePresenter profilePresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout=view.findViewById(R.id.logout_id);
        profilePresenter=new ProfilePresenter(requireContext(),this);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePresenter.deleteTableRoom();

            }
        });
    }

    @Override
    public void onDelete() {
        YourPreference.getInstance(requireContext()).removeData("email");
        Intent intent =new Intent(requireContext(), SignUp.class);
        startActivity(intent);
    }
}