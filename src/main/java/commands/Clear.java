package commands;

import service.CollectionClass;
import service.command.Command;

public class Clear implements Command {

    private CollectionClass collectionClass;

    public Clear(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

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
