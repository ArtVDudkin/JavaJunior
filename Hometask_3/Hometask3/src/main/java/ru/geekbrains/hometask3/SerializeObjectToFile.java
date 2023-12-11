package ru.geekbrains.hometask3;

import java.io.*;
import java.util.UUID;

/**
 * Класс для сериализации и десериализации объектов и сохранения в файл
 */
public class SerializeObjectToFile {
    private final String path = SerializeObjectToFile.class.getName() + "_" + UUID.randomUUID().toString();

    public String getPath() {
        return path;
    }

    /**
     * Метод для записи объекта в файл (сериализации)
     * @param object записываемый объект
     * @param <T>    класс записываемого объекта
     */
    public <T> void writeObjectToFile(T object) {
        try (FileOutputStream outputStream = new FileOutputStream(path);
             ObjectOutput objectOutput = new ObjectOutputStream(outputStream)) {
            objectOutput.writeObject(object);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Файл " + path + " не существует (" + e.getMessage() + ")");
            } else {
                System.out.println("Ошибка записи файла (" + e.getMessage() + ")");
            }
        }
    }

    /**
     * Метод для чтения объекта из файла (десериализации)
     * @param path путь к файлу для чтения
     */
    public void readObjectFromFile(String path) {
        try (FileInputStream inputStream = new FileInputStream(path);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            System.out.println(objectInputStream.readObject());
            File inputStreamFile = new File(path);
            inputStreamFile.delete();
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Файл " + path + " не существует (" + e.getMessage() + ")");
            } else if (e instanceof ClassNotFoundException) {
                System.out.println("Класс, считанный из файла " + path + " не найден (" + e.getMessage() + ")");
            } else {
                System.out.println("Ошибка чтения файла (" + e.getMessage() + ")");
            }
        }
    }
}

