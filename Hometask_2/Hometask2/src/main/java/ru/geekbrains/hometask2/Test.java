package ru.geekbrains.hometask2;

public class Test {

    @TestAnnotation(order = 5)
    public static void thirdTest() {
        System.out.println("Third test запущен");
    }

    @TestAnnotation(order = -2)
    public static void firstTest() {
        System.out.println("First test запущен");
    }

    @TestAnnotation(order = 10)
    @Skip
    public static void skipTest() {
        System.out.println("Skip test запущен");
    }

    @TestAnnotation
    public static void secondTest() {
        System.out.println("Second test запущен");
    }

    @BeforeEach
    public static void beforeEachTest() {
        System.out.println("before each test запущен");
    }

    @AfterEach
    public static void afterEachTest() {
        System.out.println("after each test запущен");
    }

    @BeforeEach
    public static void beforeEachTestTwo() {
        System.out.println("before each test two запущен");
    }

    @TestAnnotation
    @DisplayName
    public static void displayNameTest() {
        System.out.println("DisplayName test запущен");
    }
}
