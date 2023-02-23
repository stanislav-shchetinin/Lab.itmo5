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
        System.out.print(collectionClass.collection.toString().substring(1, collectionClass.collection.toString().length() - 1));
    }
}
