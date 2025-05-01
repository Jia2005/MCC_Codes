package com.example.emi; 
 
import android.os.Bundle; 
import android.app.AlertDialog; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import androidx.appcompat.app.AppCompatActivity; 
 
public class MainActivity extends AppCompatActivity { 
    EditText loanAmount, interestRate, loanTenure; 
    Button calculateBtn; 
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
 
        loanAmount = findViewById(R.id.loanAmount); 
        interestRate = findViewById(R.id.interestRate); 
        loanTenure = findViewById(R.id.loanTenure); 
        calculateBtn = findViewById(R.id.calculateBtn); 
 
        calculateBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                calculateEMI(); 
            } 
        }); 
    } 
 
    private void calculateEMI() { 
        double principal = Double.parseDouble(loanAmount.getText().toString()); 
        double rate = Double.parseDouble(interestRate.getText().toString()) / 12 / 100; 
        int years = Integer.parseInt(loanTenure.getText().toString()); 
        int months = years * 12; 
 
        double emi = (principal * rate * Math.pow(1 + rate, months)) / (Math.pow(1 + rate, 
months) - 1); 
 
        // Show Alert Dialog with EMI 
        new AlertDialog.Builder(this) 
                .setTitle("EMI Calculation") 
                .setMessage("Your Monthly EMI is: ₹" + String.format("%.2f", emi)) 
                .setPositiveButton("OK", null) 
                .show(); 
    } 
} 
