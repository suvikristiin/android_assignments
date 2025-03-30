package com.example.week11;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView contactNameText, contactNumberText, contactGroupText;
    Button contactDetailsButton, contactDeleteButton;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        contactNameText = itemView.findViewById(R.id.ContactNameText);
        contactNumberText = itemView.findViewById(R.id.ContactNumberText);
        contactGroupText = itemView.findViewById(R.id.ContactGroupText);
        contactDetailsButton = itemView.findViewById(R.id.ContactDetailsButton);
        contactDeleteButton = itemView.findViewById(R.id.ContactDeleteButton);
    }
}
