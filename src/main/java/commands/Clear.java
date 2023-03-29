package commands;

import service.CollectionClass;
import service.command.Command;
import service.command.NoArgument;

public class Clear implements Command, NoArgument {

    private CollectionClass collectionClass;

    public Clear(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public Clear(){}

    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }

    @Override
    public String name() {
        return "clear";
    }
    @Override
    public void execute() {
        collectionClass.clear();
    }
}
