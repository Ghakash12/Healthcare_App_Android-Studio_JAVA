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

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_details = {
            {"Fitness and Exercise", "", "", "", "Click More Details"},
            {"Benefits of Water", "", "", "", "Click More Details"},
            {"COVID-19 Issues", "", "", "", "Click More Details"},
            {"Food and Nutrition", "", "", "", "Click More Details"},
            {"Walking Daily", "", "", "", "Click More Details"}
    };
    private int[] images = {
            R.drawable.hh1,
            R.drawable.hh2,
            R.drawable.hh3,
            R.drawable.hh4,
            R.drawable.hh5

    };
    HashMap<String, String> item;
    ArrayList list;
    ListView lst;

    Button btnback;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);
        lst = findViewById(R.id.ListViewHA);
        btnback = findViewById(R.id.buttonHAback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < health_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_1, R.id.line_2, R.id.line_3,R.id.line_4, R.id.line_5});

        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticlesDetailsActivity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });
    }
}

