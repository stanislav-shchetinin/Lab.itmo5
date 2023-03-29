package commands;

import service.CollectionClass;
import service.command.Command;
import service.command.NoArgument;

public class Show implements Command, NoArgument {
    private CollectionClass collectionClass;

    public Show(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public Show(){}

    @Override
    public String description() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String name() {
        return "show";
    }
    @Override
    public void execute() {
        System.out.print(collectionClass.getCollection().toString().substring(1, collectionClass.getCollection().toString().length() - 1));
    }
}
