package ru.razhapov.dinoApp.phonebookDino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactManage {
    private User user;
    private Contact contact;

    // проверяем строку на наличие цифр
    public static boolean checkString(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;// false при строковом аргументе
        }
        return true;// true при числах
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
            if(user.usersList.get(i).getId() == id){
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
            return null;
        }

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
    public void editContactInUserPhoneBook(String oldname, String newname, String number){
        try {
            if(!checkString(newname) && checkString(number)){ // если с аргументами все хорошо(имя это не цифры, а номер телефона это не буквы)
                System.out.println("Редактируем контакт " + oldname + " пользователя " + user.getName());
                for (int i = 0; i < user.userPhoneBook.size(); i++) { // цикл по списку пользователей
                    if (!newname.isEmpty() && !number.isEmpty()){ // если имя и номер не пустые
                        if (user.userPhoneBook.get(i).getName().equals(oldname)) {
                            System.out.println("Редактируем имя и номер");
                            user.userPhoneBook.get(i).setName(newname); // изменяем имя у контакта
                            user.userPhoneBook.get(i).setNumber(number); // меняем его номер
                        }
                    }
                    else if(number.isEmpty()) { // если пустой только номер изменяем имя
                        System.out.println("Редактируем имя");
                        if (user.userPhoneBook.get(i).getName().equals(oldname)) {
                            user.userPhoneBook.get(i).setName(newname);
                        }
                    }
                    else if (newname.isEmpty()){ // если пустое имя, изменяем только номер телефона
                        System.out.println("Редактируем номер");
                        if (user.userPhoneBook.get(i).getName().equals(oldname)) {
                            user.userPhoneBook.get(i).setNumber(number);
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
        System.out.println("");
    }
}
