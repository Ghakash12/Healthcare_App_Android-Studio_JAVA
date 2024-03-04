package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class HomeActivity extends AppCompatActivity {

    LottieAnimationView lot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lot=findViewById(R.id.lot);

        /*SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String un=sharedPreferences.getString("un","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+un,Toast.LENGTH_SHORT).show();*/
        CardView exit=findViewById(R.id.cardexist);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();*/
                startActivity(new Intent(HomeActivity.this,Loginactivity.class));
            }
        });
        CardView findDoctor =findViewById(R.id.cardfinddoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });
      /* LinearLayout Hom2=findViewById(R.id.home2);
                Hom2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
                    }
                });*/
        CardView lt =findViewById(R.id.cardlabtest);
        lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });

        CardView orderDetails=findViewById(R.id.cardorderdet);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
            }
        });


        CardView buyMedicine=findViewById(R.id.cardbuymedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
            }
        });

        CardView health=findViewById(R.id.cardHealthdoctor);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HealthArticlesActivity.class));
            }
        });



    }
}