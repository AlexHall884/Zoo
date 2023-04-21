package ZooRegistry.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ZooRegistry.Model.Pet;
import ZooRegistry.Model.Cat;
import ZooRegistry.Model.Dog;
import ZooRegistry.Model.Hamster;

public class PetController {
    private ArrayList<Pet> pets = new ArrayList<>(); // список животных

    // метод добавления нового животного
    public void addPet() {
        Scanner scanner = new Scanner(System.in);
        try (Counter counter = new Counter()) {
            String name = Validate.validateName(scanner);
            int type = Validate.validateType(scanner);

            LocalDate birthDate = Validate.validateDate(scanner);
            List<String> commands = Validate.validateCommands(scanner, type);

            Pet pet = createPet(type, name, birthDate, commands);
            pets.add(pet);

            counter.add();
            System.out.println("Питомец успешно добавлен!");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении питомца: " + e.getMessage());
        }
    }

    // метод создания нового животного
    private Pet createPet(int type, String name, LocalDate birthDate, List<String> commands) {
        switch (type) {
            case 1:
                return new Cat(name, birthDate, commands);
            case 2:
                return new Dog(name, birthDate, commands);
            case 3:
                return new Hamster(name, birthDate, commands);
            default:
                throw new IllegalArgumentException("Введён неверный тип животного.");
        }
    }

    // метод удаления питомца из реестра
    public void removePet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________");
        String name = Validate.validateName(scanner);
        Pet pet = findPet(name);
        if (pet == null) {
            System.out.println("Животное не найдено.");
            return;
        }
        if (pets.remove(pet)) {
            System.out.println(pet.getType() + " " + pet.getName() + " был удален из реестра");
        } else {
            System.out.println("Питомец не найден в реестре");
        }
    }

    // метод добавления новых команд
    public void viewCommands() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________");
        String name = Validate.validateName(scanner);
        Pet pet = findPet(name);
        if (pet == null) {
            System.out.println("Животное не найдено.");
            return;
        }
        List<String> commands = pet.getCommands();
        if (commands.isEmpty()) {
            System.out.println("Команды не найдены.");
            return;
        }
        System.out.println("Команды:");
        for (String command : commands) {
            System.out.println("- " + command);
        }
    }

    // метод обучения новым командам
    public void teachCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________");
        String name = Validate.validateName(scanner);
        Pet pet = findPet(name);
        if (pet == null) {
            System.out.println("Животное не найдено.");
            return;
        }
        System.out.println("Введите новую команду: ");
        String command = scanner.nextLine();
        pet.addCommand(command);
    
        System.out.println("Команда успешна добавлена.");
    }

    // метод поиска по имени
    private Pet findPet(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equals(name)) {
                return pet;
            }
        }
        return null;
    }

    public void viewPets() {
        System.out.println("Список всех питомцев:");
        for (Pet pet : pets) {
            System.out.println("- " + pet.getName() + " (" + pet.getClass().getSimpleName() + ")");
        }
    }
}
