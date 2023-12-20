package ru.geekbrains.hometask4.jdbc;

import java.sql.*;

public class DB {
    private final String nameTable = "books";
    private final String columnId = "id";
    private final String columnName = "name_book";
    private final String columnAuthor = "author";

    /**
     * Метод создания таблицы BOOKS в базе данных
     * @param connection соединение с базой данных
     */
    public void prepareTables(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(""" 
                    create table %s(
                    %s bigint,
                    %s varchar(100),
                    %s varchar(100)
                    );
                    """.formatted(nameTable, columnId, columnName, columnAuthor)
            );
            System.out.println("Создана таблица " + nameTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод заполнения таблицы BOOKS в базе данных
     * @param connection соединение с базой данных
     */
    public void insertData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int updatedRows = statement.executeUpdate("""
                insert into %s(%s, %s, %s)
                    values(1, 'Евгений Онегин', 'Пушкин А.С.'),
                          (2, 'Мцыри', 'Лермонтов М.Ю.'),
                          (3, 'Ревизор', 'Гоголь Н.В.'),
                          (4, 'Дубровский', 'Пушкин А.С.'),
                          (5, 'Каштанка', 'ЭЧехов А.П.'),
                          (6, 'Война и мир', 'Толстой Л.Н.'),
                          (7, 'Герой нашего времени', 'Лермонтов М.Ю.'),
                          (8, 'Ночь перед рождеством', 'Гоголь Н.В.'),
                          (9, 'Мертвые души', 'Гоголь Н.В.'),
                          (10, 'Вишневый сад', 'Чехов А.П.')
                    """.formatted(nameTable, columnId, columnName, columnAuthor));

            System.out.println("В таблицу " + nameTable + " добавлено " + updatedRows + " строк");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод получения записи из базы данных
     * @param connection соединение с базой данных
     */
    public void getData(Connection connection, String searchAuthor) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select * from %s where %s = '%s'
                    """.formatted(nameTable, columnAuthor, searchAuthor)
            );
            while (resultSet.next()) {
                int id = resultSet.getInt(columnId);
                String name = resultSet.getString(columnName);
                String author = resultSet.getString(columnAuthor);
                System.out.println(columnId + ": " + id + ",\n" +
                        columnName + ": " + name + ",\n" +
                        columnAuthor + ": " + author
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод создания соединения с базой данных
     */
    public Connection dbConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db");
            Statement statement = connection.createStatement();
//            statement.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName); // если не файловая бд
            statement.close();
            System.out.println("Соединение с БД установлено");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод закрытия соединения с базой данных
     * @param connection соединение с базой данных
     */
    public void dbClose(Connection connection) {
        try {
            connection.close();
            System.out.println("Соединение с БД разорвано");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
