package com.example.restaurant_reservation2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class Burger extends AppCompatActivity {
    private DatabaseReference reference ;
    Homescreeen homscreen=new Homescreeen();


    int Show_Cart = 0  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        reference = FirebaseDatabase.getInstance().getReference("Restaurant reservation2");

        ImageButton addCheese = findViewById(R.id.add1) ;
        ImageButton addBloody = findViewById(R.id.add2) ;
        ImageButton addChicago = findViewById(R.id.add3) ;
        Button Cart = findViewById(R.id.Cart_Button) ;
        //Objects.requireNonNull(getSupportActionBar()).hide();



        addCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String ID_Cart = reference.push().getKey() ;
               assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","cheese Burger");
                Parameters.put("Price","47") ;
                homscreen.Add_total(47);

                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });
        addBloody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Bloody Hell");
                Parameters.put("Price","56") ;
                homscreen.Add_total(56);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });
        addChicago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Chicago Burger");
                Parameters.put("Price","56") ;
                homscreen.Add_total(56);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Show_Cart++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ShowCart.class));
            }
        });


    }


}