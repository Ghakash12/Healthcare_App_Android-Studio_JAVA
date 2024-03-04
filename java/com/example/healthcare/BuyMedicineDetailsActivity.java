package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

        TextView tvPackagename,tvTotalcost;
        EditText edDetails;
        Button btnBack,btnAddtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackagename=findViewById(R.id.textViewHA);
        edDetails=findViewById(R.id.editTextltmultilineBMD);
        edDetails.setKeyListener(null);
        tvTotalcost=findViewById(R.id.textViewBMDtotalcost);
        btnBack=findViewById(R.id.buttonHAback);
        btnAddtocart=findViewById(R.id.buttonBMDaddtocart);

        Intent intent=getIntent();
        tvPackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalcost.setText("Total Cost:"+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String un=sharedpreferences.getString("un","").toString();
                String pd=tvPackagename.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkcart(un,pd)==1)
                {
                    Toast.makeText(getApplicationContext(),"PRODUCT ALREADY EXIST",Toast.LENGTH_SHORT).show();
                }else{
                    db=new Database(getApplicationContext(),"healthcare",null,1);
                    db.addcart(un,pd,price,"medicine");
                    Toast.makeText(getApplicationContext(),"RECORD INSERTED TO CART",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });
    }
}