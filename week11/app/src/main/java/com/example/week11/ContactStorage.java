package com.example.week11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ContactStorage {
    private static ContactStorage contactStorage = null;
    private ArrayList<Contact> contacts = new ArrayList<>();

    private ContactStorage() {

    }

    public void sortContactsAlphabetically() {
        Collections.sort(contacts, (c1, c2) -> c1.getFullName().compareToIgnoreCase(c2.getFullName()));
    }

    public void sortContactsByGroup() {

            ArrayList<Contact> workContacts = new ArrayList<>();
            ArrayList<Contact> personalContacts = new ArrayList<>();

            Iterator<Contact> iterator = contacts.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                if (contact.getContactGroup().equals("Työ")) {
                    workContacts.add(contact);
                } else {
                    personalContacts.add(contact);
                }
            }

        contacts.clear();
        contacts.addAll(workContacts);
        contacts.addAll(personalContacts);

    }

    public static ContactStorage getInstance() {
        if (contactStorage == null) {
            contactStorage = new ContactStorage();
        }
        return contactStorage;
    }
    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    public void addContact(Contact newContact) {
        contacts.add(newContact);
    }
    public void removeContact(int id) {
        contacts.remove(id);
    }

}
