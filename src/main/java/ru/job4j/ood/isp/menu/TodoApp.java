package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    Menu menu;
    MenuPrinter printer;
    Scanner scanner = new Scanner(System.in);
    final ActionDelegate defaultAction = () -> System.out.println("Условное действие");

    public TodoApp(Menu menu, MenuPrinter printer) {
        this.menu = menu;
        this.printer = printer;
    }

    public void start() {
        int choice = 0;
        while (choice != 5) {
            System.out.print("Выберите действие:\n"
                    + "1. Добавить элемент в корень меню;\n"
                    + "2. Добавить элемент к родительскому элементу;\n"
                    + "3. Вызвать действие, привязанное к пункту меню;\n"
                    + "4. Вывести меню в консоль;\n"
                    + "5. Закончить работу;\n");
            choice = 0;
            choice = scanner.nextInt();
            validChoice(choice);
        }
    }
    private void validChoice(int choice) {
        if (choice == 0) {
            System.out.println("Вы не выбрали ни один пункт меню, попробуйте еще раз");
        }
        if (choice == 1) {
            scanner.nextLine();
            System.out.println("Введите название элемента для добавления в корень меню");
            String rootElement = scanner.nextLine();
            menu.add(null, rootElement, defaultAction);
        }
        if (choice == 2) {
            scanner.nextLine();
            System.out.println("Введите название родительского элемента");
            String rootElement = scanner.nextLine();
            Optional<Menu.MenuItemInfo> menuItemInfo = menu.select(rootElement);
            if (menuItemInfo.isEmpty()) {
                System.out.println(String.format("Корневой пункт меню \"%s\" не найден", rootElement));
            }
            if (menuItemInfo.isPresent()) {
                System.out.println("теперь введите название самого элемента");
                String childName = scanner.nextLine();
                menu.add(rootElement, childName, defaultAction);
            }

        }
        if (choice == 3) {
            scanner.nextLine();
            System.out.println("Введите название пункта меню");
            String elementName = scanner.nextLine();
            Optional<Menu.MenuItemInfo> menuItemInfo = menu.select(elementName);
            if (menuItemInfo.isPresent()) {
                System.out.printf("%s ->", menuItemInfo.get().getName());
                menuItemInfo.get().getActionDelegate().delegate();
            }
            if (menuItemInfo.isEmpty()) {
                System.out.println(String.format("Пункт меню \"%s\" не найден", elementName));
            }
        }
        if (choice == 4) {
            printer.print(menu);
        }
        if (choice == 5) {
            System.out.println("Завершение работы программы...");
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new Printer();
        TodoApp todoApp = new TodoApp(menu, printer);
        todoApp.start();
    }



}
