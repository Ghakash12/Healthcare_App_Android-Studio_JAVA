package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Loginactivity extends AppCompatActivity {
 EditText etUser,etPass;

    Button Btn;
    TextView tv,logo,Slogan;

    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_loginactivity);

        etUser=findViewById(R.id.etusername);
        etPass=findViewById(R.id.etpassword);
        Btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);
        img=findViewById(R.id.logoImage);
        logo=findViewById(R.id.logoname);
        Slogan=findViewById(R.id.sloganname);



        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Loginactivity.this, HomeActivity.class));

               String un = etUser.getText().toString();
                String pw = etPass.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (un.length() == 0 || pw.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill the Details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(un, pw) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("un", un);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Welcome "+un,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Loginactivity.this, HomeActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tv.setOnClickListener((view) -> {
         Intent it=new Intent(Loginactivity.this, RegisterActivity.class);

            Pair[] pairs=new Pair[3];

            pairs[0]=new Pair<View,String>(img,"logo_image");
            pairs[1]=new Pair<View,String>(logo,"logo_name");
            pairs[2]=new Pair<View,String>(Slogan,"logo_slogan");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Loginactivity.this, pairs);
                startActivity(it, options.toBundle());
            }
        });
    }
}