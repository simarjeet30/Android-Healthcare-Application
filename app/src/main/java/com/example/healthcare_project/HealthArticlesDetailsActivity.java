package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetailsActivity extends AppCompatActivity {

    TextView HealthArticles;
    Button btnBack;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        btnBack=findViewById(R.id.btnBack);
        image=findViewById(R.id.image);
        HealthArticles=findViewById(R.id.HealthArticles);

        Intent intent = getIntent();
        HealthArticles.setText(intent.getStringExtra("text1"));

        //for dynamic image
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            int resId = bundle.getInt("text2");
            image.setImageResource(resId);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetailsActivity.this,HealthArticlesActivity.class));
            }
        });
    }
}