package commands;

import service.CollectionClass;
import service.command.Command;

public class PrintUniqueEnginePower implements Command {
    private CollectionClass collectionClass;

    public PrintUniqueEnginePower(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        collectionClass.printUniqueEnginePower();
    }
}
