package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={
            {"Doctor Name : Akash","Hospital Address : Cmc Hospital","Exp : 2yrs","Mobile no :9486423525","1500"},
            {"Doctor Name : Bharath","Hospital Address : Govt Hospital","Exp : 4yrs","Mobile no :9486423525","1100"},
            {"Doctor Name : Dharshan","Hospital Address : Narayani Hospital","Exp : 3yrs","Mobile no :9486423525","2000"},
            {"Doctor Name : Nivedita","Hospital Address : Grace Hospital","Exp : 5yrs","Mobile no :9486423525","1800"}
    };
    private String[][] doctor_details2={
            {"Doctor Name : Sudharsan","Hospital Address : Artemis Hospital","Exp : 6yrs","Mobile no :4235259486","2500"},
            {"Doctor Name : Vignesh","Hospital Address : Indira Super Speciality Hospital","Exp : 5yrs","Mobile no :9486525423","3200"},
            {"Doctor Name : Ramanathan","Hospital Address : Nalam Medical Centre And Hospital","Exp : 4yrs","Mobile no :3529486425","1900"},
            {"Doctor Name : Yuvaraj","Hospital Address : Speciality Centre","Exp : 12yrs","Mobile no :9435612525","6200"}
    };
    private String[][] doctor_details3={
            {"Doctor Name : Sivagami","Hospital Address : Dhanya Medical Centre","Exp : 6yrs","Mobile no :9662422525","3500"},
            {"Doctor Name : Tharun","Hospital Address : Sri Sakthi Amma Hospital","Exp : 8yrs","Mobile no :6852352525","4800"},
            {"Doctor Name : Vicky","Hospital Address : Mumme Hospital","Exp : 9yrs","Mobile no :8534223525","5500"},
            {"Doctor Name : Hari","Hospital Address : Sri Ragavendra Health Care","Exp : 10yrs","Mobile no :6524823525","6500"}
    };

    private  String[][] doctor_details4={
            {"Doctor Name : Ramya","Hospital Address : Pimpri Hospital","Exp : 5yrs","Mobile no :6524851525","7400"},
            {"Doctor Name : suriya","Hospital Address : Nigdi Hospital","Exp : 6yrs","Mobile no :9452458674","2200"},
            {"Doctor Name : shyam","Hospital Address : grace Hospital","Exp : 2yrs","Mobile no :6524865123","4500"},
            {"Doctor Name : sriram","Hospital Address : Apollo Hospital","Exp : 5yrs","Mobile no :9657843258","6500"}
    };
    private  String[][] doctor_details5={
            {"Doctor Name : praveen","Hospital Address : Pink Hospital","Exp : 8yrs","Mobile no :6874512568","7200"},
            {"Doctor Name : swathi","Hospital Address : Aishwaraya Hospital","Exp : 14yrs","Mobile no :6124856952","1800"},
            {"Doctor Name : dharshini","Hospital Address : Gandhiji Hospital","Exp : 12yrs","Mobile no :9578123548","3600"},
            {"Doctor Name : Suresh","Hospital Address : Mirat Hospital","Exp : 1yrs","Mobile no :9624578521","6500"}
    };
    private  String[][] doctor_details6={
            {"Doctor Name : dany","Hospital Address : Brindha Hospital","Exp : 11yrs","Mobile no :8517632495","1000"},
            {"Doctor Name : jai","Hospital Address : Sathish Hospital","Exp : 5yrs","Mobile no :6248957134","6800"},
            {"Doctor Name : vishal","Hospital Address : Guru Hospital","Exp : 7yrs","Mobile no :9958842235","8500"},
            {"Doctor Name : rajesh","Hospital Address : Paras Hospital","Exp : 9yrs","Mobile no :666254852","4500"}
    };
    TextView tv;
    Button btn;

    String [][] doctor_details={};
    ArrayList list;
    SimpleAdapter sa;

    HashMap<String,String>item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewTitle);
        btn=findViewById(R.id.buttonHAback);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
    if(title.compareTo("Family Physician")==0){
         doctor_details=doctor_details1;
    }
    else
    if(title.compareTo("Cardiologists")==0){
        doctor_details=doctor_details2;
    }
    else
    if(title.compareTo("Dentist")==0){
        doctor_details=doctor_details3;
    }
    else
    if(title.compareTo("Surgeon")==0){
        doctor_details=doctor_details4;
    }
    else
    if(title.compareTo("Dietician")==0){
        doctor_details=doctor_details5;
    }
    else {
        doctor_details=doctor_details6;
    }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
    list = new ArrayList();
    for(int i=0;i<doctor_details.length;i++)
    {
        item = new HashMap<String,String>();
        item.put("line1",doctor_details[i][0]);
        item.put("line2",doctor_details[i][1]);
        item.put("line3",doctor_details[i][2]);
        item.put("line4",doctor_details[i][3]);
        item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
        list.add(item);
    }
    sa=new SimpleAdapter(this,list,
            R.layout.multi_lines,
            new String[]{"line1","line2","line3","line4","line5"},
            new int[]{R.id.line_1,R.id.line_2,R.id.line_3,R.id.line_4,R.id.line_5}
    );
        ListView lst=findViewById(R.id.ListViewHA);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
            });
    }
}


