package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText editTextFullName,editTextAddress,editTextPinCode,editTextContactNo;
    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        editTextAddress=findViewById(R.id.editTextAddress);
        editTextFullName= findViewById(R.id.editTextFullName);
        editTextPinCode= findViewById(R.id.editTextPinCode);
        editTextContactNo= findViewById(R.id.editTextContactNo);
        book = findViewById(R.id.book);
        //we passed data from cart lab activity
        Intent it = getIntent();
        String[] price = it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = it.getStringExtra("date");
        String time = it.getStringExtra("time");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                //now we need to create another table also add order
                db.addOrder(username,editTextFullName.getText().toString(),editTextAddress.getText().toString(),editTextContactNo.getText().toString(),Integer.parseInt(editTextPinCode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"Lab");
                db.removeCart(username,"Lab");
                Toast.makeText(LabTestBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });
    }
}