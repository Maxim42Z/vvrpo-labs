package com.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-репозиторий для управления коллекцией элементов библиотеки
 * @version 1.0
 */
public class LibraryRepository {
    // Список для всех элементов библиотеки
    private List<LibraryItem> items;

    /**
     * Конструктор репозитория
     */
    public LibraryRepository() {
        this.items = new ArrayList<>();
    }
    /**
     * Добавляет элемент в коллекцию репозитория
     * @param item элемент для добавления
     */
    public void addItem(LibraryItem item) {
        items.add(item);
    }

    /**
     * Удаляет элемент из коллекции по индексу
     * @param index индекс элемента для удаления
     * @return true если удаление успешно, false если индекс неверный
     */
    public boolean removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Изменяет элемент в коллекции по индексу
     * @param index индекс элемента для изменения
     * @param newItem новый элемент
     * @return true если изменение успешно, false если индекс неверный
     */
    public boolean updateItem(int index, LibraryItem newItem) {
        if (index >= 0 && index < items.size()) {
            items.set(index, newItem);
            return true;
        }
        return false;
    }

    /**
     * Возвращает все элементы репозитория
     * @return копия списка всех элементов
     */
    public List<LibraryItem> getAllItems() {
        return new ArrayList<>(items);
    }

    /**
     * Получить общее количество элементов
     * @return общее количество элементов
     */
    public int getTotalItemCount() {
        return items.size();
    }
}