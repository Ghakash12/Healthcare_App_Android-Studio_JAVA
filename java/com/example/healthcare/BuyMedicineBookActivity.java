package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.etfn);
        edaddress  = findViewById(R.id.etad);
        edcontact = findViewById(R.id.etcn);
        edpincode =findViewById(R.id.etpc);
        btnBooking = findViewById(R.id.buttonBMBbook);

        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent. getStringExtra("date");
       // String time = intent. getStringExtra("time");
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences(  "shared_prefs", Context.MODE_PRIVATE);
                String un = sharedpreferences.getString(  "un", "").toString();
                Database db = new Database(getApplicationContext(),  "healthcare", null, 1);
                db.addOrder(un, edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removecart(un,"medicine");
                Toast.makeText(getApplicationContext(),"Your booking is done successfully", Toast. LENGTH_LONG).show();
                startActivity(new Intent( BuyMedicineBookActivity. this, HomeActivity.class));
            }
        });
    }
}