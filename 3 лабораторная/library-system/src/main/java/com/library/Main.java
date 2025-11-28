package com.library;

import java.util.Scanner;

/**
 * Главный класс для демонстрации работы библиотечной системы после рефакторинга
 * РЕФАКТОРИНГ: Добавлен класс-репозиторий
 * @version 2.0
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    // РЕФАКТОРИНГ: Создаем экземпляр репозитория
    private static LibraryRepository repository = new LibraryRepository();
    private static Library library = new Library("Главная библиотека");
    
    public static void main(String[] args) {
        // Создаем начальные данные
        createInitialData();
        
        // Демонстрация работы Library (поиск и фильтрация)
        demonstrateLibraryOperations();
        
        // Демонстрация работы Repository
        demonstrateRepositoryOperations();
        
        scanner.close();
    }
    
    /**
     * Создает начальные данные
     * РЕФАКТОРИНГ: Добавляем данные и в Library и в Repository для демонстрации
     */
    private static void createInitialData() {
        // Создаем книги
        Book book1 = new Book("Репка", "А.Н. Толстой", 1863);
        Book book2 = new Book("Курочка Ряба", "А.Н. Афанасьев", 1864);
        
        // РЕФАКТОРИНГ: Добавляем в репозиторий (новый функционал)
        repository.addItem(book1);
        repository.addItem(book2);
        
        // Добавляем в Library (старый функционал)
        library.addBook(book1);
        library.addBook(book2);
    }
    
    /**
     * Демонстрирует операции через класс Library
     * РЕФАКТОРИНГ: Используем старый функционал поиска
     */
    private static void demonstrateLibraryOperations() {
        System.out.println("\nОПЕРАЦИИ ПОИСКА");
        
        System.out.println(library);
        
        System.out.println("\nКниги А.Н. Толстого:");
        for (Book book : library.getBooksByAuthor("А.Н. Толстой")) {
            System.out.println("  " + book);
        }
    }
    
    /**
     * Демонстрирует операции через репозиторий
     * РЕФАКТОРИНГ: Новый функционал управления данными
     */
    private static void demonstrateRepositoryOperations() {        
        // Операция ДОБАВЛЕНИЯ
        System.out.println("\nОПЕРАЦИЯ ДОБАВЛЕНИЯ:");
        Book newBook = new Book("Новая книга", "Новый автор", 2024);
        repository.addItem(newBook);
        System.out.println("Добавлено: " + newBook);
        
        // Операция УДАЛЕНИЯ
        System.out.println("\nОПЕРАЦИЯ УДАЛЕНИЯ:");
        boolean removed = repository.removeItem(0);
        if (removed) {
            System.out.println("Элемент с индексом 0 удален");
        }
        
        System.out.println("\nФинальное состояние репозитория:");
        for (LibraryItem item : repository.getAllItems()) {
            System.out.println("  " + item);
        }
    }
}