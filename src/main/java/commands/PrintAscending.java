package commands;

import service.CollectionClass;
import service.command.Command;
import service.command.NoArgument;

public class PrintAscending implements Command, NoArgument {
    private CollectionClass collectionClass;

    public PrintAscending(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public PrintAscending(){}

    @Override
    public String description() {
        return "print_ascending : вывести элементы коллекции в порядке возрастания";
    }


    @Override
    public String name() {
        return "print_ascending";
    }
    @Override
    public void execute() {
        collectionClass.printAscending();
    }
}
