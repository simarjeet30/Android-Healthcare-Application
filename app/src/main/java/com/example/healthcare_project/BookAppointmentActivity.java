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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    Button setDate,setTime,back,buttonBookAppointment;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv=(TextView) findViewById(R.id.textViewTitleName);
        ed1=(EditText) findViewById(R.id.editTextFullName);
        ed2=(EditText)findViewById(R.id.editTextAddress);
        ed3=(EditText)findViewById(R.id.editTextContactNo);
        ed4=(EditText)findViewById(R.id.editTextFees);
        setDate=(Button)findViewById(R.id.setDate);
        setTime=(Button)findViewById(R.id.setTime);
        back=(Button)findViewById(R.id.back);
        buttonBookAppointment=(Button)findViewById(R.id.buttonBookAppointment);
        //remove the click ,edit text are not edittable now
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

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
        //time picker
        initTimePicker();

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });


        //retrieve data from intent
        Intent it=getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");
        //now display all these intent data to edit text
        tv.setText(title);
        ed1.setText(fullName);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees : "+fees+"/-");

        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //now we need to create another table in the database for booking
                //but since lab test also have book order so we will create both together
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                //now we need to check on the same day and time ,there must be no other appointment
                if (db.checkAppointmentExists(username,title+" => "+fullName,address,contact,setDate.getText().toString(),setTime.getText().toString())==1){
                    Toast.makeText(BookAppointmentActivity.this, "Appointment already booked", Toast.LENGTH_LONG).show();
                }else{
                    db.addOrder(username,title+" => "+fullName,address,contact,0,setDate.getText().toString(),setTime.getText().toString(),Float.parseFloat(fees),"Doctor");
                    Toast.makeText(BookAppointmentActivity.this, "Your appointment is done successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));

                }
            }
        });

    }


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

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                setTime.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }

}