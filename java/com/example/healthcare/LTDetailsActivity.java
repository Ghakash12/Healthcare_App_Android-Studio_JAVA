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

public class LTDetailsActivity extends AppCompatActivity {
    TextView tvpackagename,tvtotalcost;
    EditText etdetails;

    Button btnaddtocart,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltdetails);

        tvpackagename=findViewById(R.id.textViewHA);
        tvtotalcost=findViewById(R.id.textViewBMDtotalcost);
        etdetails=findViewById(R.id.editTextltmultilineBMD);
        btnaddtocart=findViewById(R.id.buttonBMDaddtocart);
        btnback=findViewById(R.id.buttonHAback);

        etdetails.setKeyListener(null);

        Intent intent=getIntent();
       tvpackagename.setText(intent.getStringExtra("text1"));
       etdetails.setText(intent.getStringExtra("text2"));
       tvtotalcost.setText("total Cost:"+intent.getStringExtra("text3")+"/-");

       btnback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(LTDetailsActivity.this,LabTestActivity.class));
           }
       });

       btnaddtocart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String un=sharedpreferences.getString("un","").toString();
                String pd=tvpackagename.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkcart(un,pd)==1)
                {
                    Toast.makeText(getApplicationContext(),"PRODUCT ALREADY EXIST",Toast.LENGTH_SHORT).show();
                }else{
                    db.addcart(un,pd,price,"lab");
                    Toast.makeText(getApplicationContext(),"RECORD INSERTED TO CART",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LTDetailsActivity.this,LabTestActivity.class));
                }
           }
       });


    }

}