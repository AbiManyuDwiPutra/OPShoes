package com.abim.opshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reff;
    private RecyclerView rvSepatu;
    private Adapter adapter;
    private ArrayList<Sepatu> listSepatu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database = FirebaseDatabase.getInstance("https://opshoes-793ef-default-rtdb.asia-southeast1.firebasedatabase.app/");

        rvSepatu = findViewById(R.id.rv_sepatu);
        rvSepatu.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        rvSepatu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, listSepatu);
        rvSepatu.setAdapter(adapter);
        adapter.setOnItemClickCallback(new Adapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Sepatu shoes) {
                Intent i = new Intent(Home.this, Detail.class);
                i.putExtra("NAMA", shoes.getNama());
                i.putExtra("GAMBAR", shoes.getGambar());
                i.putExtra("DESKRIPSI", shoes.getDeskripsi());
                i.putExtra("HARGA", String.valueOf(shoes.getHarga()));
                startActivity(i);
            }
        });

        reff = database.getReference("produk");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSepatu.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Sepatu shoes = ds.getValue(Sepatu.class);
                    shoes.setId(Integer.parseInt(ds.getKey()));
                    listSepatu.add(shoes);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void clickLogout(View view) {
        Intent i = new Intent(Home.this, Login.class);
        startActivity(i);
    }
}