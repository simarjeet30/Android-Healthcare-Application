package com.example.healthcare_project;

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
Button btnGoToCart,btnBack;

private String[][] packages =
        {
                {"Package 1 : Full Body Checkup","","","","999"},
                {"Package 2 : Blood Glucose Fasting","","","","299"},
                {"Package 3 : Covid-19 Antibody - IgG","","","","899"},
                {"Package 4 : Thyroid Check","","","","499"},
                {"Package 5 : Immunity Check","","","","699"}
        };
private String[] package_details = {
        "Blood Glucose Fasting\n"+
                "Complete Hemogram\n"+
                "HbA1c\n"+
                "Iron Studies\n"+
                "Kidney Function Test\n"+
                "LDH Lactate Dehydrogenase, Serum\n"+
                "Lipid Profile\n"+
                "Liver Function Test",
        "Blood Glucose Fasting",
        "COVID-19 Antibody - IgG",
        "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
        "Complete Hemogram\n"+
                "CRP (C Reactive Protien) Quantitative, Serum\n" +
                "Iron Studies\n" +
                "Kidney Function Test\n" +
                "Vitamin D Total-25 Hydroxy\n" +
                "Liver Function Test\n" +
                "Lipid Profile"
};
HashMap<String,String> item;
ArrayList list;
SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.btnGoToCart);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LabTestActivity.this,HomeActivity.class);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LabTestActivity.this,CartLabActivity.class);
                startActivity(it);
            }
        });
        //now for list view we need to crrate an object of arraylist
        list = new ArrayList();
        //we will use hash map and split the whole string into name id salary etc
        for (int i=0;i<packages.length;i++)
        {
            item = new HashMap<String, String>();
            item.put("Line1",packages[i][0]);
            item.put("Line2",packages[i][1]);
            item.put("Line3",packages[i][2]);
            item.put("Line4",packages[i][3]);
            item.put("Line5","Cons Fees:"+packages[i][4]+"/-");
            list.add(item);

        }
        //now we will create another layout
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.btnlistLabTest);
        lst.setAdapter(sa);

        //list item ke click pr book appointment pr jana chahiye

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                //now we also need to pass that data
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });

    }
}