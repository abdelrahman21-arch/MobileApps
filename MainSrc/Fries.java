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

public class Fries extends AppCompatActivity {

    private DatabaseReference reference ;
    int Show_Cart = 0  ;
    Homescreeen homscreen=new Homescreeen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fries);
        reference = FirebaseDatabase.getInstance().getReference("Restaurant reservation2");

        ImageButton fries = findViewById(R.id.add1) ;
        ImageButton CheFries = findViewById(R.id.add2) ;
        ImageButton MeatFries = findViewById(R.id.add3) ;
        Button Cart = findViewById(R.id.Cart_Button) ;

      //  Objects.requireNonNull(getSupportActionBar()).hide();


        fries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Standard Fries");
                Parameters.put("Price","10") ;

                homscreen.Add_total(10);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        CheFries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Cheese Fries");
                Parameters.put("Price","15") ;
                homscreen.Add_total(15);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        MeatFries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Cheese Meat Fries");
                Parameters.put("Price","25") ;
                homscreen.Add_total(25);
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