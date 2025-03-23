package com.example.week10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListInfoActivity extends AppCompatActivity {
    private TextView CityText;
    private TextView YearText;
    private TextView CarInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CityText = findViewById(R.id.CityText);
        YearText = findViewById(R.id.YearText);
        CarInfoText = findViewById(R.id.CarInfoText);
        CarDataStorage storage = CarDataStorage.getInstance();


        // Jos kaupunkia tai autodata ei ole tallennettu
        if (storage.getCity() == null || storage.getCarData().isEmpty()) {
            CarInfoText.setText("Tietoja ei ole haettu vielä.");
            return;
        }

        CityText.setText(storage.getCity().toString());
        String yearString = String.valueOf(storage.getYear());
        YearText.setText(yearString);

        ArrayList<CarData> carsInfo = storage.getCarData();
        String infoText = "";
        int total = 0;
        for (CarData info : carsInfo) {
            infoText += info.getType() + ": " + info.getAmount() + "\n";
            total += info.getAmount();
        }

        infoText += "\nYhteensä: " + total;

        Log.d("tag", "onCreate: " + infoText);
        CarInfoText.setText(infoText);
    }
    public void moveToMainActivity2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}