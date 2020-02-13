package com.example.soccer_alliance_project_test;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


public class SignUp4_Fragment extends Fragment implements View.OnClickListener{

    TextInputEditText signup4_OTP_edit_txt;

    FirebaseAuth fAuth;
    public NavController navController;
    MaterialButton signup4_validate_otp_btn;
    String email,phone,name,gender,country,age;


    public SignUp4_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        signup4_OTP_edit_txt = view.findViewById(R.id.signup4_OTP_edit_txt);
        signup4_validate_otp_btn = view.findViewById(R.id.signup4_validate_otp_btn);
        signup4_OTP_edit_txt.setOnClickListener(this);
        email = getArguments().getString("email");
        phone = getArguments().getString("phone");
        name = getArguments().getString("name");
        gender = getArguments().getString("gender");
        country = getArguments().getString("country");
        age = getArguments().getString("age");

        System.out.println(email);
        fAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {


        if(view == signup4_validate_otp_btn){



            navController.navigate(R.id.loginFragment);

        }
    }


}
