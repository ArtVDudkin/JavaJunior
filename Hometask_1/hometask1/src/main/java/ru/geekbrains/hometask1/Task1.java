package ru.geekbrains.hometask1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 1.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 1.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 */
public class Task1 {

    public static void main(String[] args) {

        // 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        int minNumber = 1;
        int maxNumber = 1_000_000;
        int numberCount = 1_000;
        for(int i = 0; i < numberCount; i++) {
            numbers.add(rand.nextInt(minNumber, maxNumber +1));
        }

        System.out.println("Max value: " + findMax(numbers));

        int moreThan = 500_000;
        System.out.println("Sum of numbers more than " + moreThan + " multipied by 5 and redused by 150: " + moreThan(numbers, moreThan));

        int lessThan = 100_000;
        System.out.println("Count of numbers^2 < " + lessThan + ": " + howMany(numbers, lessThan));
    }

    // 1.1 Найти максимальное
    public static Integer findMax(List<Integer> numbers) {
        return numbers.stream().max(Integer::compare).get();
    }

    // 1.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
    public static Integer moreThan(List<Integer> numbers, int moreThan) {
        return numbers.stream().filter(e -> e > moreThan)
                .map(e -> e * 5 - 150).mapToInt(Integer::intValue).sum();
    }

    // 1.3 Найти количество чисел, квадрат которых меньше, чем 100_000
    public static Integer howMany(List<Integer> numbers, int lessThen) {
        return numbers.stream().filter(e -> e * e < lessThen).toList().size();
    }

}
