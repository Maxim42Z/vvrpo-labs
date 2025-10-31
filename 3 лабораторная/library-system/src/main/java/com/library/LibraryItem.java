package com.library;

/**
 * Абстрактный класс,представляющий элемент библиотеки
 */
public abstract class LibraryItem {
    protected String title;
    protected int year;
    /**
     * Конструктор элемента библиотеки
     * @param title название элемента
     * @param year год создания
     */
    public LibraryItem(String title, int year) {
        this.title = title;
        this.year = year;
    }
    /**
     * Получить название элемента
     * @return название элемента
     */
    public String getTitle() {
        return title;
    }
    /**
     * Получить год создания
     * @return год создания
     */
    public int getYear() {
        return year;
    }
    /**
     * Получить полную информацию об элементе
     * @return строка с информацией об элементе
     */
    public String toString() {
        return "Название: " + title + ", Год: " + year;
    }
}