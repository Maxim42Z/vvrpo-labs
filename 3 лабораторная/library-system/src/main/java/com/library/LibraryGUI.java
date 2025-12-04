package com.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Графический интерфейс для управления библиотечным репозиторием
 */
public class LibraryGUI extends JFrame {
    private LibraryRepository repository;
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, yearField, authorField, numberField;
    private JComboBox<String> typeComboBox;

    public LibraryGUI() {
        repository = new LibraryRepository();
        createInitialData();
        initUI();
    }

    private void createInitialData() {
        // Начальные данные для демонстрации
        repository.addItem(new Book("Репка", "А.Н. Толстой", 1863));
        repository.addItem(new Book("Курочка Ряба", "А.Н. Афанасьев", 1864));
        repository.addItem(new Magazine("Наука и жизнь", 2024, 5));
    }

    private void initUI() {
        setTitle("Библиотека - Управление коллекцией");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для ввода данных
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Таблица для отображения элементов
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        // Панель с кнопками управления
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        // Обновить таблицу
        refreshTable();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Добавить новый элемент"));

        // Поля ввода
        panel.add(new JLabel("Тип:"));
        typeComboBox = new JComboBox<>(new String[]{"Книга", "Журнал"});
        panel.add(typeComboBox);

        panel.add(new JLabel("Название:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Год:"));
        yearField = new JTextField();
        panel.add(yearField);

        panel.add(new JLabel("Автор (для книги):"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Номер (для журнала):"));
        numberField = new JTextField();
        panel.add(numberField);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Элементы библиотеки"));

        // Модель таблицы
        String[] columns = {"Тип", "Название", "Автор/Номер", "Год"};
        tableModel = new DefaultTableModel(columns, 0);
        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        // Кнопка добавления
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e -> addItem());
        
        // Кнопка обновления
        JButton updateButton = new JButton("Обновить");
        updateButton.addActionListener(e -> updateItem());
        
        // Кнопка удаления
        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(e -> deleteItem());
        
        // Кнопка обновления таблицы
        JButton refreshButton = new JButton("Обновить таблицу");
        refreshButton.addActionListener(e -> refreshTable());

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(refreshButton);

        return panel;
    }

    private void addItem() {
        try {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String type = (String) typeComboBox.getSelectedItem();

            if (type.equals("Книга")) {
                String author = authorField.getText();
                Book book = new Book(title, author, year);
                repository.addItem(book);
            } else {
                int number = Integer.parseInt(numberField.getText());
                Magazine magazine = new Magazine(title, year, number);
                repository.addItem(magazine);
            }

            JOptionPane.showMessageDialog(this, "Элемент добавлен успешно!");
            clearFields();
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите элемент для обновления!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String type = (String) typeComboBox.getSelectedItem();

            LibraryItem newItem;
            if (type.equals("Книга")) {
                String author = authorField.getText();
                newItem = new Book(title, author, year);
            } else {
                int number = Integer.parseInt(numberField.getText());
                newItem = new Magazine(title, year, number);
            }

            if (repository.updateItem(selectedRow, newItem)) {
                JOptionPane.showMessageDialog(this, "Элемент обновлен успешно!");
                clearFields();
                refreshTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите элемент для удаления!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Удалить выбранный элемент?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (repository.removeItem(selectedRow)) {
                JOptionPane.showMessageDialog(this, "Элемент удален успешно!");
                refreshTable();
            }
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<LibraryItem> items = repository.getAllItems();

        for (LibraryItem item : items) {
            if (item instanceof Book) {
                Book book = (Book) item;
                tableModel.addRow(new Object[]{"Книга", book.getTitle(), book.getAuthor(), book.getYear()});
            } else if (item instanceof Magazine) {
                Magazine magazine = (Magazine) item;
                tableModel.addRow(new Object[]{"Журнал", magazine.getTitle(), magazine.getNumber(), magazine.getYear()});
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        yearField.setText("");
        authorField.setText("");
        numberField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.setVisible(true);
        });
    }
}