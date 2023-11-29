package ru.geekbrains.hometask1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        System.out.println("Unique depts:");
        getUniqueDepts(stuff);
        System.out.println("----------");

        System.out.println("Increase salary:");
        salaryIncrease(stuff, 10000, 20);
        System.out.println("----------");

        System.out.println("Map employee:");
        getMapEmployee(stuff);
        System.out.println("----------");

        System.out.println("Map salary:");
        getMapSalary(stuff);
        System.out.println("----------");
    }

    /**
     * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
     */
    private static void getUniqueDepts(List<Employee> stuff) {
        stuff.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
    }

    /**
     * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
     */
    private static void salaryIncrease(List<Employee> stuff, int minSalary, int percent) {
        stuff.stream().filter(s -> s.getSalary() < minSalary).forEach(s -> s.setSalary(s.getSalary() * (1 + (double)percent / 100)));
        stuff.stream().forEach(System.out::println);
    }

    /**
     * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
     */
    private static void getMapEmployee(List<Employee> stuff) {
        Map<String, List<Employee>> mapEmployee =
                stuff.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        mapEmployee.forEach((key, value) -> System.out.println("Department: " + key + "\nEmployees:" + value));
    }

    /**
     * 2.5 * Из списка сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
     */
    private static void getMapSalary(List<Employee> stuff) {
        System.out.println("Department\t | \tAverage salary by dept");
        Map<String, Double> mapSalary =
                stuff.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        mapSalary.forEach((key, value) -> System.out.println(key + "\t | \t" + value));
    }

}

