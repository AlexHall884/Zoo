package ZooRegistry.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validate {

    public static String validateName(Scanner scanner) throws IllegalArgumentException {
        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя животного не может быть пустым!");
        }
        return name;
    }

    public static int validateType(Scanner scanner) throws IllegalArgumentException {
        System.out.println("Выберете тип животного ( 1-> Cat, 2 -> Dog или 3 -> Hamster): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // считываем символ новой строки
        if (type < 1 || type > 3) {
            throw new IllegalArgumentException("Введён неверный тип животного.");
        }
        return type;
    }

    public static LocalDate validateDate(Scanner scanner) {
        System.out.println("Введите дату рождения животного в формате -> (yyyy-mm-dd): ");
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты, введите дату в формате (yyyy-mm-dd):");
            }
        }
    }

    public static List<String> validateCommands(Scanner scanner, int type) throws IllegalArgumentException {
        System.out.println("Введите список команд (каждая команда на новой строке, для окончания введите пустую строку):");
        List<String> commands = new ArrayList<>();
        String command;
        while (!(command = scanner.nextLine().trim()).isEmpty()) {
            commands.add(command);
        }
        if (commands.isEmpty()) {
            throw new IllegalArgumentException("Список команд не может быть пустым!");
        }
        return commands;
    }
}
    
    