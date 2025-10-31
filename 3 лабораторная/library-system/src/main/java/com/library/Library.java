package com.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий библиотеку с коллекцией элементов
 */
public class Library {
    private String name;
    private List<Book> books;
    private List<Magazine> magazines;
    /**
     * Конструктор библиотеки
     * @param name название библиотеки
     */
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.magazines = new ArrayList<>();
    }
    /**
     * Добавить книгу в библиотеку
     * @param book объект книги для добавления
     */
    public void addBook(Book book) {
        books.add(book);
    }
    /**
     * Добавить журнал в библиотеку
     * @param magazine объект журнала для добавления
     */
    public void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }
    /**
     * Найти все книги указанного автора
     * @param author автор для поиска
     * @return список книг автора
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }
    /**
     * Получить название библиотеки
     * @return название библиотеки
     */
    public String getName() {
        return name;
    }
    /**
     * Получить все книги библиотеки
     * @return список всех книг
     */
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
    /**
     * Получить все журналы библиотеки
     * @return список всех журналов
     */
    public List<Magazine> getMagazines() {
        return new ArrayList<>(magazines);
    }
    /**
     * Получить полную информацию о библиотеке
     * @return строка с информацией о библиотеке
     */
    public String toString() {
        return "Библиотека: " + name + ", Книг: " + books.size() + ", Журналов: " + magazines.size();
    }
}