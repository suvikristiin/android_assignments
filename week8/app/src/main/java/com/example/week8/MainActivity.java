package com.example.week8;

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

public class MainActivity extends AppCompatActivity {
    private EditText euroInput;
    private TextView resultText;
    private Button USDButton;
    private Button GDPButton;
    private double euroInteger;
    private double euroDouble;


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

    }

    public void getEuroAmount() {
        String euroString = euroInput.getText().toString();
        euroInteger = Double.parseDouble(euroString);
        euroDouble = Double.parseDouble(euroString);
    }

    public void euroToDollar(View view) {
        getEuroAmount();
        String dollar = String.format("%.2f", euroInteger * 1.05);
        resultText.setText("$" + dollar);
    }

    public void euroToPound(View view) {
        String pound = String.format("%.2f", euroDouble * 0.83);
        resultText.setText("£" + pound);
    }
}