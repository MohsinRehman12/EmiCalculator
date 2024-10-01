package com.example.emicalculator;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculation_page);

        Button homeBtn = (Button) findViewById(R.id.buttonHome);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //get the intent and display the value of the calculate EMI from the main activity
        Intent intent = getIntent();
        String recievedEmi = intent.getStringExtra("EmiValue");
        TextView emiDisplay = findViewById(R.id.textViewEmi);
        emiDisplay.setText(recievedEmi);

        //button returns user to the home page
        homeBtn.setOnClickListener(v-> {

            Intent intentHome = new Intent(CalculationPage.this, MainActivity.class);
            startActivity(intentHome);

        });

        }
}