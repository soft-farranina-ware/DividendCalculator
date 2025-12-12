package com.example.dividendcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Define variables
    EditText etAmount, etRate, etMonths;
    Button btnCalculate, btnAbout;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link variables to XML IDs
        etAmount = findViewById(R.id.etAmount);
        etRate = findViewById(R.id.etRate);
        etMonths = findViewById(R.id.etMonths);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnAbout = findViewById(R.id.btnAbout);
        tvResult = findViewById(R.id.tvResult);

        // Calculate Button Logic
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDividend();
            }
        });

        // About Button Logic
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateDividend() {
        // 1. Get text from inputs
        String amountStr = etAmount.getText().toString();
        String rateStr = etRate.getText().toString();
        String monthsStr = etMonths.getText().toString();


        if (amountStr.isEmpty() || rateStr.isEmpty() || monthsStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        double amount = Double.parseDouble(amountStr);
        double rate = Double.parseDouble(rateStr);
        int months = Integer.parseInt(monthsStr);


        if (months > 12) {
            Toast.makeText(this, "Months cannot exceed 12", Toast.LENGTH_SHORT).show();
            return;
        }


        double monthlyDividend = ((rate / 100) / 12) * amount;


        double totalDividend = monthlyDividend * months;


        String resultText = "Monthly Dividend: RM " + String.format("%.2f", monthlyDividend) + "\n\n" +
                "Total Dividend: RM " + String.format("%.2f", totalDividend);

        tvResult.setText(resultText);
    }
}