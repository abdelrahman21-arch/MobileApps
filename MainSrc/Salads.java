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

public class Salads extends AppCompatActivity {

    private DatabaseReference reference ;
    int Show_Cart = 0  ;
    Homescreeen homscreen=new Homescreeen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salads);
        reference = FirebaseDatabase.getInstance().getReference("Restaurant reservation2");

        ImageButton GSalad = findViewById(R.id.add1) ;
        ImageButton cSalad = findViewById(R.id.add2) ;
        ImageButton sSalad = findViewById(R.id.add3) ;
        Button Cart = findViewById(R.id.Cart_Button) ;

    //    Objects.requireNonNull(getSupportActionBar()).hide();

        GSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Green Salad");
                Parameters.put("Price","15") ;
                homscreen.Add_total(15);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        cSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Caesar Salad");
                Parameters.put("Price","25") ;
                homscreen.Add_total(25);
                reference.child(ID_Cart).setValue(Parameters) ;
                Toast.makeText(getBaseContext(), "Add " + Show_Cart + " item/s to Cart ", Toast.LENGTH_SHORT).show();
            }
        });

        sSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID_Cart = reference.push().getKey() ;
                assert ID_Cart != null;
                HashMap<String,String> Parameters = new HashMap<>() ;
                Parameters.put("Product_Name","Grilled Shrimp Salad ");
                Parameters.put("Price","35") ;
                homscreen.Add_total(35);
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

        }
}