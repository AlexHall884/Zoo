package ZooRegistry.View;

import java.util.Scanner;

import ZooRegistry.Controller.PetController;

public class PetView {
    private PetController petController = new PetController();

    public void greetingUser() {
        System.out.println("_______________________");
        System.out.println("Добро пожаловать в реестр домашних животных!");
        System.out.println("Выберете необходимое действие: ");
    }

    public void showMenu() {
        greetingUser();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("_______________________");
                System.out.println("1. Добавить питомца");
                System.out.println("2. Посмоттреть список команд питомца");
                System.out.println("3. Обучить новым командам");
                System.out.println("4. Посмоттреть список питомцев");
                System.out.println("5. Удалить питомца");
                System.out.println("0. Выход");
                int choice;
                while (true) {
                    System.out.println("Ваш выбор: ");
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный ввод, попробуйте ещё раз.");
                    }
                }
                scanner.nextLine(); 
    
                switch (choice) {
                    case 1:
                        petController.addPet();
                        break;
                    case 2:
                        petController.viewCommands();
                        break;
                    case 3:
                        petController.teachCommand();
                        break;
                    case 4:
                        petController.viewPets();
                        break;
                    case 5:
                        petController.removePet();
                        break;
                    case 0:
                        System.out.println("Завершение программы.");
                        return;
                    default:
                        System.out.println("Некорректный ввод, попробуйте ещё раз.");
                        break;
                }
            }
        }
    }
}
