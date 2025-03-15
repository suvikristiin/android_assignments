package com.example.week8;

import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
>>>>>>> 24f20e4 (add answers to week 8 assignments)

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

=======
public class MainActivity extends AppCompatActivity {
>>>>>>> 24f20e4 (add answers to week 8 assignments)
    private EditText euroInput;
    private TextView resultText;
    private Button USDButton;
    private Button GDPButton;
<<<<<<< HEAD
    private double euroInteger;
=======
    private double euroDouble;
>>>>>>> 24f20e4 (add answers to week 8 assignments)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        euroInput = findViewById(R.id.EuroInput);
        resultText = findViewById(R.id.ResultText);
        USDButton = findViewById(R.id.USDButton);
        GDPButton = findViewById(R.id.GDPButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
<<<<<<< HEAD
=======


>>>>>>> 24f20e4 (add answers to week 8 assignments)
    }

    public void getEuroAmount() {
        String euroString = euroInput.getText().toString();
<<<<<<< HEAD
        euroInteger = Double.parseDouble(euroString);
=======
        euroDouble = Double.parseDouble(euroString);
>>>>>>> 24f20e4 (add answers to week 8 assignments)

    }

    public void euroToDollar(View view) {
        getEuroAmount();
<<<<<<< HEAD
        String dollar = String.format("%.2f", euroInteger * 1.05);
=======
        String dollar = String.format("%.2f", euroDouble * 1.05);
>>>>>>> 24f20e4 (add answers to week 8 assignments)
        resultText.setText("$" + dollar);
    }

    public void euroToPound(View view) {
        getEuroAmount();
<<<<<<< HEAD
        String pound = String.format("%.2f", euroInteger * 0.83);
=======
        String pound = String.format("%.2f", euroDouble * 0.83);
>>>>>>> 24f20e4 (add answers to week 8 assignments)
        resultText.setText("£" + pound);
    }
}