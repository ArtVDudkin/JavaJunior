package ru.geekbrains.hometask4.hibernate;

public class Program {

    public static void main(String[] args) {

        String searchAuthor = "Гоголь Н.В.";

        DB db = new DB();
        db.createTableBook();
        db.getBooksByAuthor(searchAuthor);
        db.closedSession();
    }

}
