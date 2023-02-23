package commands;

import service.CollectionClass;
import service.command.Command;

import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

public class Clear implements Command {

    private CollectionClass collectionClass;

    public Clear(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        collectionClass.clear();
    }
}
