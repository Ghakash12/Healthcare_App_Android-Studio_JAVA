package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton,btnbook,btnback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv=findViewById(R.id.textViewapptitle3);
        ed1=findViewById(R.id.etfn);
        ed2=findViewById(R.id.etad);
        ed3=findViewById(R.id.etcn);
        ed4=findViewById(R.id.etfe);
        dateButton=findViewById(R.id.buttonBMCcartappdate);
        timeButton=findViewById(R.id.buttonBMCcartapptime);
        btnbook=findViewById(R.id.buttonbookappoint);
        btnback=findViewById(R.id.buttonBMCcartappback);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fess=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fess"+fess+"/-");

        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String un=sharedpreferences.getString("un","").toString();
                if(db.checkAppointmentExists(un,title+"=>"+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1)
                {
                    Toast.makeText(getApplicationContext(),"Appoinment Already Booked",Toast.LENGTH_SHORT).show();
                }else{
                    db.addOrder(un,title+"=>"+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fess),"appointment");
                    Toast.makeText(getApplicationContext(),"Your Appointment booked Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,DoctorDetailsActivity.class));
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}