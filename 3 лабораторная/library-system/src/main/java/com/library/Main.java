package com.library;

/**
 * Главный класс для демонстрации работы библиотечной системы
 */
public class Main {
    /**
     * Точка входа в программу
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Library lib1 = new Library("Первая");
        Library lib2 = new Library("Вторая");

        Book book1 = new Book("Репка", "А.Н. Толстой", 1863);
        Book book2 = new Book("Курочка Ряба", "А.Н. Афанасьев", 1864);
        Book book3 = new Book("Теремок", "А.Н. Толстой", 1867);
        Book book4 = new Book("Лиса и Заяц", "А.Н. Афанасьев", 1873);
        Book book5 = new Book("Волк и козлята", "А.Н. Толстой", 1856);
        Book book6 = new Book("Гуси-лебеди", "А.Н. Афанасьев", 1871);
        Book book7 = new Book("Колобок", "Я", 2024);

        Magazine magazine1 = new Magazine("Трансформеры", 2012, 9);
        Magazine magazine2 = new Magazine("Настоящие насекомые", 2010, 14);

        lib1.addBook(book1);
        lib1.addBook(book3);
        lib1.addBook(book4);
        lib1.addBook(book7);

        lib2.addBook(book2);
        lib2.addBook(book5);
        lib2.addBook(book6);

        lib1.addMagazine(magazine1);
        lib1.addMagazine(magazine2);

        System.out.println("Книги А.Н. Толстого в " + lib1.getName() + ":");
        for (Book book : lib1.getBooksByAuthor("А.Н. Толстой")) {
            System.out.println("  " + book);
        }

        System.out.println("\nКниги А.Н. Афанасьева в " + lib2.getName() + ":");
        for (Book book : lib2.getBooksByAuthor("А.Н. Афанасьев")) {
            System.out.println("  " + book);
        }

        System.out.println("\nВсе книги в библиотеках:");
        System.out.println(lib1);
        for (Book book : lib1.getBooks()) {
            System.out.println("  " + book);
        }
        System.out.println(lib2);
        for (Book book : lib2.getBooks()) {
            System.out.println("  " + book);
        }
    }
}