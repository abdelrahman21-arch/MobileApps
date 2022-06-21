package com.example.restaurant_reservation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class Pizza extends AppCompatActivity {

    private DatabaseReference reference ;
    int Show_Cart = 0  ;
    Homescreeen homscreen=new Homescreeen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        reference = FirebaseDatabase.getInstance().getReference("Rest app");

        ImageButton addCheese = findViewById(R.id.add1) ;
        ImageButton addPep = findViewById(R.id.add2) ;
        ImageButton addChicken = findViewById(R.id.add3) ;
        ImageButton addSeaFood =findViewById(R.id.add4);
        Button Cart = findViewById(R.id.Cart_Button) ;

     //   Objects.requireNonNull(getSupportActionBar()).hide();

        addCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Margherita Pizza");
                Parameters.put("Price","62") ;
                homscreen.Add_total(62);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        addPep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","pepperoni Pizza");
                Parameters.put("Price","75") ;
                homscreen.Add_total(75);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        addChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Chicken Ranch Pizza ");
                Parameters.put("Price","75") ;
                homscreen.Add_total(75);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        addSeaFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Seafood Pizza ");
                Parameters.put("Price","90") ;
                homscreen.Add_total(90);
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