package com.example.restaurant_reservation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowCart extends AppCompatActivity {
    private RecyclerView rec ;
    private List<ShowCart_Model> showCart_modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        rec = findViewById(R.id.rec) ;
        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DatabaseReference Show_info = FirebaseDatabase.getInstance().getReference("Restaurant reservation2");

    //    Objects.requireNonNull(getSupportActionBar()).hide();

        Show_info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    showCart_modelList.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        ShowCart_Model showCart_model = snapshot1.getValue(ShowCart_Model.class) ;
                        showCart_modelList.add(showCart_model) ;
                    }
                    rec.setAdapter(new ShowCart_Adapter(getApplicationContext(),showCart_modelList));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}