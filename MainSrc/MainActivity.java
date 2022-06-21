package com.example.restaurant_reservation2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN =156 ;
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab1;
    private static final String TAG = "GoogleActivity";
    private GoogleSignInClient mGoogleSignInClient;
    float v=0;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =mAuth.getCurrentUser();
        if(user!=null)
        {
            updateUI(user);
        }
    }

    private void reload() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        tabLayout=findViewById(R.id.Tab_layout);
        viewPager=findViewById(R.id.View_pager);
        fab1=findViewById(R.id.fab_google);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final Login_Adapter adapter= new Login_Adapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        fab1.setTranslationY(100);
        tabLayout.setTranslationY(100);
        fab1.setAlpha(v);
        tabLayout.setAlpha(v);
        fab1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        createRequest();

 fab1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         signIn();
     }
 });

    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("881934534667-pe03o28a1eu6rm4s9u871fdq29prcgpg.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(MainActivity.this,"firebaseAuthWithGoogle"+ account.getId(),Toast.LENGTH_SHORT).show();
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(@Nullable String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this,Homescreeen.class);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"Error signing in with google.",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public static boolean hasText(EditText editText, String error_message) {

        String text = editText.getText().toString().trim();
        //editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(error_message);
            return false;
        }

        return true;
    }
    private void updateUI(FirebaseUser user)
    {

    }
}