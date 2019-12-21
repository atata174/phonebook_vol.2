package ru.razhapov.dinoApp.phonebookDino;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserManage implements UserMng {
    private User user;

    public UserManage(User user) {
        this.user = user;
    }

    public User addUser(String name, String number){
        return new User(name, number);
    }

    // проверяем строку на наличие цифр
    public static boolean checkString(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;// false при строковом аргументе
        }
        return true;// true при числах
    }

    // получение пользователя по id
    public User userGetId(int id){
        User user = null;
        for (int i = 0; i < User.usersList.size(); i++) {
            if(User.usersList.get(i).getId() == id){
                user = User.usersList.get(i);
                break;
            }
            else{
            }
        }
        return user;
    }

    // показать всех пользователей(не контактов)
    public List<User> showAllUsers() {
        return User.usersList;
    }

    // удаление пользователя(не контакта)
    public void removeUser(String name){
        int count = 0;
        for (int i = 0; i < User.usersList.size(); i++) {
            if(User.usersList.get(i).getName().equals(name)){
                for(int j = User.usersList.get(i).userPhoneBook.size()-1; j >= 0;){
                    User.usersList.get(i).userPhoneBook.remove(j);
                    j--;
                    count++;
                }
                User.usersList.remove(i);
                break;
            }
        }
    }

    // поиск пользователя(не контакта)
    public List<User> findUser(String name){
        java.util.List<User> list = new ArrayList<User>();
        int count = 0;
        for (int i = 0; i < User.usersList.size(); i++) {
            if(User.usersList.get(i).getName().contains(name)){
                list.add(User.usersList.get(i));
                count++;
            }
            else{
            }
        }
        return list;
    }

    // редактируем пользователя
    public String editUser(String name, String number){
        try {
            if(!checkString(name) && checkString(number)){ // если с аргументами все хорошо(имя это не цифры, а номер телефона это не буквы)
                for (int i = 0; i < User.usersList.size(); i++) { // цикл по списку пользователей
                    if (!name.isEmpty() && !number.isEmpty()){ // если имя и номер не пустые
                        if (User.usersList.get(i).getName().equals(user.getName())) {
                            User.usersList.get(i).setName(name); // изменяем имя у пользователя
                            User.usersList.get(i).setNumber(number); // меняем его номер
                            return "Редактируем имя и номер";
                        }
                    }
                    else if(number.isEmpty()) { // если пустой только номер изменяем имя
                        if (User.usersList.get(i).getName().equals(user.getName())) {
                            User.usersList.get(i).setName(name);
                            return "Редактируем имя!";
                        }
                    }
                    else if (name.isEmpty()){ // если пустое имя, изменяем только номер телефона
                        if (User.usersList.get(i).getName().equals(user.getName())) {
                            User.usersList.get(i).setNumber(number);
                            return "Редактируем номер!";
                        }
                    }
                }
            }
            else { // если есть ошибка в имени то выводим соответсвующее сообщение
                return "Некорректно введено имя: " + name;
            }
        } catch (Exception e){ // выбрасывает эксипшн при неправильном вводе номера телефона
            return "Некорректно введен номер: " + number;
        }
        return "Введен некорректный символ: " + name + ", " + number;
    }

    // добавление контакта в телефонную книгу пользователя
    public String addContactList(String name, String number){
        user.userPhoneBook.add(new Contact(name, number));
        return "Контакт " + name + number +" добавлен";
    }


    // получение контакта по id
    public Contact contactsGetId(int id){
        Contact contact = null;
        System.out.println("Получаем контакт по id: "+ id +" у пользователя " + user.getName());
        for (int i = 0; i < user.userPhoneBook.size(); i++) {
            if(User.usersList.get(i).getId() == id){
                contact = user.userPhoneBook.get(i);
                break;
            }
            else{
            }
        }
        return contact;
    }

    //поиск контакта в телефонной книге пользователя номеру телефона
    public List<Contact> findContact(String number){
        List<Contact> list = new ArrayList<Contact>();
        int count = 0;
        System.out.println("Поиск контакта с номером телефона: " + number + " в телефонной книге пользователя " + user.getName());
        for (int i = 0; i < user.userPhoneBook.size(); i++) {
            if(user.userPhoneBook.get(i).getNumber().equals(number)){
                list.add(user.userPhoneBook.get(i));
                count++;
            }
            else{
            }
        }
        if(count > 0) {
            return list;
        }
        else{
            System.out.println("Нет такого контакта с таким номером: " + number + " в телефонной книге пользователя " + user.getName());
        }
        return list;
    }

    // удаление контакта в телефонной книге
    public void removeContact(String name){
        int count = 0;
        System.out.println("Удаление контакта " + name + " пользователя " + user.getName());
        if(!name.isEmpty() && !checkString(name)){
            for (int i = 0; i < user.userPhoneBook.size(); i++) {
                if(name.equals(user.userPhoneBook.get(i).getName())){
                    user.userPhoneBook.remove(i);
                    System.out.println("Контакт " + name +" удалён \n");
                    count++;
                    break;
                }
            }
            if(count == 0){
                System.out.println("Контакт "+ name +" не найден\n");
            }
        }
        else if(name.isEmpty()){
            System.out.println("Пустое значение\n");
        }
        else if (checkString(name)){
            System.out.println("Вы ввели цифры\n");
        }
    }

    // показать все контакты пользователя
    public void showAllContactUserPhoneBook(){
        System.out.println("Список всех контактов, пользователя " +user.getName()+ ": ");
        for(int i = 0; i < user.userPhoneBook.size()-1; i++){
            System.out.println(user.userPhoneBook.toString());
        }

        System.out.println("");
    }

    // редактирование контакта в телефонной книге пользователя
    public List<Contact> editContactInUserPhoneBook(String oldname, String newname, String number){
        try {
            if(!checkString(newname) && checkString(number)){ // если с аргументами все хорошо(имя это не цифры, а номер телефона это не буквы)
                System.out.println("Редактируем контакт " + oldname + " пользователя " + user.getName());
                for (int i = 0; i < user.userPhoneBook.size(); i++) { // цикл по списку пользователей
                    if (!newname.isEmpty() && !number.isEmpty()){ // если имя и номер не пустые
                        if (user.userPhoneBook.get(i).getName().equals(oldname)) {
                            System.out.println("Редактируем имя и номер");
                            user.userPhoneBook.get(i).setName(newname); // изменяем имя у контакта
                            user.userPhoneBook.get(i).setNumber(number); // меняем его номер
                            return user.userPhoneBook;
                        }
                    }
                    else if(number.isEmpty()) { // если пустой только номер изменяем имя
                        System.out.println("Редактируем имя");
                        if (user.userPhoneBook.get(i).getName().equals(oldname)) {
                            user.userPhoneBook.get(i).setName(newname);
                            return user.userPhoneBook;
                        }
                    }
                    else if (newname.isEmpty()){ // если пустое имя, изменяем только номер телефона
                        System.out.println("Редактируем номер");
                        if (user.userPhoneBook.get(i).getName().equals(oldname)) {
                            user.userPhoneBook.get(i).setNumber(number);
                            return user.userPhoneBook;
                        }
                    }
                }
            }
            else if(!checkString(newname)){ // если если имя введено верно, но номер с ошибкой
                System.out.println("Некорректно введен номер: " + number);
            }
            else { // если есть ошибка в имени то выводим соответсвующее сообщение
                System.out.println("Некорректно введено имя: " + newname);
            }
        } catch (Exception e){ // выбрасывает эксипшн при неправильном вводе номера телефона
            System.out.println("Некорректно введен номер: " + number);
        }
        return user.userPhoneBook;
    }

}
