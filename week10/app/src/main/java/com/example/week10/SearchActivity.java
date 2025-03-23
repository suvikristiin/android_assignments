package com.example.week10;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SearchActivity extends AppCompatActivity {
    private EditText CityNameEdit;

    private EditText YearEdit;

    private TextView StatusText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CityNameEdit = findViewById(R.id.CityNameEdit);
        YearEdit = findViewById(R.id.YearEdit);
        StatusText = findViewById(R.id.StatusText);

    }

    public void moveToListActivity(View view) {
        Intent intent = new Intent(this, ListInfoActivity.class);
        startActivity(intent);
    }

    public void searchButtonClick(View view) {
        String cityName = CityNameEdit.getText().toString();
        String yearStr = YearEdit.getText().toString().trim();

        int year;
        try {
            year = Integer.parseInt(yearStr);

        } catch (NumberFormatException e) {
            StatusText.setText("Vuoden tulee olla numero");
            return;
        }

        if (cityName.isEmpty()) {
            StatusText.setText("Anna kaupunki");
            return;
        }

        getData(this, cityName, year);
    }

    public void getData(Context context, String city, int year) {
        StatusText.setText("Haetaan");
        new Thread(() -> {

            ObjectMapper objectMapper = new ObjectMapper();


                JsonNode areas = null;
            try {

                areas = objectMapper.readTree(new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/mkan/statfin_mkan_pxt_11ic.px"));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            ArrayList<String> keys = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            for (JsonNode node : areas.get("variables").get(3).get("values")) {
                values.add(node.asText());
            }
            for (JsonNode node : areas.get("variables").get(3).get("valueTexts")) {
                keys.add(node.asText());
            }

            HashMap<String, String> yearMap = new HashMap<>();

            for(int i = 0; i < keys.size(); i++) {
                yearMap.put(keys.get(i), values.get(i));
            }
            Log.d("yearmap", "getData: " + yearMap);

            String yearCode = null;

            yearCode = yearMap.get(Integer.toString(year));

            if (yearCode == null) {
                runOnUiThread(() -> StatusText.setText("Haku epäonnistui, vuosi on kirjoitettu väärin."));
                return;
            }

            ArrayList<String> municipalityKeys = new ArrayList<>();
            ArrayList<String> municipalityValues = new ArrayList<>();

            for (JsonNode node : areas.get("variables").get(0).get("values")) {
                municipalityValues.add(node.asText());
            }
            for (JsonNode node : areas.get("variables").get(0).get("valueTexts")) {
                municipalityKeys.add(node.asText());
            }

            HashMap<String, String> municipalityMap = new HashMap<>();

            for(int i = 0; i < municipalityKeys.size(); i++) {
                municipalityMap.put(municipalityKeys.get(i), municipalityValues.get(i));
            }
            String municipalityCode = null;


            municipalityCode = municipalityMap.get(city);
            if (municipalityCode == null) {
                runOnUiThread(() -> StatusText.setText("Haku epäonnistui, kaupunkia ei ole olemassa tai se on kirjoitettu väärin."));
                return;
            }



            try {
                URL url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/mkan/statfin_mkan_pxt_11ic.px");

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.query));

                ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(municipalityCode);
                ((ObjectNode) jsonInputString.get("query").get(3).get("selection")).putArray("values").add(yearCode);

                byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
                OutputStream os = con.getOutputStream();
                os.write(input, 0, input.length);

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }

                JsonNode municipalityData = objectMapper.readTree(response.toString());


                ArrayList<String> types = new ArrayList<>();
                ArrayList<String> amounts = new ArrayList<>();
                JsonNode valueNode = municipalityData.get("value");
                JsonNode indexNode = municipalityData.get("dimension").get("Ajoneuvoluokka").get("category").get("index");
                JsonNode labelNode = municipalityData.get("dimension").get("Ajoneuvoluokka").get("category").get("label");

                CarDataStorage storage = CarDataStorage.getInstance();
                storage.clearData();
                storage.setCity(city);
                storage.setYear(year);

                for (String code : List.of("01", "02", "03", "04", "05")) {
                    String type = labelNode.get(code).asText();
                    int index = indexNode.get(code).asInt();
                    int amount = valueNode.get(index).asInt();
                    storage.addCarData(new CarData(type, amount));
                }






                runOnUiThread(() -> StatusText.setText("Haku onnistui"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();


    }

}