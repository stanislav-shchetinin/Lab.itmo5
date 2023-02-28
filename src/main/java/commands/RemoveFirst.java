package commands;

import service.CollectionClass;
import service.command.Command;

public class RemoveFirst implements Command {
    private CollectionClass collectionClass;

    public RemoveFirst(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        collectionClass.removeFirst();
    }
}
