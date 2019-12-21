package ru.razhapov.dinoApp.phonebookDino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public interface UserMng {

    public User addUser(String name, String number);

    public User userGetId(int id);
    // показать всех пользователей(не контактов)
    public List<User> showAllUsers();

    // удаление пользователя(не контакта)
    public void removeUser(String name);

    // поиск пользователя(не контакта)
    public List<User> findUser(String name);

    // редактируем пользователя
    public String editUser(String name, String number);

    // добавление контакта в телефонную книгу пользователя
    public String addContactList(String name, String number);

    // получение контакта по id
    public Contact contactsGetId(int id);

    //поиск контакта в телефонной книге пользователя номеру телефона
    public List<Contact> findContact(String number);

    // удаление контакта в телефонной книге
    public void removeContact(String name);

    // показать все контакты пользователя
    public void showAllContactUserPhoneBook();

    // редактирование контакта в телефонной книге пользователя
    public List<Contact> editContactInUserPhoneBook(String oldname, String newname, String number);
}
