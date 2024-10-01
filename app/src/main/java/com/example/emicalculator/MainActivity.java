package com.example.emicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //ensures the display is to 2 decimals indicating dollar format
    private static final DecimalFormat dollarFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //getters for the views of components on home screen
        Button btn = (Button) findViewById(R.id.button);
        EditText getMortgage = (EditText) findViewById(R.id.MortgageTextNumber);
        EditText getRate = (EditText) findViewById(R.id.RateTextNumber);
        EditText getPeriod = (EditText) findViewById(R.id.PeriodTextNumber);


        //onclick handler that performs calculation and redirects to next page
        btn.setOnClickListener(v-> {

            //store the text values from the users inputs into a string
            String mortgageString = getMortgage.getText().toString();
            String rateString = getRate.getText().toString();
            String periodString = getPeriod.getText().toString();

            //ensures values are inputted, then we do the calculation and go to the next page
            if(!mortgageString.isEmpty() && !rateString.isEmpty() && !periodString.isEmpty()){


                //convert the values from user input into numerical ones and perform the calculation
                double m = Double.parseDouble(mortgageString);
                double r = Double.parseDouble(rateString);
                int y = Integer.parseInt(periodString);

                double emi = emiCalculator(m,r,y);


                //convert the number back into the string that will be sent over
                String emiSting = dollarFormat.format(emi);


                //create an intent to go to the next page but also add the EmiValue to be sent over aswell
                Intent intent = new Intent(MainActivity.this, CalculationPage.class);
                intent.putExtra("EmiValue", emiSting);
                startActivity(intent);





            }



        });
    }

    public double emiCalculator(double m, double r, int y){
        r = r/(100*12);
        y = y*12;

        double emi = (
                ((m)*(r)*(Math.pow((1+r),y)))
                /((Math.pow((1+r),y))-1)

                );

        return emi;

    }




}