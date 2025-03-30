package com.example.week11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private ContactStorage contactStorage;
    private RecyclerView recyclerView;
    private ContactListAdapter adapter;

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

        contactStorage = ContactStorage.getInstance();
        recyclerView = findViewById(R.id.ListContactsRV);


        adapter = new ContactListAdapter(getApplicationContext(), contactStorage.getContacts());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void moveToAddContact(View view) {

        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);

    }

    public void sortAlphabetically(View view) {
        ContactStorage.getInstance().sortContactsAlphabetically();
        adapter.notifyDataSetChanged();
    }

    public void sortGroup(View view) {
        ContactStorage.getInstance().sortContactsByGroup();


        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();

    }
}