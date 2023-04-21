package ZooRegistry.Model;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private List<String> commands = new ArrayList<>();

    public void add(String command) {
        commands.add(command);
    }

    public void removeAll() {
        commands.clear();
    }

    public List<String> getAll() {
        return commands;
    }
}
