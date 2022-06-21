package com.example.restaurant_reservation2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class Homescreeen extends AppCompatActivity {

int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreeen);
        ImageButton Burger = findViewById(R.id.Burger_Button);
        ImageButton Pizza = findViewById(R.id.Pizza_Button);
        ImageButton Fries = findViewById(R.id.Fies_Button);
        ImageButton Salads = findViewById(R.id.Salad_Button);

        //Objects.requireNonNull(getSupportActionBar()).hide();

        Burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B = new Intent(getApplicationContext(), Burger.class);
                startActivity(B);
            }
        });

        Pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B = new Intent(getApplicationContext(), Pizza.class);
                startActivity(B);
            }
        });

        Fries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B = new Intent(getApplicationContext(), Fries.class);
                startActivity(B);
            }
        });

        Salads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B = new Intent(getApplicationContext(), Salads.class);
                startActivity(B);
            }
        });

    }
    public void Add_total(int price) {

         sum = sum + price;

        System.out.println(sum);
    }
}