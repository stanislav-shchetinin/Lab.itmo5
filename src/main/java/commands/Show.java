package commands;

import service.CollectionClass;
import service.command.Command;

public class Show implements Command {
    private CollectionClass collectionClass;

    public Show(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        System.out.print(collectionClass.getCollection().toString().substring(1, collectionClass.getCollection().toString().length() - 1));
    }
}
