package ZooRegistry.Model;

import java.time.LocalDate;
import java.util.List;

public abstract class Pet {
    private String name;  // имя животного
    private LocalDate birthDate; // дата рождения животного
    private List<String> commands;  // список команд, которые выполняет животное (изначально по умалчанию пустой)

    
    // конструктор класса Pet
    public Pet(String name, LocalDate birthDate, List<String> commands) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
    }

    
    // метод getClass для определения типа животного
    public abstract String getType();

    // геттеры для доступа к полям класса Pet
    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    // метод для добавления новой команды в список команд животного
    public void addCommand(String command) {
        commands.add(command);
    }

    @Override
    public String  toString () {
        // TODO Auto-generated method stub
        return String.format("Тип животного: %s\nИмя: %s \nДата рождения: %s",getType(), getName(), getBirthDate());
    }
   
}

