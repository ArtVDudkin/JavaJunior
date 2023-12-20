package ru.geekbrains.hometask4.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DB {
    final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    Book book;

    /**
     * Метод создания и заполнения таблицы BOOKS
     */
    public void createTableBook() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Author author1 = new Author("Пушкин А.С.");
            session.persist(author1);

            Author author2 = new Author("Лермонтов М.Ю.");
            session.persist(author2);

            Author author3 = new Author("Гоголь Н.В.");
            session.persist(author3);

            Author author4 = new Author("Чехов А.П.");
            session.persist(author4);

            Author author5 = new Author("Толстой Л.Н.");
            session.persist(author5);


            book = new Book("Евгений Онегин", author1);
            session.persist(book);

            book = new Book("Мцыри", author2);
            session.persist(book);

            book = new Book("Ревизор", author3);
            session.persist(book);

            book = new Book("Дубровский", author1);
            session.persist(book);

            book = new Book("Каштанка", author4);
            session.persist(book);

            book = new Book("Война и мир", author5);
            session.persist(book);

            book = new Book("Герой нашего времени", author2);
            session.persist(book);

            book = new Book("Ночь перед рождеством", author3);
            session.persist(book);

            book = new Book("Мертвые души", author3);
            session.persist(book);

            book = new Book("Вишневый сад", author4);
            session.persist(book);

            book = new Book("Анна Каренина", author5);
            session.persist(book);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска в базе данных по автору
     * @param searchAuthor строка (имя автора)
     */
    public void getBooksByAuthor(String searchAuthor) {
        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery(
                            "FROM Book WHERE author = (FROM Author WHERE nameAuthor = :name_author)", Book.class
                    ).setParameter("name_author", searchAuthor)
                    .getResultList();

            books.forEach(System.out::println);
        }
    }

    /**
     * Метод закрытия сессии
     */
    public void closedSession() {
        sessionFactory.close();
    }
}