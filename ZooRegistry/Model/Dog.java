package ZooRegistry.Model;

import java.time.LocalDate;
import java.util.List;

public class Dog extends Pet{

    public Dog(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getType() {
        return "Dog";
    }
    
}
