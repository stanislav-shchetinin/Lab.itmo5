package commands;

import service.CollectionClass;
import service.command.Command;

public class PrintAscending implements Command {
    private CollectionClass collectionClass;

    public PrintAscending(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        collectionClass.printAscending();
    }
}
