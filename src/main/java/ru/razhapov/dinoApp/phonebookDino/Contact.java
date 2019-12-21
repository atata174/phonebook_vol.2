package ru.razhapov.dinoApp.phonebookDino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Contact {
    private String name;
    private String number;
    private static long contactId = 0;
    private long id = 0;


    private Contact() { }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        this.id = ++contactId;
    }

    @Override
    public String toString() {
        return "name: " + this.name
                + " number: " + this.number
                + " id: " + this.id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static long getContactId() {
        return contactId;
    }

    public static void setContactId(long contactId) {
        Contact.contactId = contactId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
