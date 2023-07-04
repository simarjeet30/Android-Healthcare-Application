package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    android.widget.TextView TextView;
    ListView btnList;
    private String[][] packages ={};
    private DatePickerDialog datePickerDialog;
    private static ArrayList<String> dbData = new ArrayList<>();

    private TimePickerDialog timePickerDialog;
    private Button setDate,btnBack,btnCheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        btnBack = findViewById(R.id.btnBack);
        btnCheckout = findViewById(R.id.btnCheckout);
        setDate = findViewById(R.id.setDate);
        TextView = findViewById(R.id.TextView);
        btnList = findViewById(R.id.btnlist);

        //get username
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        //now object of database
        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        float totalAmount=0;

        dbData = db.getCartData(username,"Medicine");
        //now we need to work on checkout:date time and total price would be displayed


        /*if (dbData.isEmpty())
            Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), ""+dbData, Toast.LENGTH_LONG).show();*/
//now we need to split the data into parts
//define the array
        packages = new String[dbData.size()][];
        for (int i=0;i<packages.length;i++)
        {
            packages[i]= new String[5];
        }
        //split
        for (int i=0;i<dbData.size();i++)
        {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        //after breaking we put the total amount in a text view
        TextView.setText("Total Cost : "+totalAmount);

        //we had all the data in array list then we transferred it into packages of array lists ,so now creating an object of arraylist
        list = new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("Line1",packages[i][0]);
            item.put("Line2",packages[i][1]);
            item.put("Line3",packages[i][2]);
            item.put("Line4",packages[i][3]);
            item.put("Line5",packages[i][4]);
            list.add(item);
        }
        //if black screen aara ho but data not displaying yo sa = new wala purani kisi activity se copy krro chal jayega
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        btnList.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                it.putExtra("price",TextView.getText());
                it.putExtra("date",setDate.getText());
                startActivity(it);
            }
        });

        //date picker
        //call the fn
        initDatePicker();
//first the fn will be called then the object with current date time year will be formed and then that dialogue is created and then we do .show
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

    }
    //date picker
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                //todays booking cant be made ,booking will start from tomorrow
                setDate.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal =Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,date);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }


}