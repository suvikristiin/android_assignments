package com.example.week11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addNewContact(View view) {

        RadioGroup rgRocketType = findViewById(R.id.ContactTypeRadioGroup);

        EditText firsName = findViewById(R.id.FirstNameEdit);
        EditText lastName = findViewById(R.id.LastNameEdit);
        EditText phoneNumber = findViewById(R.id.PhoneNumberEdit);
        String radioChoice = "";

        int selectedId = rgRocketType.getCheckedRadioButtonId();

        if (selectedId == R.id.PersonalRadioButton) {
            radioChoice = "Henkilökohtainen";
        } else if (selectedId == R.id.WorkRadioButton) {
            radioChoice = "Työ";
        } else {
            radioChoice = "";
        }

        Contact newContact = new Contact(firsName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString(), radioChoice);
        ContactStorage.getInstance().addContact(newContact);

    }

    public void moveToMainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}