package ru.razhapov.dinoApp.phonebookDino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class  User{
    private String name;
    private String number;
    private static int userId = 0;
    private int id = 0;
    public static List<User> usersList = new ArrayList<User>();
    public List<Contact> userPhoneBook = new ArrayList<Contact>();
    private Contact contacts;

    // конструктор

    public User(String name, String number) {
        this.id = ++userId;
        this.name = name;
        this.number = number;
        usersList.add(this);
    }

    public User() { }

    @Override
    public String toString() {
        return "\nИмя: " + this.name
                + "\n Номер: " + this.number
                + "\n id: " + this.id
                + "\n Телефонный справочник: " + userPhoneBook
                + "\n";

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

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        User.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<User> getUsersList() {
        return usersList;
    }

    public static void setUsersList(List<User> usersList) {
        User.usersList = usersList;
    }

    public List<Contact> getUserPhoneBook() {
        return userPhoneBook;
    }

    public void setUserPhoneBook(List<Contact> userPhoneBook) {
        this.userPhoneBook = userPhoneBook;
    }

    public Contact getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }
}
