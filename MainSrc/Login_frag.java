package com.example.restaurant_reservation2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_frag extends Fragment {
    EditText email;
    EditText password;
    Button btn;
    TextView forgetpass;
    float v=0;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
    {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);
        email=root.findViewById(R.id.Phone_num);
        password=root.findViewById(R.id.pass);
        forgetpass=root.findViewById(R.id.txtView);
        btn=root.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent i = new Intent(getActivity(),Homescreeen.class);

                                    Toast.makeText(getActivity(), "Authentication Correct.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(i);
                                    updateUI(user);

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });
       /* forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/

        email.setTranslationY(200);
        password.setTranslationY(200);
        forgetpass.setTranslationY(200);
        btn.setTranslationY(200);
        email.setAlpha(v);
        password.setAlpha(v);
        forgetpass.setAlpha(v);
        btn.setAlpha(v);
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btn.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();



        return root;
    }
    private void reload() { }
    private void updateUI(FirebaseUser user) {


    }
}