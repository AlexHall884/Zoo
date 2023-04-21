package ZooRegistry.Model;

import java.time.LocalDate;
import java.util.List;

public class Cat extends Pet{

    public Cat(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getType() {
        return "Cat";
    }
    
}
