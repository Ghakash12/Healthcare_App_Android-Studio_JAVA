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

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages=
            {
                    {"Benzydamine ","","","","15"},
                    {"Bisoprolol ","","","","758"},
                    {"Candesartan ","","","","568"},
                    {"Chlorhexidine ","","","","164"},
                    {"Clopidogrel ","","","","87"},
                    {"Dexamethasone eye drops ","","","","125"},
                    {"Fluticasone nasal spray and drops ","","","","652"},
                    {"Glyceryl trinitrate (GTN) ","","","","315"},
                    {"Hormone replacement therapy (HRT) ","","","","550"},
            };
    private String[] package_details={
      "It comes as mouthwash, mouth spray and lozenges.\n",
              "bisoprolol treats high blood pressure (hypertension) and heart failure\n",
              "It helps to prevent future strokes, heart attacks and kidney problems It also improves your survival if you're taking it for heart failure.\n",
              "Chlorhexidine is an antiseptic and disinfectant. It helps reduce the number of germs (bacteria) in your mouth or on your skin.\n",
                "Clopidogrel is an antiplatelet medicine. It prevents platelets (a type of blood cell) from sticking together and forming a dangerous blood clot.\n",
              "to treat eye inflammation by reducing swelling, redness and irritation. These can be symptoms of eye injuries, surgery, or damage caused by severe allergies or chemicals.\n",
              "It works by reducing the swelling and irritation inside the nose.\n",
              "It can help stop chest pain if an angina attack has already started. It can also help to prevent the chest pain starting.\n",
              "Hormone replacement therapy (HRT) is a treatment used to help menopause symptoms"
    };

    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btngotoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);


        lst=findViewById(R.id.ListViewHA);
        btnBack=findViewById(R.id.buttonHAback);
        btngotoCart=findViewById(R.id.buttonBMDaddtocart);

        btngotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list=new ArrayList();
        for (int i=0;i< packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_1,R.id.line_2,R.id.line_3,R.id.line_4,R.id.line_5});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}