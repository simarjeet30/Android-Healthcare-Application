package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
TextView LabTest,TextView;
EditText editTextBody;
Button btnBack,btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        LabTest=findViewById(R.id.LabTest);
        TextView=findViewById(R.id.TextView);
        editTextBody=findViewById(R.id.editTextBody);
        btnBack=findViewById(R.id.btnBack);
        btnAddToCart=findViewById(R.id.btnAddToCart);

        //we dont want to modify edit text
        editTextBody.setKeyListener(null);
        //now we need to get the data we passed in LabTestActivity.java
        Intent it =getIntent();
        LabTest.setText(it.getStringExtra("text1"));
        TextView.setText("Total Cost : "+it.getStringExtra("text3")+"/-");
        editTextBody.setText(it.getStringExtra("text2"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //now we need to create another table in the database for booking
                //but since book appointment also have book order so we will create both together
                //now here as the query in database.java suggests ,we need some username for creating an entry ,which is already taken at the time of login in the shared preference so we will take it from there
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = LabTest.getText().toString();
                float price = Float.parseFloat(getIntent().getStringExtra("text3").toString());//casting the string to float

                //now create an object of database
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                //first check whether its present in cart or not
                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else{
                    db.addToCart(username,product,price,"Lab");
                    Toast.makeText(LabTestDetailsActivity.this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });


    }


}