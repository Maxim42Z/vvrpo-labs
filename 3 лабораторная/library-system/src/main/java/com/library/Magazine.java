package com.library;

/**
 * Класс, представляющий журнал в библиотеке
 */
public class Magazine extends LibraryItem {
    private int Number;
    /**
     * Конструктор журнала
     * @param title название журнала
     * @param year год издания
     * @param Number номер выпуска
     */
    public Magazine(String title, int year, int Number) {
        super(title, year);
        this.Number = Number;
    }
    /**
     * Получить номер выпуска
     * @return номер выпуска
     */
    public int getNumber() {
        return Number;
    }
    /**
     * Получить полную информацию о журнале
     * @return строка с информацией о журнале
     */
    public String toString() {
        return "Журнал: " + title + ", Номер: " + Number + ", Год: " + year;
    }
}