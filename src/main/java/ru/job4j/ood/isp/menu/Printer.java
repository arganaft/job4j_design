package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo itemInfo = iterator.next();
            System.out.print(indentation(itemInfo.getNumber().length()));
            System.out.print(itemInfo.getName() + " ");
            System.out.println(itemInfo.getNumber());
        }
    }

    private String indentation(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append("");
        for (int i = 0; i < length - 2; i = i + 2) {
            builder.append("----");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        final ActionDelegate STUB_ACTION = System.out::println;
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        MenuPrinter printer = new Printer();
        printer.print(menu);
    }
}
