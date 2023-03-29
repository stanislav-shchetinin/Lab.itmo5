package commands;

import service.CollectionClass;
import service.InitGlobalCollections;
import service.command.Command;
import service.command.NoArgument;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Help implements Command, NoArgument {

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
        ArrayList<Command> helpCommand = InitGlobalCollections.helpCommand();
        for (Command command : helpCommand){
            System.out.println(command.description());
        }
    }
}
