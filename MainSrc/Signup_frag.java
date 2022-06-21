package com.example.restaurant_reservation2;


import static com.example.restaurant_reservation2.MainActivity.hasText;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Policy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


public class Signup_frag extends Fragment {
    EditText mobile_no;
    EditText password;
    EditText email;
    EditText nickname;
    Button singup;
    private DatabaseReference mDatabase;

     ProgressBar progress_bar;










    private FirebaseAuth auth;
    float v=0;
    public boolean Validate() {
        boolean check = true;
        if (!hasText(mobile_no,"please enter mobile number")) check = false;
        if (!hasText(password,"please enter a password")) check = false;
        if (!hasText(email,"Please enter Email")) check = false;
        if(!hasText(nickname,"PLease enter nickname")) check = false;

        return check;
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
    {



        auth=FirebaseAuth.getInstance();
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.signup_fragment,container,false);
        mobile_no=root.findViewById(R.id.Phone_num);
        password=root.findViewById(R.id.pass);
        email=root.findViewById(R.id.email);
        nickname=root.findViewById(R.id.nick_name);
        singup=root.findViewById(R.id.button);
           progress_bar=root.findViewById(R.id.progressBar);



        singup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(Validate())
                {
                    if(password.getText().toString().length()<6)
                    {
                        Toast.makeText(  getActivity()  ,"password too short!",Toast.LENGTH_SHORT).show();
                    }
                    if(mobile_no.getText().toString().length() <11 || !mobile_no.getText().toString().matches("^[0-9]*$") || mobile_no.getText().toString().length() >11 )
                    {
                        Toast.makeText(  getActivity()  ,"enter correct 12 digit mobile number",Toast.LENGTH_SHORT).show();
                    }
                    if(!email.getText().toString().contains("@"))
                    {
                        Toast.makeText(  getActivity()  ,"enter correct mail",Toast.LENGTH_SHORT).show();
                    }
                    //progress_bar.setVisibility(View.VISIBLE);
                    RegisterUser(email.getText().toString(),password.getText().toString());


                }




            }
        });
        mobile_no.setTranslationY(200);
        password.setTranslationY(200);
        email.setTranslationY(200);
        nickname.setTranslationY(200);
        singup.setTranslationY(200);
        mobile_no.setAlpha(v);
        password.setAlpha(v);
        email.setAlpha(v);
        nickname.setAlpha(v);
        singup.setAlpha(v);
        mobile_no.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        nickname.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        singup.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void RegisterUser(String email,String password) {

        progress_bar.setVisibility((View.VISIBLE));
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               progress_bar.setVisibility((View.GONE));
                if (task.isSuccessful()) {
                    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    String timestamp = s.format(new Date());
                    Toast.makeText(getActivity(), "successful", Toast.LENGTH_SHORT).show();
                    Users user = new Users(nickname.getText().toString(), email, mobile_no.getText().toString(),timestamp);


                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").child(nickname.getText().toString()).setValue(user);





                    // store extra fields in firebase database
                } else {
                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }

}




