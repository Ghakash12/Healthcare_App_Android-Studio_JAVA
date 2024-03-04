package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {


    private String[][] packages={
            {"Package 1: MASTER CHECKUP","","","","5500"},
            {"Package 2: BLOOD GLUCOSE TESTING","","","","1500"},
            {"Package 3: COIVD TEST","","","","2500"},
            {"Package 4: TYP TESTING","","","","1200"},
            {"Package 5: HMG TEST","","","","5600"},

    };
    private String[] package_details={
            "Blood Glucose Fasting\n"+
                    "Complete Hemogram\n"+
                    "HbA1c\n"+
                    "Iron Studies\n"+
                    "Lipid Profile\n"+
                    "Liver Function",
            "blood Glucose",
            "covid-19",
            "thyroid",
            "complete Hemogram\n"+
                    "Iron studies\n"+
                    "lipid Profile"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btngtcart,btnback;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btngtcart=findViewById(R.id.buttonBMDaddtocart);
        btnback=findViewById(R.id.buttonHAback);
        lv=findViewById(R.id.ListViewHA);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        btngtcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartlabActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_1,R.id.line_2,R.id.line_3,R.id.line_4,R.id.line_5});
            lv.setAdapter(sa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it=new Intent(LabTestActivity.this,LTDetailsActivity.class);

                    it.putExtra("text1",packages[i][0]);
                    it.putExtra("text2",package_details[i]);
                    it.putExtra("text3",packages[i][4]);
                    startActivity(it);
                }

            });

            btngtcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LabTestActivity.this,CartlabActivity.class));
                }
            });
    }
}