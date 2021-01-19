package com.alkhairunasriyuska.wisataandalas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeSatuActivity extends AppCompatActivity {

    LinearLayout btn_ticket_pisa, btn_ticket_torri,
            btn_ticket_pagoda, btn_ticket_candi,
            btn_ticket_sphinx, btn_ticket_monas;
    CircleView btn_to_profile;
    TextView userbalance, namalengkap, biob;

    DatabaseReference reference;

    ImageView fotodashboard;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_satu);

        getUsernameLocal();

        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_to_profile = findViewById(R.id.btn_to_profile);

        btn_ticket_torri = findViewById(R.id.btn_ticket_torri);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_candi = findViewById(R.id.btn_ticket_candi);
        btn_ticket_sphinx = findViewById(R.id.btn_ticket_sphinx);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);


        fotodashboard = findViewById(R.id.fotodashboard);
        userbalance = findViewById(R.id.userbalance);
        namalengkap = findViewById(R.id.namalengkap);
        biob = findViewById(R.id.biob);

        // Ambil data menggunakan firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namalengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                biob.setText(dataSnapshot.child("bio").getValue().toString());
                userbalance.setText("US$ " + dataSnapshot.child("user_balance").getValue().toString());

                //============Replace Foto Gambar==================
                Picasso.with(HomeSatuActivity.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit()
                        .into(fotodashboard);
                //===================================
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //*******************************************************************************
        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeSatuActivity.this, TicketDetailActivity.class);
                // meletakan data pada intent
                gotopisaticket.putExtra("jenis tiket", "Pisa"); //baru **********************
                startActivity(gotopisaticket );
            }
        });

        btn_ticket_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeSatuActivity.this, TicketDetailActivity.class);
                gotopisaticket.putExtra("jenis tiket", "Torri"); //baru **********************
                startActivity(gotopisaticket );
            }
        });

        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeSatuActivity.this, TicketDetailActivity.class);
                gotopisaticket.putExtra("jenis tiket", "Pagoda"); //baru **********************
                startActivity(gotopisaticket );
            }
        });

        btn_ticket_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeSatuActivity.this, TicketDetailActivity.class);
                gotopisaticket.putExtra("jenis tiket", "Candi"); //baru **********************
                startActivity(gotopisaticket );
            }
        });

        btn_ticket_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeSatuActivity.this, TicketDetailActivity.class);
                gotopisaticket.putExtra("jenis tiket", "Sphinx"); //baru **********************
                startActivity(gotopisaticket );
            }
        });

        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeSatuActivity.this, TicketDetailActivity.class);
                gotopisaticket.putExtra("jenis tiket", "Monas"); //baru **********************
                startActivity(gotopisaticket );
            }
        });
        // *******************************************************

        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(HomeSatuActivity.this, MyProfileActivity.class);
                startActivity(gotoprofile);
            }
        });

    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}