package com.library;

/**
 * Класс, представляющий книгу в библиотеке
 */
public class Book extends LibraryItem {
    private String author;
    /**
     * Конструктор книги
     * @param title название книги
     * @param author автор книги
     * @param year год написания
     */
    public Book(String title, String author, int year) {
        super(title, year);
        this.author = author;
    }
    /**
     * Получить автора книги
     * @return автор книги
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Получить полную информацию о книге
     * @return строка с информацией о книге
     */
    public String toString() {
        return "Книга: " + title + ", Автор: " + author + ", Год: " + year;
    }
}