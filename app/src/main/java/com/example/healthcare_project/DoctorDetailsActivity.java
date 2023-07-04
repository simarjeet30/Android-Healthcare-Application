package com.example.healthcare_project;

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
    private String [][] doctor_details1 =
            {//last field is consultancy fees.
            {"Doctor Name : Ajit Saste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9876978676", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No : 8769788676", "900"},
            {"Doctor Name : Saapnil Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 9876876978", "300"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchaad", "Exp : 6yrs", "Mobile No : 9878769786", "500"},
            {"Doctor Name : Ashok Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 9876978676", "600"}
            };
    private String [][] doctor_details2 =
            {//last field is consultancy fees.
                    {"Doctor Name : Ajit Tomar", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9876978676", "600"},
                    {"Doctor Name : Rakesh Pawar", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No : 8769788676", "900"},
                    {"Doctor Name : Sapan Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 9876876978", "300"},
                    {"Doctor Name : Deepti Deshmukh", "Hospital Address : Chinchaad", "Exp : 6yrs", "Mobile No : 9878769786", "500"},
                    {"Doctor Name : Akhilesh Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 9876978676", "600"}
            };
    private String [][] doctor_details3 =
            {//last field is consultancy fees.
                    {"Doctor Name : Shanden Saste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9876978676", "600"},
                    {"Doctor Name : Mukesh Pawar", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No : 8769788676", "900"},
                    {"Doctor Name : Saapnil Balh", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 9876876978", "300"},
                    {"Doctor Name : Rhitesh Deshmukh", "Hospital Address : Chinchaad", "Exp : 6yrs", "Mobile No : 9878769786", "500"},
                    {"Doctor Name : Ashok Pandeshwar", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 9876978676", "600"}
            };
    private String [][] doctor_details4 =
            {//last field is consultancy fees.
                    {"Doctor Name : Anand Saste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9876978676", "600"},
                    {"Doctor Name : Kishor Pawar", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No : 8769788676", "900"},
                    {"Doctor Name : Sanya Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 9876876978", "300"},
                    {"Doctor Name : Deepika Deshmukh", "Hospital Address : Chinchaad", "Exp : 6yrs", "Mobile No : 9878769786", "500"},
                    {"Doctor Name : Alok Panda", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 9876978676", "600"}
            };
    private String [][] doctor_details5 =
            {//last field is consultancy fees.
                    {"Doctor Name : Ajit Namaste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9876978676", "600"},
                    {"Doctor Name : Prasad Pande", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No : 8769788676", "900"},
                    {"Doctor Name : Saapnil Kaleshwari", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No : 9876876978", "300"},
                    {"Doctor Name : Deepak Dendrite", "Hospital Address : Chinchaad", "Exp : 6yrs", "Mobile No : 9878769786", "500"},
                    {"Doctor Name : Ashok Pecular", "Hospital Address : Katrai", "Exp : 7yrs", "Mobile No : 9876978676", "600"}
            };
TextView tv;
Button btn;
ArrayList list;
SimpleAdapter sa;
HashMap<String,String> item;
//create one common string
    String [][] doctor_details = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
//har activity ke liye different accordingly titles
        tv = findViewById(R.id.HealthcareTop);
        btn = findViewById(R.id.btn);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians")==0)
            doctor_details=doctor_details1;
        else if (title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else if (title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else if (title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        //we will use hash map and split the whole string into name id salary etc
        for (int i=0;i<doctor_details.length;i++)
        {
            item = new HashMap<String, String>();
            item.put("Line1",doctor_details[i][0]);
            item.put("Line2",doctor_details[i][1]);
            item.put("Line3",doctor_details[i][2]);
            item.put("Line4",doctor_details[i][3]);
            item.put("Line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);

        }
        //now we will create another layout
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"Line1","Line2","Line3","Line4","Line5"},
        new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.btnlist);
        lst.setAdapter(sa);

        //list item ke click pr book appointment pr jana chahiye

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                //now we also need to pass that data
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