package ru.geekbrains.hometask3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Phonebook implements Serializable {

    private List<Contact> contactList;

    public Phonebook() {
        this.contactList = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    public void removeContact(Contact contact) {
        contactList.remove(contact);
    }

    public List<Contact> getAll() {
        return contactList;
    }

    public List<Contact> getByName(String name) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contactList) {
            if(contact.getName().equals(name)) {
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Phonebook{" +
                "contactList=" + contactList +
                '}';
    }
}
