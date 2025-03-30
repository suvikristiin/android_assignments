package com.example.week11;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private Context context;
    private ArrayList<Contact> contacts = new ArrayList<>();

    public ContactListAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;

    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contactNameText.setText(contacts.get(position).getFullName());
        holder.contactNumberText.setText(contacts.get(position).getNumber());
        holder.contactGroupText.setText(contacts.get(position).getContactGroup());

        holder.contactNumberText.setVisibility(View.GONE);
        holder.contactGroupText.setVisibility(View.GONE);

        holder.contactDeleteButton.setOnClickListener(view -> {


            int pos = holder.getAdapterPosition();
            Log.d("id", "onBindViewHolder: " + pos);
            ContactStorage.getInstance().removeContact(pos);
            notifyItemRemoved(pos);


        });

        holder.contactDetailsButton.setOnClickListener(view -> {
            if (holder.contactGroupText.getVisibility() == View.VISIBLE) {
                holder.contactNumberText.setVisibility(View.GONE);
                holder.contactGroupText.setVisibility(View.GONE);
            } else {
                holder.contactNumberText.setVisibility(View.VISIBLE);
                holder.contactGroupText.setVisibility(View.VISIBLE);
            }

        });




    }

    public void updateData(ArrayList<Contact> newContacts) {
        Log.d("newContacts", "updateData: "+ newContacts);
        contacts.clear();
        contacts.addAll(newContacts);

    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
