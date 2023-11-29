package ru.geekbrains.hometask1;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списка сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */
public class Task2 {

    public static void main(String[] args) {

        // 2.1 Создать список из 10-20 сотрудников
        List<Employee> stuff = new ArrayList<>();
        stuff.add(new Employee("Alexandr Petrov", 35,18000, "Legal"));
        stuff.add(new Employee("Alexey Ivanov", 45,8000, "Sales"));
        stuff.add(new Employee("Irina Fedorova", 30,9000, "Accounting"));
        stuff.add(new Employee("Elena Petrova", 45,12000, "Accounting"));
        stuff.add(new Employee("Boris Nemtsov", 45,18000, "SEO"));
        stuff.add(new Employee("Petr Pavlov", 35,10000, "Legal"));
        stuff.add(new Employee("Pavel Petrov", 25,8000, "Sales"));
        stuff.add(new Employee("Pavel Ivanov", 30,12000, "IT"));
        stuff.add(new Employee("Alexey Pavlov", 35,15000, "IT"));
        stuff.add(new Employee("Dmitry Alexeev", 40,9000, "Sales"));
        stuff.add(new Employee("Alena Sergeeva", 33,11000, "Marketing"));
        stuff.add(new Employee("Sergey Borisov", 50,12000, "Analytic"));

        // 2.2 Вывести список всех различных отделов (department) по списку сотрудников
        System.out.println(stuff.stream().filt;
    }

}

