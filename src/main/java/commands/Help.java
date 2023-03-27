package commands;

import service.CollectionClass;
import service.InitGlobalCollections;
import service.command.Command;

import java.util.HashMap;

public class Help implements Command {

    @Override
    public String description() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public String name() {
        return "help";
    }
    @Override
    public void execute() {
        HashMap<String, Command> mapCommand = InitGlobalCollections.mapCommand(new CollectionClass());
        for (Command command : mapCommand.values()){
            System.out.println(command.description());
        }
    }
}
